package com.aitor.hotelsgpsolution.controller;

import com.aitor.hotelsgpsolution.dto.HotelBrief;
import com.aitor.hotelsgpsolution.dto.HotelFull;
import com.aitor.hotelsgpsolution.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("hotels")
@Tag(name = "Hotels", description = "Contains CRUD methods for hotels list management")
public class HotelController {
    @Autowired
    private final HotelService service;

    @PostMapping()
    @Operation(summary = "Create new hotel", description = "Creates new hotel with randomly generated id")
    public HotelBrief add(@RequestBody HotelFull request){
        return service.add(request);
    }
}
