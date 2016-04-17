package com.saddham;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by saddhamp on 31/3/16.
 */
public interface Person {
    @NotBlank(message = "Gender cannot be blank", groups = {EmployeeChecks.class})
    public String getGender();
}
