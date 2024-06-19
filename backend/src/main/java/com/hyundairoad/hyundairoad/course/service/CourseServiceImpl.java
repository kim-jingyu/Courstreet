package com.hyundairoad.hyundairoad.course.service;

import com.hyundairoad.hyundairoad.course.domain.dto.*;
import com.hyundairoad.hyundairoad.course.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Transactional
    public void updateCourse(UpdateCourseDto updateCourseDto) {
        courseMapper.updateCourse(updateCourseDto);

        List<CoursePlaceDto> existingCoursePlaces = courseMapper.getCoursePlacesByCourseId(updateCourseDto.getCourseId());
        Map<Long, CoursePlaceDto> existingCoursePlaceMap = existingCoursePlaces.stream()
                .collect(Collectors.toMap(
                        CoursePlaceDto::getCoursePlaceId,
                        Function.identity()
                ));

        for (CoursePlaceDto place : updateCourseDto.getCoursePlaces()) {
            if (place.getCoursePlaceId() == null) {
                CreateCoursePlaceDto createCoursePlaceDto = CreateCoursePlaceDto.builder()
                        .courseId(updateCourseDto.getCourseId())
                        .placeId(place.getPlaceId())
                        .memo(place.getMemo())
                        .rank(place.getRank())
                        .image(place.getImage())
                        .build();
                courseMapper.insertCoursePlace(createCoursePlaceDto);
            } else {
                courseMapper.updateCoursePlace(place.toBuilder().courseId(updateCourseDto.getCourseId()).build());
                existingCoursePlaceMap.remove(place.getCoursePlaceId());
            }
        }

        for (CoursePlaceDto place : existingCoursePlaceMap.values()) {
            courseMapper.deleteCoursePlaceById(place.getCoursePlaceId());
        }
    }
}