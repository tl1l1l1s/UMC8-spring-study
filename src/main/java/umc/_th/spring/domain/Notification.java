package umc._th.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc._th.spring.domain.common.BaseEntity;
import umc._th.spring.domain.enums.NotificationType;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column(length = 10)
    private String storeName;
}
