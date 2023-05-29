package com.etelligens.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etelligens.ecommerce.model.MetaData1;

@Repository
public interface MetaData1Repository extends JpaRepository<MetaData1, Long>{

	public Optional<MetaData1> findBySizeAndProductMetaDataId(String size, Long id);

}
