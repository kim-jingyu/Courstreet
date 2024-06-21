package com.hyundairoad.hyundairoad.course.service;

import com.hyundairoad.hyundairoad.course.domain.dto.*;
import com.hyundairoad.hyundairoad.image.service.ImageService;
import com.hyundairoad.hyundairoad.course.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.function.Function;

@Transactional
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseMapper courseMapper;
    private final ImageService imageService;

    public void createCourse(CreateCourseDto createCourseDto, MultipartFile courseImageFile, List<MultipartFile> coursePlaceImageFiles) throws IOException {
        courseMapper.insertCourse(createCourseDto);
        Long courseId = createCourseDto.getCourseId();

        imageService.insertImage(courseImageFile, courseId, null);

        int index = 0;
        for (CreateCoursePlaceDto place : createCourseDto.getCoursePlaces()) {
            CreateCoursePlaceDto createCoursePlaceDto = CreateCoursePlaceDto.builder()
                    .courseId(courseId)
                    .placeId(place.getPlaceId())
                    .memo(place.getMemo())
                    .rank(place.getRank())
                    .build();
            courseMapper.insertCoursePlace(createCoursePlaceDto);

            if (index < coursePlaceImageFiles.size()) {
                MultipartFile coursePlaceImageFile = coursePlaceImageFiles.get(index++);
                imageService.insertImage(coursePlaceImageFile, null, createCoursePlaceDto.getCoursePlaceId());
            }
        }
    }

    @Transactional(readOnly = true)
    public CourseDetailDto getCourseDetailsById(Long courseId, Long memberId) {
        return courseMapper.getCourseDetailsById(courseId, memberId);
    }

    @Transactional(readOnly = true)
    public List<CourseDetailDto> getCoursesWithMember(Long memberId) {
        return courseMapper.getCoursesById(memberId);
    }

    public void updateCourse(UpdateCourseDto updateCourseDto, MultipartFile courseImageFile, List<MultipartFile> coursePlaceImageFiles) throws IOException {
        courseMapper.updateCourse(updateCourseDto);

        imageService.updateImage(courseImageFile, updateCourseDto.getImageId());

        List<CoursePlaceDto> existingCoursePlaces = courseMapper.getCoursePlacesByCourseId(updateCourseDto.getCourseId());
        Map<Long, CoursePlaceDto> existingCoursePlaceMap = existingCoursePlaces.stream()
                .collect(Collectors.toMap(
                        CoursePlaceDto::getCoursePlaceId,
                        Function.identity()
                ));

        int index = 0;
        for (CoursePlaceDto place : updateCourseDto.getCoursePlaces()) {
            if (place.getCoursePlaceId() == null) {
                CreateCoursePlaceDto createCoursePlaceDto = CreateCoursePlaceDto.builder()
                        .courseId(updateCourseDto.getCourseId())
                        .placeId(place.getPlaceId())
                        .memo(place.getMemo())
                        .rank(place.getRank())
                        .build();
                courseMapper.insertCoursePlace(createCoursePlaceDto);

                if (index < coursePlaceImageFiles.size()) {
                    MultipartFile coursePlaceImageFile = coursePlaceImageFiles.get(index++);
                    imageService.insertImage(coursePlaceImageFile, null, createCoursePlaceDto.getCoursePlaceId());
                }
            } else {
                courseMapper.updateCoursePlace(place.toBuilder().courseId(updateCourseDto.getCourseId()).build());

                if (index < coursePlaceImageFiles.size()) {
                    MultipartFile coursePlaceImageFile = coursePlaceImageFiles.get(index++);
                    imageService.updateImage(coursePlaceImageFile, place.getCoursePlaceId());
                }
                existingCoursePlaceMap.remove(place.getCoursePlaceId());
            }
        }

        for (CoursePlaceDto place : existingCoursePlaceMap.values()) {
            courseMapper.deleteCoursePlaceById(place.getCoursePlaceId());
        }
    }

    public void deleteCourse(Long courseId) {
        List<Long> coursePlaceIds = courseMapper.getCoursePlaceIdsByCourseId(courseId);
        imageService.deleteImagesByCoursePlaceIds(coursePlaceIds);
        imageService.deleteImagesByCourseId(courseId);
        courseMapper.deleteCoursePlacesByCourseId(courseId);
        courseMapper.deleteCourseById(courseId);
    }

    public void deleteCoursePlace(Long coursePlaceId) {
        imageService.deleteImagesByCoursePlaceIds(List.of(coursePlaceId));
        courseMapper.deleteCoursePlaceById(coursePlaceId);
    }

    @Transactional(readOnly = true)
    public List<LikedCourseResponseDTO> getLikedCoursesWithMember(Long memberId) {
        return courseMapper.getLikedCoursesByMemberId(memberId);
    }

    public List<CourseResponseDTO> getCoursesWithComments(Long memberId) {
        return courseMapper.getCoursesWithComments(memberId);
    }
}