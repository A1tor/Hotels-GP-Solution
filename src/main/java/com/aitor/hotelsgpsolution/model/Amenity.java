package com.aitor.hotelsgpsolution.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Amenity {
    @Id
    String name;
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Hotel> hotels;
}
