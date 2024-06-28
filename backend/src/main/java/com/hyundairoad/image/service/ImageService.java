package com.hyundairoad.image.service;

import com.hyundairoad.image.exception.ImageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static com.hyundairoad.global.constants.ImageConstants.UPLOAD_DIR;

/**
 * ImageService
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Service
@RequiredArgsConstructor
public class ImageService {
    public String uploadFile(MultipartFile file) throws IOException {
        checkImageExists(file);
        String newFilename = getNewFilename(file);
        saveFile(file, newFilename);
        return newFilename;
    }

    public void deleteFile(String imgUrl) throws IOException {
        Files.deleteIfExists(Paths.get(UPLOAD_DIR + File.separator + imgUrl));
    }

    public String updateFile(String oldImgUrl, MultipartFile file) throws IOException {
        checkImageExists(file);
        deleteFile(oldImgUrl);
        String newFilename = getNewFilename(file);
        saveFile(file, newFilename);
        return newFilename;
    }

    private void checkImageExists(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ImageNotFoundException();
        }
    }

    private void saveFile(MultipartFile file, String newFilename) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(getFullImageUrl(newFilename));
        Files.write(path, bytes);
    }

    private String getNewFilename(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : "txt";
        return UUID.randomUUID().toString() + fileExtension;
    }

    private String getFullImageUrl(String fileName) {
        return UPLOAD_DIR + File.separator + fileName;
    }
}
