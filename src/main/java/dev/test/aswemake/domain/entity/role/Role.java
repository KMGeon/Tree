package dev.test.aswemake.domain.entity.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
public class Role {

    //******************************* PK 필드 *********************************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    /********************************* PK가 아닌 필드 *********************************/
    @Column(length = 20)
    private String name;

    /********************************* 생성자 *********************************/
    protected Role() {
    }

    protected Role(Long roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }
}
