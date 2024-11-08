package com.example.english_learning_server.auth;

import com.example.english_learning_server.config.JwtService;
import com.example.english_learning_server.reponsitory.TokenRepository;
import com.example.english_learning_server.reponsitory.uploadImageFile;
import com.example.english_learning_server.token.TokenType;
import com.example.english_learning_server.reponsitory.UserReponsitory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.english_learning_server.entity.Token;
import com.example.english_learning_server.user.Role;
import com.example.english_learning_server.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserReponsitory reponsitory;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final uploadImageFile uploadImageService;

    public AuthenticationResponse register(RegisterRequest request) {
        // Upload ảnh lên Cloudinary
        String avatarUrl = null;
        if (request.getAvatar() != null && !request.getAvatar().isEmpty()) {
            try {
                avatarUrl = uploadImageService.uploadImage(request.getAvatar());
            } catch (IOException e) {
                throw new RuntimeException("Upload avatar failed", e);
            }
        }

        var user = User.builder()
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .avatar(avatarUrl)
                .status(request.getStatus())
                .createDate(request.getCreateDate())
                .updatedDate(request.getUpdatedDate())
                .createdBy(request.getCreatedBy())
                .updatedBy(request.getUpdatedBy())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser = reponsitory.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = reponsitory.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user,jwtToken);
        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .role(user.getRole()) // Thêm thông tin về vai trò
                .id(user.getId()) // Thêm id user vào phản hồi
                .fullName(user.getFullName())
                .build();
    }

    private void revokeAllUserTokens(User user){
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    public void refreshToken(
            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if(userEmail != null){
            var user = this.reponsitory.findByEmail(userEmail)
                    .orElseThrow();

            if(jwtService.isTokenValid(refreshToken, user)){
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user,accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
