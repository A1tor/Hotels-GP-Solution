package com.aitor.hotelsgpsolution.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Amenity {
    @Id
    String name;
    Set<Long> hotels;
}
