package com.hyundairoad.image.service;

import com.hyundairoad.image.exception.ImageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static com.hyundairoad.global.constants.ImageConstants.UPLOAD_DIR;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {
    public String uploadFile(MultipartFile file) throws IOException {
        checkImageExists(file);
        String newFilename = getNewFilename(file);
        saveFile(file, newFilename);
        return newFilename;
    }

    public Resource getImage(String imgUrl) throws MalformedURLException {
        Resource resource = getResource(UPLOAD_DIR + File.separator + imgUrl);
        if (!resource.exists()) throw new ImageNotFoundException();
        return resource;
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

    private Resource getResource(String imgUrl) throws MalformedURLException {
        return new UrlResource(Paths.get(imgUrl).toUri());
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
