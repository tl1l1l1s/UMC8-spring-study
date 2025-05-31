package umc._th.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc._th.spring.domain.common.BaseEntity;
import umc._th.spring.domain.enums.Gender;
import umc._th.spring.domain.enums.MemberStatus;
import umc._th.spring.domain.enums.SocialType;
import umc._th.spring.domain.enums.Role;
import umc._th.spring.domain.mapping.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;

    private String name;

    private String address;

    private String specAddress;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'ACTIVE'")
    private MemberStatus status;

    private LocalDate inactiveDate;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isPhoneAuthorized;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberPrefer> memberPreferList = new HashSet<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberNotification> memberNotificationList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberNotificationType> memberNotificationTypeList = new ArrayList<>();

    @Override
    public String toString() {
        return "Member" +
                "id " + id
                + ", name" + name
                + ", address" + address
                + ", specAddress " + specAddress
                + ", gender " + gender
                + ", socialType " + socialType
                + ", status " + status
                + ", inactiveDate " + inactiveDate
                + ", email " + email
                + ", phone " + phone
                + ", isPhoneAuthorized " + isPhoneAuthorized
                + ", point " + point;
    }

    public void encodePassword(String password) {
        this.password = password;
    }
}