package com.aitor.hotelsgpsolution.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@Schema(description = "Hotel complete address")
public class Address {
    @Schema(description = "House number")
    String houseNumber;
    @Schema(description = "Street name")
    String street;
    @Schema(description = "City name")
    String city;
    @Schema(description = "Country name")
    String country;
    @Schema(description = "Postal code")
    String postCode;
}
