package com.aitor.hotelsgpsolution.service;

import com.aitor.hotelsgpsolution.dto.HotelBrief;
import com.aitor.hotelsgpsolution.dto.HotelFull;
import com.aitor.hotelsgpsolution.mapper.HotelMapper;
import com.aitor.hotelsgpsolution.model.Amenity;
import com.aitor.hotelsgpsolution.model.AmenityList;
import com.aitor.hotelsgpsolution.model.Hotel;
import com.aitor.hotelsgpsolution.repository.AmenityListRepository;
import com.aitor.hotelsgpsolution.repository.AmenityRepository;
import com.aitor.hotelsgpsolution.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HotelService {
    final HotelRepository hotelRepository;
    final AmenityRepository amenityRepository;
    final AmenityListRepository amenityListRepository;
    final HotelMapper hotelMapper = HotelMapper.INSTANCE;

    public List<HotelBrief> getAll(){
        var list = new LinkedList<HotelBrief>();
        hotelRepository.findAll().forEach(hotel -> {
            var briefDto = hotelMapper.toBrief(hotel);
            briefDto.setAddress(hotel);
            list.add(briefDto);
        });
        return list;
    }

    public HotelFull get(Long id){
        var fullDto = hotelMapper.toFull(getHotelIfExist(id));
        amenityListRepository.findById(id).ifPresent(
                amenityList -> fullDto.setAmenities(amenityList.getAmenities()));
        return fullDto;
    }

    public HotelBrief add(HotelFull dto){
        var persisted = hotelRepository.save(hotelMapper.toEntity(dto));
        var briefDto = hotelMapper.toBrief(persisted);
        briefDto.setAddress(persisted);
        return briefDto;
    }

    public void addAmenities(Long id, Set<String> amenitiesSet){
        getHotelIfExist(id);
        var list = new ArrayList<String>(amenitiesSet.size());
        var optional = amenityListRepository.findById(id);

        AmenityList amenityListEntity;
        if (optional.isPresent()) {
            amenityListEntity = optional.get();
            var removedAmenitiesSet = new HashSet<>(amenityListEntity.getAmenities());
            removedAmenitiesSet.removeAll(amenitiesSet);
            for (var amenity : amenityRepository.findAllById(removedAmenitiesSet)) {
                amenity.getHotels().remove(id);
                amenityRepository.save(amenity);
            }
        } else {
            amenityListEntity = new AmenityList();
            amenityListEntity.setId(id);
        }

        for (var amenity : amenitiesSet) {
            var optionalAmenity = amenityRepository.findById(amenity);
            Amenity amenityEntity;
            if (optionalAmenity.isEmpty())
                amenityEntity = new Amenity(amenity, Set.of(id));
            else {
                amenityEntity = optionalAmenity.get();
                amenityEntity.getHotels().add(id);
            }
            list.add(amenityRepository.save(amenityEntity).getName());
        }
        amenityListEntity.setAmenities(list);
        amenityListRepository.save(amenityListEntity);
    }

    private Hotel getHotelIfExist(Long id){
        var optional = hotelRepository.findById(id);
        if (optional.isEmpty())
            throw new RuntimeException("Hotel with this id doesn't exist");
        return optional.get();
    }
}
