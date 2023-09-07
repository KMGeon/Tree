package dev.test.aswemake.domain.entity.member;

import dev.test.aswemake.domain.controller.dto.request.member.MemberSignupRequest;
import dev.test.aswemake.domain.entity.coupon.Coupon;
import dev.test.aswemake.domain.entity.order.Order;
import dev.test.aswemake.domain.entity.role.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Table(name = "MEMBER", uniqueConstraints = {
        @UniqueConstraint(name = "MEMBER_EMAIL", columnNames = {"email"}),
        @UniqueConstraint(name = "MEMBER_PASSWORD", columnNames = {"password"})
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    /********************************* PK 필드 *********************************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /********************************* PK가 아닌 필드 *********************************/
    private String email;

    private String password;

    private int point = 10000;

    /********************************* 연관관계 매핑 *********************************/
    @ManyToMany
    @JoinTable(name = "member_role",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(
            mappedBy = "member",
            fetch = FetchType.LAZY
    )
    private List<Order> orders = new ArrayList<>();

    @OneToMany(
            mappedBy = "member",
            fetch = FetchType.LAZY
    )
    private Set<Coupon> coupons = new HashSet<>();


    /********************************* public 인터페이스 *********************************/
    public void changeRole(Role role) {
        roles.add(role);
    }

    /********************************* 생성자 *********************************/
    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Builder
    protected Member(Long id, String email, String password, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    /********************************* 비즈니스 로직 *********************************/
    public static Member signupMember(MemberSignupRequest memberSignupRequest, PasswordEncoder passwordEncoder) {
        return new Member(
                memberSignupRequest.getEmail(),
                passwordEncoder.encode(memberSignupRequest.getPassword())
        );
    }
}
