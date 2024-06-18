package com.hyundairoad.hyundairoad.course.mapper;

import com.hyundairoad.hyundairoad.course.domain.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CourseMapper {
    List<CourseDetailDto> getAllCourses(Long memberId);
    CourseDetailDto getCourseDetailsById(@Param("courseId") Long courseId, @Param("memberId") Long memberId);
    void insertCourse(CreateCourseDto createCourseDto);
    void insertCoursePlace(CreateCoursePlaceDto createCoursePlaceDto);
    void updateCourse(UpdateCourseDto updateCourseDto);
    void updateCoursePlace(CoursePlaceDto coursePlaceDto);
    void deleteCoursePlaceById(Long coursePlaceId);
    List<CoursePlaceDto> getCoursePlacesByCourseId(Long courseId);
}
