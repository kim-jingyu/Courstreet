package com.hyundairoad.hyundairoad.image.mapper;

import com.hyundairoad.hyundairoad.image.domain.dto.ImageDto;
import com.hyundairoad.hyundairoad.image.domain.vo.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {
    List<Image> getImageListById(Long imageId);
    List<Image> getImageListWithCoursePlace(Long coursePlaceId);
    List<Image> getImageListWithPlace(Long placeId);
    List<Image> getImageListWithMember(Long memberId);
    void insertImage(ImageDto imageDto);
}
