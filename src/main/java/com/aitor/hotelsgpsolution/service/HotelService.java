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
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
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
        return hotelMapper.toBrief(hotelRepository.save(hotelMapper.toEntity(dto)));
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

    static final String[] searchCriteriaNamesArray = {"name", "brand", "city", "country"};
    public List<HotelBrief> search(String name, String brand, String city, String country, String amenity){
        Specification<Hotel> spec = null;
        if (amenity != null) {
            var amenityOptional = amenityRepository.findById(amenity);
            if (amenityOptional.isEmpty())
                return new LinkedList<>();
            spec = (root, query, cb) ->
                    root.get("id").in(amenityOptional.get().getHotels());
        }

        var searchCriteriaValuesArray = new String[]{name, brand, city, country};
        for (int i = 0; i < searchCriteriaValuesArray.length; i++) {
            var criteriaName = searchCriteriaNamesArray[i];
            var criteriaValue = searchCriteriaValuesArray[i];
            Specification<Hotel> additionalSpec = (root, query, cb) ->
                    criteriaValue == null ? cb.conjunction() : cb.equal(root.get(criteriaName), criteriaValue);
            spec = spec == null ? additionalSpec : spec.and(additionalSpec);
        }

        return hotelRepository.findAll(spec).stream().map(hotelMapper::toBrief).toList();
    }


    public Map<String, Long> histogram(String param){
        Map<String, Long> histogramMap;
        switch (param) {
            case "amenities":
                histogramMap = new HashMap<>();
                for (var amenity : amenityRepository.findAll())
                    histogramMap.put(amenity.getName(), (long) amenity.getHotels().size());
                break;
            case "brand":
                histogramMap = parseMap(hotelRepository.getHistgramByBrand());
                break;
            case "city":
                histogramMap = parseMap(hotelRepository.getHistgramByCity());
                break;
            case "country":
                histogramMap = parseMap(hotelRepository.getHistgramByCountry());
                break;
            default:
                histogramMap = new HashMap<>();
        }
        return histogramMap;
    }

    Hotel getHotelIfExist(Long id){
        var optional = hotelRepository.findById(id);
        if (optional.isEmpty())
            throw new RuntimeException("Hotel with this id doesn't exist");
        return optional.get();
    }

    Map<String, Long> parseMap(List<Object[]> list){
        var returnMap = new HashMap<String, Long>();
        list.forEach(item -> returnMap.put((String) item[0], (Long) item[1]));
        return returnMap;
    }
}
