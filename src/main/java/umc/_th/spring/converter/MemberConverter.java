package umc._th.spring.converter;

import umc._th.spring.domain.Member;
import umc._th.spring.domain.enums.Gender;
import umc._th.spring.web.dto.MemberRequestDTO;
import umc._th.spring.web.dto.MemberResponseDTO;
import java.time.LocalDateTime;
import java.util.HashSet;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request){

        Gender gender = switch (request.getGender()) {
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
            default -> Gender.NONE;
        };

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new HashSet<>())
                .build();
    }
}