package com.example.english_learning_server.converter;//package com.example.english_learning_server.converter;
//
//import com.example.english_learning_server.dto.UserDTO;
//import com.example.english_learning_server.entity.UserEntity;
//
//public class UserConverter extends BaseConverter<UserDTO, UserEntity>{
//    @Override
//    public UserDTO toDTO(UserEntity entity) {
//        UserDTO userDTO = new UserDTO();
//        if(entity.getId()!= null){
//            userDTO.setId(entity.getId());
//        }
//        userDTO.setEmail(entity.getEmail());
//        userDTO.setPassword(entity.getPassword());
//        userDTO.setFullName(entity.getFullName());
//        userDTO.setAvatar(entity.getAvatar());
//        userDTO.setRole(entity.getRole());
//        userDTO.setStatus(entity.getStatus());
//        return userDTO;
//    }
//
//    @Override
//    public UserEntity toEntity(UserDTO dto) {
//        UserEntity userEnity = new UserEntity();
//        userEnity.setEmail(dto.getEmail());
//        userEnity.setPassword(dto.getPassword());
//        userEnity.setFullName(dto.getFullName());
//        userEnity.setAvatar(dto.getAvatar());
//        userEnity.setRole(dto.getRole());
//        userEnity.setStatus(dto.getStatus());
//        return userEnity;
//    }
//
//    @Override
//    public UserEntity toEntity(UserDTO dto, UserEntity entity) {
//        entity.setEmail(dto.getEmail());
//        entity.setPassword(dto.getPassword());
//        entity.setFullName(dto.getFullName());
//        entity.setAvatar(dto.getAvatar());
//        entity.setRole(dto.getRole());
//        entity.setStatus(dto.getStatus());
//        return entity;
//    }
//}
