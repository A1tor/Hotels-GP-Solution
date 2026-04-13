package com.aitor.hotelsgpsolution.repository;

import com.aitor.hotelsgpsolution.model.AmenityList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityListRepository extends CrudRepository<AmenityList, Long> {
}