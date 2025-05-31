package umc._th.spring.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import umc._th.spring.domain.Member;
import umc._th.spring.web.dto.MemberResponseDTO;

import java.util.Optional;

public interface MemberQueryService {
    Optional<Member> findById(long id);
    MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request);
}
