package com.hyundairoad.hyundairoad.course.controller;

import com.hyundairoad.hyundairoad.course.domain.dto.CourseDetailDto;
import com.hyundairoad.hyundairoad.course.domain.dto.CreateCourseDto;
import com.hyundairoad.hyundairoad.course.domain.dto.UpdateCourseDto;
import com.hyundairoad.hyundairoad.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/courses", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Void> createCourse(
            @RequestPart("course") CreateCourseDto createCourseDto,
            @RequestPart("courseImage") MultipartFile courseImageFile,
            @RequestPart("coursePlaceImages") List<MultipartFile> coursePlaceImageFiles) throws IOException {
        courseService.createCourse(createCourseDto, courseImageFile, coursePlaceImageFiles);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDetailDto> getCourseDetailsById(@PathVariable Long courseId, @RequestParam Long memberId) {
        CourseDetailDto courseDetailDto = courseService.getCourseDetailsById(courseId, memberId);
        return ResponseEntity.ok(courseDetailDto);
    }

    @GetMapping
    public ResponseEntity<List<CourseDetailDto>> getAllCourses(@RequestParam Long memberId) {
        List<CourseDetailDto> courseDetailDtos = courseService.getCoursesWithMember(memberId);
        return ResponseEntity.ok(courseDetailDtos);
    }

    @PutMapping(value = "/{courseId}", consumes = {"multipart/form-data"})
    public ResponseEntity<Void> updateCourse(
            @PathVariable Long courseId,
            @RequestPart("course") UpdateCourseDto updateCourseDto,
            @RequestPart("courseImage") MultipartFile courseImageFile,
            @RequestPart("coursePlaceImages") List<MultipartFile> coursePlaceImageFiles) throws IOException {
        courseService.updateCourse(updateCourseDto.toBuilder().courseId(courseId).build(), courseImageFile, coursePlaceImageFiles);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/place/{coursePlaceId}")
    public ResponseEntity<Void> deleteCoursePlace(@PathVariable Long coursePlaceId) {
        courseService.deleteCoursePlace(coursePlaceId);
        return ResponseEntity.ok().build();
    }
}