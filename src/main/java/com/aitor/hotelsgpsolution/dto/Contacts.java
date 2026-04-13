package com.aitor.hotelsgpsolution.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@Schema(description = "Contact number and email of hotel")
public class Contacts {
    @Schema(description = "Hotel contact phone number")
    String phone;
    @Schema(description = "Hotel contact email")
    String email;
}
