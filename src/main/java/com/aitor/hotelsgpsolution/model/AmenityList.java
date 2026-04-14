package com.aitor.hotelsgpsolution.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmenityList {
    @Id
    Long id;
    List<String> amenities;
}
