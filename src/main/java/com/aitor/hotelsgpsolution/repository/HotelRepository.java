package com.aitor.hotelsgpsolution.repository;

import com.aitor.hotelsgpsolution.model.Hotel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends CrudRepository<Hotel,Long>, JpaSpecificationExecutor<Hotel> {

    @Query("SELECT h.brand, COUNT(h) FROM Hotel h GROUP BY h.brand")
    List<Object[]> getHistgramByBrand();

    @Query("SELECT h.city, COUNT(h) FROM Hotel h GROUP BY h.city")
    List<Object[]> getHistgramByCity();

    @Query("SELECT h.country, COUNT(h) FROM Hotel h GROUP BY h.country")
    List<Object[]> getHistgramByCountry();
}