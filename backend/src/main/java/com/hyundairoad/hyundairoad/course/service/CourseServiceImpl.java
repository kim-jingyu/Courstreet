package com.hyundairoad.hyundairoad.course.service;

import com.hyundairoad.hyundairoad.course.domain.dto.CourseDetailDto;
import com.hyundairoad.hyundairoad.course.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;

    @Override
    public CourseDetailDto getCourseDetailsById(Long courseId, Long memberId) {
        return courseMapper.getCourseDetailsById(courseId, memberId);
    }

    @Override
    public List<CourseDetailDto> getAllCourses(Long memberId) {
        return courseMapper.getAllCourses(memberId);
    }
}
