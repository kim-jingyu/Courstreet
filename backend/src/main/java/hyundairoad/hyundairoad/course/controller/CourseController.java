package hyundairoad.hyundairoad.course.controller;

import hyundairoad.hyundairoad.course.domain.dto.CourseCreateRequest;
import hyundairoad.hyundairoad.course.domain.dto.CourseResponse;
import hyundairoad.hyundairoad.course.domain.dto.CourseUpdateRequest;
import hyundairoad.hyundairoad.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<CourseResponse>> getAllCourses() throws MalformedURLException {
        return ResponseEntity.ok().body(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseDetail(@PathVariable Long id) throws MalformedURLException {
        return ResponseEntity.ok().body(courseService.getCourseDetail(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        return ResponseEntity.ok().body(courseService.deleteCourse(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateCourse(@PathVariable Long id, @ModelAttribute CourseUpdateRequest courseUpdateRequest) throws IOException {
        return ResponseEntity.ok().body(courseService.updateCourse(id, courseUpdateRequest));
    }

    @PostMapping
    public ResponseEntity<Void> createCourse(@ModelAttribute CourseCreateRequest courseCreateRequest) throws IOException {
        return ResponseEntity.ok().body(courseService.createCourse(courseCreateRequest));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getCourseSearched(@RequestBody String keyword) throws MalformedURLException {
        return ResponseEntity.ok().body(courseService.searchCourse(keyword));
    }

    @GetMapping("/today-pick")
    public ResponseEntity<List<CourseResponse>> getTodayPick() throws MalformedURLException {
        return ResponseEntity.ok().body(courseService.getTodayPick());
    }
}
