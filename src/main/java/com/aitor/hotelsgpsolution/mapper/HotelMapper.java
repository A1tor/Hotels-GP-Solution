package com.aitor.hotelsgpsolution.mapper;

import com.aitor.hotelsgpsolution.dto.HotelBrief;
import com.aitor.hotelsgpsolution.dto.HotelFull;
import com.aitor.hotelsgpsolution.model.Hotel;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HotelMapper {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    @Mapping(target = "houseNumber", source = "address.houseNumber")
    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "city", source = "address.city")
    @Mapping(target = "country", source = "address.country")
    @Mapping(target = "postCode", source = "address.postCode")
    @Mapping(target = "phone", source = "contacts.phone")
    @Mapping(target = "email", source = "contacts.email")
    @Mapping(target = "checkIn", source = "arrivalTime.checkIn")
    @Mapping(target = "checkOut", source = "arrivalTime.checkOut")
    Hotel toEntity(HotelFull full);

    @Mapping(target = "address.houseNumber", source = "houseNumber")
    @Mapping(target = "address.street", source = "street")
    @Mapping(target = "address.city", source = "city")
    @Mapping(target = "address.country", source = "country")
    @Mapping(target = "address.postCode", source = "postCode")
    @Mapping(target = "contacts.phone", source = "phone")
    @Mapping(target = "contacts.email", source = "email")
    @Mapping(target = "arrivalTime.checkIn", source = "checkIn")
    @Mapping(target = "arrivalTime.checkOut", source = "checkOut")
    HotelFull toFull(Hotel entity);

    HotelBrief toBrief(Hotel entity);
}
