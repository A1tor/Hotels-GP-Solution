package com.aitor.hotelsgpsolution.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String description;
    String houseNumber;
    String street;
    String city;
    String country;
    String postCode;
    String phone;
    String email;
    String checkIn;
    String checkOut;
}
