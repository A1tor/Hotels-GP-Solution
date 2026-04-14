package com.aitor.hotelsgpsolution.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "Complete hotel description")
public class HotelFull {
    @Schema(description = "Hotel identification number")
    Long id;
    @Schema(description = "Hotel name")
    String name;
    @Schema(description = "Hotel description")
    String description;
    @Schema(description = "Brand that owns the hotel")
    String brand;
    @Schema(description = "Hotel complete address")
    Address address;
    @Schema(description = "Contact number and email of hotel")
    Contacts contacts;
    @Schema(description = "Checkin and checkout time in hotel")
    ArrivalTime arrivalTime;
    @Schema(description = "List of amenities in hotel")
    List<String> amenities;
}
