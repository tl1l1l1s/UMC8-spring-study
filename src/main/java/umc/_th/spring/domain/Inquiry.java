package umc._th.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc._th.spring.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Inquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false, length = 40)
    private String content;

    @Column(length = 50)
    private String image;

    @Column(length = 40)
    private String answer;
}
