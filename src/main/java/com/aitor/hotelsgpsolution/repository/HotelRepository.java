package com.aitor.hotelsgpsolution.repository;

import com.aitor.hotelsgpsolution.model.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel,Long> {
}