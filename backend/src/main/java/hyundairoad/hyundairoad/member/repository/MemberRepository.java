package hyundairoad.hyundairoad.member.repository;

import hyundairoad.hyundairoad.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
