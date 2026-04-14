package com.aitor.hotelsgpsolution.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String brand;
    private String houseNumber;
    private String street;
    private String city;
    private String country;
    private String postCode;
    private String phone;
    private String email;
    private String checkIn;
    private String checkOut;
}
