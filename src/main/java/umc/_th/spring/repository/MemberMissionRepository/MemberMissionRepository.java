package umc._th.spring.repository.MemberMissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc._th.spring.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMissionRepositoryCustom {
}
