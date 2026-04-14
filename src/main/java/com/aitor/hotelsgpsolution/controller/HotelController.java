package com.aitor.hotelsgpsolution.controller;

import com.aitor.hotelsgpsolution.dto.HotelBrief;
import com.aitor.hotelsgpsolution.dto.HotelFull;
import com.aitor.hotelsgpsolution.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("hotels")
@Tag(name = "Hotels", description = "Contains CRUD methods for hotels list management")
public class HotelController {
    @Autowired
    private final HotelService service;

    @GetMapping()
    @Operation(summary = "Returns hotel list", description = "Return list of existing hotels")
    public List<HotelBrief> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns hotel data", description = "Receives hotel id. Return full data about hotel")
    public HotelFull get(@PathVariable Long id){
        return service.get(id);
    }

    @PostMapping()
    @Operation(summary = "Create new hotel", description = "Creates new hotel with randomly generated id")
    public HotelBrief add(@RequestBody HotelFull request){
        return service.add(request);
    }

    @PostMapping("/{id}/amenities")
    @Operation(summary = "Add amenities", description = "Add amenities for the existing hotel")
    public void addAmenities(@PathVariable Long id, @RequestBody Set<String> request){
        service.addAmenities(id, request);
    }
}
