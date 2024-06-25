package com.hyundairoad.course.repository;

import com.hyundairoad.course.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c WHERE c.title LIKE %:keyword% OR c.content LIKE %:keyword%")
    List<Course> findByTitleOrContentContaining(String keyword);
    List<Course> findByMemberId(Long memberId);
}
