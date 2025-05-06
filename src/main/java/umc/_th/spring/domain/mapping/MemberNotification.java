package umc._th.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc._th.spring.domain.Member;
import umc._th.spring.domain.Notification;
import umc._th.spring.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberNotification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "BOOLEAN DEFAULT 0")
    private Boolean isConfirmed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id")
    private Notification notification;
}
