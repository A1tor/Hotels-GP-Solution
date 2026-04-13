package com.aitor.hotelsgpsolution.mapper;

import com.aitor.hotelsgpsolution.dto.HotelBrief;
import com.aitor.hotelsgpsolution.dto.HotelFull;
import com.aitor.hotelsgpsolution.model.Hotel;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface HotelMapper {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    Hotel toEntity(HotelFull dto);
}
