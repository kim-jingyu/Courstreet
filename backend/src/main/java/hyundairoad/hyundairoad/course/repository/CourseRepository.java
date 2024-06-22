package hyundairoad.hyundairoad.course.repository;

import hyundairoad.hyundairoad.course.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTitleContainingOrContentContaining(String keyword);
    List<Course> findByMemberId(Long memberId);
    List<Course> findByCoursePlacesMemberId(Long memberId);
}
