package com.aitor.hotelsgpsolution.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@Schema(description = "Checkin and checkout time in hotel")
public class ArrivalTime {
    @Schema(description = "Checkin time in hotel")
    String checkIn;
    @Schema(description = "Checkout time in hotel")
    String checkOut;
}
