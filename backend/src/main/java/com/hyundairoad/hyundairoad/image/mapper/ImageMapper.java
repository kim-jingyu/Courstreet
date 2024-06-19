package com.hyundairoad.hyundairoad.image.mapper;

import com.hyundairoad.hyundairoad.image.domain.dto.ImageDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {
    void insertImage(ImageDto imageDto);
}
