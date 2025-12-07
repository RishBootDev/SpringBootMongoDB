package com.rishbootdev.springbootmongodb.entity;


import lombok.Data;

@Data
public class Address {

    private String line1;
    private String city;
    private String state;
    private String zipCode;
    private String country;

}
