package hyundairoad.hyundairoad.course.repository;

import hyundairoad.hyundairoad.course.domain.CoursePlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursePlaceRepository extends JpaRepository<CoursePlace, Long> {
    List<CoursePlace> findByCourseIdAndPlaceId(Long courseId, Long placeId);
}
