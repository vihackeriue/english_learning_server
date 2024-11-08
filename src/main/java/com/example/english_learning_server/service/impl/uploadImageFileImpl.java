package com.example.english_learning_server.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.english_learning_server.reponsitory.uploadImageFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class uploadImageFileImpl implements uploadImageFile {

    private final Cloudinary cloudinary;
    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        assert file.getOriginalFilename() != null;
        String publicValue = generatePublicValue(file.getOriginalFilename());
        log.info("publicValue is: "+publicValue);
        String extension = getFileName(file.getOriginalFilename())[1];
        log.info("extension is: "+extension);
        File fileUpload = convert(file);
        log.info("fileUpload is: "+fileUpload);
        cloudinary.uploader().upload(fileUpload, ObjectUtils.asMap("public_id", publicValue));

        String filePaths = cloudinary.url().generate(StringUtils.join(publicValue,".",extension));
        cleanDisk(fileUpload);
        return filePaths;
    }

    private File convert(MultipartFile file) throws IOException {
        assert file.getOriginalFilename() != null;
        File convFile = new File(StringUtils.join(generatePublicValue(file.getOriginalFilename()), getFileName(file.getOriginalFilename())[1]));
        try (InputStream is = file.getInputStream()){
            Files.copy(is, convFile.toPath());
        }
        return convFile;
    }

    private void cleanDisk(File file){
        try {
            log.info("file.toPath: "+file.toPath());
            Path filePath = file.toPath();
            Files.delete(filePath);
        } catch (IOException e) {
            log.error("Error");
        }

    }

    public String generatePublicValue(String originalName){
        String fileName = getFileName(originalName)[0];
        return StringUtils.join(UUID.randomUUID().toString(),"_",fileName);
    }

    public String[] getFileName(String originalName){
        return originalName.split("\\.");
    }
}
