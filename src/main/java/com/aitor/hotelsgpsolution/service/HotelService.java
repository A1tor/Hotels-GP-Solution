package com.aitor.hotelsgpsolution.service;

import com.aitor.hotelsgpsolution.dto.HotelBrief;
import com.aitor.hotelsgpsolution.dto.HotelFull;
import com.aitor.hotelsgpsolution.model.Hotel;
import com.aitor.hotelsgpsolution.repository.AmenityListRepository;
import com.aitor.hotelsgpsolution.repository.AmenityRepository;
import com.aitor.hotelsgpsolution.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final AmenityRepository amenityRepository;
    private final AmenityListRepository amenityListRepository;

    public HotelBrief add(HotelFull dto){
        var entity = new Hotel();
        var persisted = hotelRepository.save(entity);
        return new HotelBrief(
                persisted.getId(),
                persisted.getName(),
                persisted.getDescription(),
                String.format("%s %s, %s, %s, %s",
                        persisted.getHouseNumber(),
                        persisted.getStreet(),
                        persisted.getCity(),
                        persisted.getPostCode(),
                        persisted.getCountry()),
                persisted.getPhone());
    }
}
