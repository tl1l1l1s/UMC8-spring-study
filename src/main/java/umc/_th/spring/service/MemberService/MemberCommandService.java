package umc._th.spring.service.MemberService;

import umc._th.spring.domain.Member;
import umc._th.spring.web.dto.MemberRequestDTO;

public interface MemberCommandService {
    public Member joinMember(MemberRequestDTO.JoinDto request);
}
