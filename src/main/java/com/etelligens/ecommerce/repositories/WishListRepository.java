package com.etelligens.ecommerce.repositories;

import com.etelligens.ecommerce.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Long> {
    List<WishList> findAllByUserEmailOrderByCreatedDateDesc(String userEmail);
}
