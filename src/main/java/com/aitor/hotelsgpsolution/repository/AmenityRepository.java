package com.aitor.hotelsgpsolution.repository;

import com.aitor.hotelsgpsolution.model.Amenity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityRepository extends CrudRepository<Amenity,String> {
}