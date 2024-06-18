package com.hyundairoad.hyundairoad.course.service;

import com.hyundairoad.hyundairoad.course.domain.dto.CourseDetailDto;
import com.hyundairoad.hyundairoad.course.domain.dto.CreateCoursePlaceDto;
import com.hyundairoad.hyundairoad.course.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hyundairoad.hyundairoad.course.domain.dto.CreateCourseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;

    @Transactional
    public void createCourse(CreateCourseDto createCourseDto) {
        courseMapper.insertCourse(createCourseDto);
        Long courseId = createCourseDto.getCourseId();
        createCourseDto.getCoursePlaces().forEach(place -> {
            CreateCoursePlaceDto createCoursePlaceDto = CreateCoursePlaceDto.builder()
                    .courseId(courseId)
                    .placeId(place.getPlaceId())
                    .memo(place.getMemo())
                    .rank(place.getRank())
                    .image(place.getImage())
                    .build();
            courseMapper.insertCoursePlace(createCoursePlaceDto);
        });
    }

    @Override
    public CourseDetailDto getCourseDetailsById(Long courseId, Long memberId) {
        return courseMapper.getCourseDetailsById(courseId, memberId);
    }

    @Override
    public List<CourseDetailDto> getAllCourses(Long memberId) {
        return courseMapper.getAllCourses(memberId);
    }
}