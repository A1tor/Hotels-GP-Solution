package com.aitor.hotelsgpsolution.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class AmenityList {
    @Id
    Long id;
    @OneToMany(mappedBy = "name", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Amenity> amenities;
}
