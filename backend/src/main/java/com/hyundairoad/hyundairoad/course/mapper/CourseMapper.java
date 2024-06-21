package com.hyundairoad.hyundairoad.course.mapper;

import com.hyundairoad.hyundairoad.course.domain.dto.*;
import com.hyundairoad.hyundairoad.image.domain.dto.ImageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CourseMapper {
    List<CourseDetailDto> getCoursesById(Long memberId);
    CourseDetailDto getCourseDetailsById(Long courseId, Long memberId);
    void insertCourse(CreateCourseDto createCourseDto);
    void insertCoursePlace(CreateCoursePlaceDto createCoursePlaceDto);
    void updateCourse(UpdateCourseDto updateCourseDto);
    void updateCoursePlace(CoursePlaceDto coursePlaceDto);
    void deleteCoursePlaceById(Long coursePlaceId);
    void deleteCoursePlacesByCourseId(Long courseId);
    void deleteCourseById(Long courseId);
    List<CoursePlaceDto> getCoursePlacesByCourseId(Long courseId);
    List<Long> getCoursePlaceIdsByCourseId(Long courseId);
    List<LikedCourseResponseDTO> getLikedCoursesByMemberId(Long memberId);
    List<CourseResponseDTO> getCoursesWithComments(Long memberId);
    void insertCourseImage(ImageDto imageDto);
    void insertCoursePlaceImage(ImageDto imageDto);
    void updateCourseImage(ImageDto imageDto);
    void updateCoursePlaceImage(ImageDto imageDto);
}
