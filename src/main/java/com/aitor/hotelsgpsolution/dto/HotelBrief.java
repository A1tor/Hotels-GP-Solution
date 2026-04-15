package com.aitor.hotelsgpsolution.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@Schema(description = "Necessary hotel information")
public class HotelBrief {
    @Schema(description = "Hotel identification number")
    Long id;
    @Schema(description = "Hotel name")
    String name;
    @Schema(description = "Hotel description")
    String description;
    @Schema(description = "Hotel address line")
    String address;
    @Schema(description = "Hotel phone number")
    String phone;
}
