package hyundairoad.hyundairoad.member.domain.like;

import hyundairoad.hyundairoad.course.domain.Course;
import hyundairoad.hyundairoad.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberCourseLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Member member;
    @ManyToOne(fetch = LAZY)
    private Course course;

    private int count;

    public static MemberCourseLike createMemberCourseLike(Member member, Course course) {
        return MemberCourseLike.builder()
                .member(member)
                .course(course)
                .build();
    }

    public void like() {
        count++;
    }

    public void cancelLike() {
        count--;
    }
}
