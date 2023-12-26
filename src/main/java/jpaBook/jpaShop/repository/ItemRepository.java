package jpaBook.jpaShop.repository;

import jakarta.persistence.EntityManager;
import jpaBook.jpaShop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) { // item은 처음에는 id가 없다.
            em.persist(item); // 신규 등록
        } else {
            em.merge(item); // update 비슷한 것
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() { // jpql
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
