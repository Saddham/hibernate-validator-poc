package com.saddham;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by saddhamp on 8/3/16.
 */
public class Employee implements Person{

    private String gender;

    private String blogURL;

    private String emailID;

    private String creditCardNumber;

    private Calendar lastWorkingDay;

    private double experience;

    private List<Company> pastCompanies = new ArrayList<Company>();

    private Address address;

    private boolean isActive;

    private String designation;

    private String fullName;

    private int age;

    public Employee() {
    }

    @Override
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @ValidFullName(
            payload = ValidationSeverity.Error.class,
            groups = EmployeeChecks.class
    )
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    @ValidAge(
            message = "{com.saddham.invalid.age.message}",
            payload = ValidationSeverity.Error.class,
            groups = EmployeeChecks.class
    )
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NotEmpty(
            message = "{com.saddham.invalid.employee.empty.message}",
            payload = ValidationSeverity.Warning.class,
            groups = EmployeeChecks.class
    )
    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @AssertTrue(
            message = "{com.saddham.invalid.active.message}",
            payload = ValidationSeverity.Error.class,
            groups = EmployeeChecks.class
    )
    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    @NotNull(
            payload = ValidationSeverity.Warning.class
    )
    @Valid
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Valid
    public List<Company> getPastCompanies() {
        return pastCompanies;
    }

    @NotNull(
            payload = ValidationSeverity.Warning.class
    )
    @Digits(
            integer = 3,
            fraction = 2,
            message = "{com.saddham.invalid.number.message}",
            payload = ValidationSeverity.Error.class,
            groups = EmployeeChecks.class
    )
    @Min(
            value = 10,
            message = "{com.saddham.invalid.manager.message}",
            payload = ValidationSeverity.Error.class,
            groups = ManagerChecks.class
    )
    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    @FutureDate(
            payload = ValidationSeverity.Error.class,
            groups = EmployeeChecks.class
    )
    public Calendar getLastWorkingDay() {
        return lastWorkingDay;
    }

    public void setLastWorkingDay(Calendar lastWorkingDay) {
        this.lastWorkingDay = lastWorkingDay;
    }

    public void setPastCompanies(List<Company> pastCompanies) {
        this.pastCompanies = pastCompanies;
    }

    @CreditCardNumber(
            message = "{com.saddham.invalid.credit.card.number.message}",
            payload = ValidationSeverity.Error.class,
            groups = EmployeeChecks.class
    )
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Email(
            regexp = "^([A-Z0-9._%+-]+)@([A-Z0-9.-]+)\\.([A-Z]{2,4})",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "{com.saddham.invalid.emailid.message}",
            payload = ValidationSeverity.Error.class,
            groups = EmployeeChecks.class
    )
    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    @URL(
            regexp = "^(https|http)://([a-z][a-z0-9.-_]+)(:[1-9][0-9]+)?(/[a-z0-9/-_]+)*(#[a-z0-9/-_]+)*(\\?[a-z0-9/-_=?&]+)*$",
            message = "{com.saddham.invalid.url.message}",
            payload = ValidationSeverity.Error.class,
            groups = EmployeeChecks.class
    )
    public String getBlogURL() {
        return blogURL;
    }

    public void setBlogURL(String blogURL) {
        this.blogURL = blogURL;
    }
}
