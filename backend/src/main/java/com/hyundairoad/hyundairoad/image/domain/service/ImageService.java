package com.hyundairoad.hyundairoad.image.domain.service;

import com.hyundairoad.hyundairoad.image.domain.dto.ImageDto;
import com.hyundairoad.hyundairoad.image.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ImageService {

    private static final String UPLOAD_DIR = "/Users/namjinsu/Downloads/images";
    private final ImageMapper imageMapper;

    public String uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("File is empty");
        }

        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + fileExtension;

        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_DIR + File.separator + newFilename);
        Files.write(path, bytes);

        ImageDto imageDto = ImageDto.builder()
                .originalName(originalFilename)
                .savedName(newFilename)
                .build();
        imageMapper.insertImage(imageDto);
        return newFilename;
    }

    public List<String> listUploadedFiles() throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(UPLOAD_DIR), 1)) {
            return stream
                    .filter(path -> !path.equals(Paths.get(UPLOAD_DIR)))
                    .map(path -> path.getFileName().toString())
                    .collect(Collectors.toList());
        }
    }

    public ImageDto getFileInformation(String filename) throws IOException {
        Path file = Paths.get(UPLOAD_DIR).resolve(filename);
        if (Files.exists(file)) {
            return ImageDto.builder()
                    .originalName(filename)
                    .savedName(file.getFileName().toString())
                    .build();
        } else {
            throw new IOException("File not found");
        }
    }

    public Resource downloadFile(String filename) throws IOException {
        Path file = Paths.get(UPLOAD_DIR).resolve(filename);
        Resource resource = new UrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new IOException("File not readable or not found");
        }
    }
}
