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
    @ElementCollection
    @CollectionTable(name = "amenity_list_items", joinColumns = @JoinColumn(name = "amenity_list_id"))
    @Column(name = "amenity_value")
    @OrderColumn(name = "item_order")
    List<String> amenities;
}
