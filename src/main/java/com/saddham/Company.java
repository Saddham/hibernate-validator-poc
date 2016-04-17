package com.saddham;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by saddhamp on 8/3/16.
 */
public class Company {
    @NotEmpty(
            payload = ValidationSeverity.Warning.class,
            groups = EmployeeChecks.class
    )
    private String name;

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
