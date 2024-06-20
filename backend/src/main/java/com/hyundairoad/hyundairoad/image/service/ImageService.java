package com.hyundairoad.hyundairoad.image.service;

import com.hyundairoad.hyundairoad.image.domain.vo.Image;
import com.hyundairoad.hyundairoad.image.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {
    private final ImageMapper imageMapper;

    @Transactional(readOnly = true)
    public List<Image> getImageListWithId(Long imageId) {
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
        return imageMapper.getImageListWithPlace(memberId);
    }
}
