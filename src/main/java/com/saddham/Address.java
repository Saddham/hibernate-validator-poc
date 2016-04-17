package com.saddham;

import javax.validation.constraints.NotNull;

/**
 * Created by saddhamp on 8/3/16.
 */

public class Address {
    private String city;
    private String state;
    private String country;

    public Address(String city, String state, String country) {
        this.city = city;
        this.state = state;
        this.country = country;
    }

    @NotNull(
            payload = ValidationSeverity.Warning.class,
            groups = EmployeeChecks.class
    )
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @NotNull(
            payload = ValidationSeverity.Warning.class,
            groups = EmployeeChecks.class
    )
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @NotNull(
            payload = ValidationSeverity.Warning.class,
            groups = EmployeeChecks.class
    )
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
