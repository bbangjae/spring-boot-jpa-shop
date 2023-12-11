package jpaBook.jpaShop.domain;

import jakarta.persistence.*;
import jpaBook.jpaShop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    private int price;
    private int stockQuantity;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"), // 중간 테이블에 있는 category_id
            inverseJoinColumns = @JoinColumn(name = "item_id") // 중간 테이블에 있는 item_id
    )
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id") // 부모 카테고리
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
