package hyundairoad.hyundairoad.member.repository;

import hyundairoad.hyundairoad.member.domain.like.MemberCourseLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberCourseLikeRepository extends JpaRepository<MemberCourseLike, Long> {
    MemberCourseLike findByMemberIdAndCourseId(Long memberId, Long courseId);
    List<MemberCourseLike> findByMemberId(Long memberId);
}
