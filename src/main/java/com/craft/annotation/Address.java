package com.craft.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Address {

    private String city;
    private String country;

    public Address(
            @Value("Addis") String city,
            @Value("Ethiopia") String country) {

        this.city = city;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address(city=" + city + ", country=" + country + ")";
    }
}