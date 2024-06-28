package com.hyundairoad.course.repository;

import com.hyundairoad.course.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * CourseRepository
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c WHERE c.title LIKE %:keyword% OR c.content LIKE %:keyword%")
    List<Course> findByTitleOrContentContaining(String keyword);
    List<Course> findByMemberId(Long memberId);
    Course findByIdAndMemberId(Long id, Long memberId);
    boolean existsByMemberIdAndId(Long memberId, Long id);

    @Query(value = "SELECT id, member_id, course_img_url, title, content, start_time, end_time, theme, with_whom, visibility FROM (SELECT id, member_id, course_img_url, title, content, start_time, end_time, theme, with_whom, visibility " +
            "FROM course ORDER BY DBMS_RANDOM.VALUE) " +
            "WHERE ROWNUM <= 5", nativeQuery = true)
    List<Course> findRandomCourses();
}
