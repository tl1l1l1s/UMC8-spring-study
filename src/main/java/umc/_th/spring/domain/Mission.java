package umc._th.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc._th.spring.domain.common.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false)
    private Integer reward;

    private LocalDateTime expiredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Override
    public String toString() {
        return "mission : "
                + "point " + point
                + ", reward " + reward
                + ", expired at " + expiredAt;
    }
}
