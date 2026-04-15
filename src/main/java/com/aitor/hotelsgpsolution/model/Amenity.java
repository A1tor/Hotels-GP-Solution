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
    @ElementCollection
    @CollectionTable(name = "amenity_hotels", joinColumns = @JoinColumn(name = "amenity_name"))
    @Column(name = "hotel_id")
    Set<Long> hotels;
}
