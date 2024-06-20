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

@Transactional
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseMapper courseMapper;

    public void createCourse(CreateCourseDto createCourseDto) {
        courseMapper.insertCourse(createCourseDto);
        Long courseId = createCourseDto.getCourseId();
        createCourseDto.getCoursePlaces().forEach(place -> {
            CreateCoursePlaceDto createCoursePlaceDto = CreateCoursePlaceDto.builder()
                    .courseId(courseId)
                    .placeId(place.getPlaceId())
                    .memo(place.getMemo())
                    .rank(place.getRank())
                    .build();
            courseMapper.insertCoursePlace(createCoursePlaceDto);
        });
    }

    @Transactional(readOnly = true)
    public CourseDetailDto getCourseDetailsById(Long courseId, Long memberId) {
        return courseMapper.getCourseDetailsById(courseId, memberId);
    }

    @Transactional(readOnly = true)
    public List<CourseDetailDto> getCoursesWithMember(Long memberId) {
        return courseMapper.getCoursesById(memberId);
    }

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

    @Transactional(readOnly = true)
    public List<LikedCourseResponseDTO> getLikedCoursesWithMember(Long memberId) {
        return courseMapper.getLikedCoursesByMemberId(memberId);
    }

    public List<CourseResponseDTO> getCoursesWithComments(Long memberId) {
        return courseMapper.getCoursesWithComments(memberId);
    }
}
