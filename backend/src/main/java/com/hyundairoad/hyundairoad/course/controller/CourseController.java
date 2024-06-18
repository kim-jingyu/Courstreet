package com.hyundairoad.hyundairoad.course.controller;

import com.hyundairoad.hyundairoad.course.domain.dto.CourseDetailDto;
import com.hyundairoad.hyundairoad.course.domain.dto.CreateCourseDto;
import com.hyundairoad.hyundairoad.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/courses", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Void> createCourse(@RequestBody CreateCourseDto createCourseDto) {
        courseService.createCourse(createCourseDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDetailDto> getCourseDetailsById(@PathVariable Long courseId, @RequestParam Long memberId) {
        CourseDetailDto courseDetailDto = courseService.getCourseDetailsById(courseId, memberId);
        return ResponseEntity.ok(courseDetailDto);
    }

    @GetMapping
    public ResponseEntity<List<CourseDetailDto>> getAllCourses(@RequestParam Long memberId) {
        List<CourseDetailDto> courseDetailDtos = courseService.getAllCourses(memberId);
        return ResponseEntity.ok(courseDetailDtos);
    }
}