package umc._th.spring.repository.MemberRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc._th.spring.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
