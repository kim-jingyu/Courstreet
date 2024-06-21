package com.hyundairoad.hyundairoad.image.service;

import com.hyundairoad.hyundairoad.image.domain.vo.Image;
import com.hyundairoad.hyundairoad.image.domain.dto.ImageDto;
import com.hyundairoad.hyundairoad.image.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {
    private final ImageMapper imageMapper;

    private static final String IMAGE_DIRECTORY = "/Users/namjinsu/Downloads/images";

    @Transactional(readOnly = true)
    public List<Image> getImageListById(Long imageId) {
        return imageMapper.getImageListById(imageId);
    }

    @Transactional(readOnly = true)
    public List<Image> getImageListWithCoursePlace(Long coursePlaceId) {
        return imageMapper.getImageListWithCoursePlace(coursePlaceId);
    }

    @Transactional(readOnly = true)
    public List<Image> getImageListWithPlace(Long placeId) {
        return imageMapper.getImageListWithPlace(placeId);
    }

    @Transactional(readOnly = true)
    public List<Image> getImageListWithMember(Long memberId) {
        return imageMapper.getImageListWithMember(memberId);
    }

    public void insertImage(MultipartFile file, Long courseId, Long coursePlaceId) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String originalFilename = file.getOriginalFilename();
        String savedFilename = UUID.randomUUID().toString() + "_" + originalFilename;
        File dest = new File(IMAGE_DIRECTORY, savedFilename);
        file.transferTo(dest);

        ImageDto imageDto = ImageDto.builder()
                .courseId(courseId)
                .coursePlaceId(coursePlaceId)
                .originalName(originalFilename)
                .savedName(savedFilename)
                .build();

        imageMapper.insertImage(imageDto);
    }

    public void updateImage(MultipartFile file, Long imageId) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String originalFilename = file.getOriginalFilename();
        String savedFilename = UUID.randomUUID().toString() + "_" + originalFilename;
        File dest = new File(IMAGE_DIRECTORY, savedFilename);
        file.transferTo(dest);

        ImageDto imageDto = ImageDto.builder()
                .imageId(imageId)
                .originalName(originalFilename)
                .savedName(savedFilename)
                .build();

        imageMapper.updateImage(imageDto);
    }

    public void deleteImage(Long imageId) {
        Image image = imageMapper.getImageById(imageId);
        if (image != null) {
            File file = new File(IMAGE_DIRECTORY, image.getSavedName());
            if (file.exists()) {
                file.delete();
            }
            imageMapper.deleteImage(imageId);
        }
    }

    public void deleteImagesByCoursePlaceIds(List<Long> coursePlaceIds) {
        for (Long coursePlaceId : coursePlaceIds) {
            List<Image> images = imageMapper.getImageListWithCoursePlace(coursePlaceId);
            for (Image image : images) {
                File file = new File(IMAGE_DIRECTORY, image.getSavedName());
                if (file.exists()) {
                    file.delete();
                }
                imageMapper.deleteImage(image.getImageId());
            }
        }
    }

    public void deleteImagesByCourseId(Long courseId) {
        List<Image> images = imageMapper.getImagesByCourseId(courseId);
        for (Image image : images) {
            File file = new File(IMAGE_DIRECTORY, image.getSavedName());
            if (file.exists()) {
                file.delete();
            }
            imageMapper.deleteImage(image.getImageId());
        }
    }
}
