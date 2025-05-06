package umc._th.spring.service.MemberService;

import umc._th.spring.domain.Member;

import java.util.Optional;

public interface MemberQueryService {

    Optional<Member> findById(long id);
}
