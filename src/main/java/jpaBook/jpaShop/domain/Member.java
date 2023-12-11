package jpaBook.jpaShop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장 타입을 포함 했다는 의미
    private Address address;

    @OneToMany(mappedBy = "member") // Order 테이블의 member 필드에 의해 매핑된 것이다.
    private List<Order> orders = new ArrayList<>();
}
