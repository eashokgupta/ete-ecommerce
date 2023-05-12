package com.etelligens.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etelligens.ecommerce.model.Slug;

@Repository
public interface SlugRepository extends JpaRepository<Slug, Long>{

}
