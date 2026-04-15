package com.aitor.hotelsgpsolution.controller;

import com.aitor.hotelsgpsolution.dto.HotelBrief;
import com.aitor.hotelsgpsolution.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "Search", description = "Contains search methods for hotels list")
public class SearchController {
    @Autowired
    private final HotelService service;

    @GetMapping("/search")
    @Operation(summary = "Search for hotels", description = "Allows to filter list of hotels by name, brand, city, country and amenities")
    public List<HotelBrief> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String amenity){
        return service.search(name, brand, city, country, amenity);
    }

    @GetMapping("/histogram/{param}")
    @Operation(summary = "Get hotels histogram", description = "Gets the number of hotels grouped by each value of the specified parameter. Parameter: brand, city, country, amenity.")
    public Map<String, Long> search(@PathVariable String param){
        return service.histogram(param);
    }
}
