package com.saddham;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by saddhamp on 8/3/16.
 */
public class EmployeeTest {
    private Validator validator;
    private Employee employee;
    private Address address;
    Calendar calendar;

    {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        employee = new Employee();
        employee.setFullName("Saddam Pathan");
        employee.setDesignation("Development Engineer");
        employee.setAge(24);
        employee.setActive(true);
        address = new Address("Hyderabad", "Telangana", "India");
        employee.setAddress(address);
        employee.getPastCompanies().add(new Company("Wavemaker"));
        employee.setExperience(2.7);
        calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, 2016);
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.DAY_OF_MONTH, 24);
        employee.setLastWorkingDay(calendar);
        employee.setCreditCardNumber("4111111111111111");
        employee.setEmailID("saddham@wave.com");
        employee.setBlogURL("https://www.saddham.com:80/profile?user=saddham222&loc=hyd");
        employee.setGender("Male");
    }

    @Test
    public void testDesignation(){
        employee.setDesignation("");
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee, EmployeeChecks.class);
        assertEquals (1, constraintViolations.size( ));
        Iterator<ConstraintViolation<Employee>> iterator = constraintViolations.iterator();
        ConstraintViolation<Employee> constraintViolation = iterator.next();
        System.out.println(constraintViolation.getMessage());
        System.out.println(constraintViolation.getConstraintDescriptor().getPayload());
    }

    @Test
    public void testFullNameLength(){
        employee.setFullName("");
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee, EmployeeChecks.class);
        assertEquals (2, constraintViolations.size( ));
        Iterator<ConstraintViolation<Employee>> iterator = constraintViolations.iterator();
        ConstraintViolation<Employee> constraintViolation = iterator.next();
        System.out.println(constraintViolation.getMessage());
        System.out.println(constraintViolation.getConstraintDescriptor().getPayload());
        constraintViolation = iterator.next();
        System.out.println(constraintViolation.getMessage());
        System.out.println(constraintViolation.getConstraintDescriptor().getPayload());
    }

    @Test
    public void testAge(){
        employee.setAge(61);
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee, EmployeeChecks.class);
        assertEquals(1, constraintViolations.size());
        Iterator<ConstraintViolation<Employee>> iterator = constraintViolations.iterator();
        ConstraintViolation<Employee> constraintViolation = iterator.next();
        System.out.println(constraintViolation.getMessage());
        System.out.println(constraintViolation.getConstraintDescriptor().getPayload());
    }

    @Test
    public void testIsActive(){
        employee.setActive(false);
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee, EmployeeChecks.class);
        assertEquals(1, constraintViolations.size());
        Iterator<ConstraintViolation<Employee>> iterator = constraintViolations.iterator();
        ConstraintViolation<Employee> constraintViolation = iterator.next();
        System.out.println(constraintViolation.getMessage());
        System.out.println(constraintViolation.getConstraintDescriptor().getPayload());
    }

    @Test
    public void testValidEmployeeDetails(){
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee, EmployeeChecks.class);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testAddress(){
        address.setCountry(null);
        employee.setAddress(address);
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee, EmployeeChecks.class);
        assertEquals(1, constraintViolations.size());
        Iterator<ConstraintViolation<Employee>> iterator = constraintViolations.iterator();
        ConstraintViolation<Employee> constraintViolation = iterator.next();
        System.out.println(constraintViolation.getMessage());
        System.out.println(constraintViolation.getConstraintDescriptor().getPayload());
    }

    @Test
    public void testPastCompanies(){
        employee.getPastCompanies().add(new Company(null));
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee, EmployeeChecks.class);
        assertEquals(1, constraintViolations.size());
        Iterator<ConstraintViolation<Employee>> iterator = constraintViolations.iterator();
        ConstraintViolation<Employee> constraintViolation = iterator.next();
        System.out.println(constraintViolation.getMessage());
        System.out.println(constraintViolation.getConstraintDescriptor().getPayload());
    }

    @Test
    public void testLastWorkingDay(){
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, 2);
        calendar.set(Calendar.DAY_OF_MONTH, 2);
        employee.setLastWorkingDay(calendar);
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee, EmployeeChecks.class);
        assertEquals(1, constraintViolations.size());
        Iterator<ConstraintViolation<Employee>> iterator = constraintViolations.iterator();
        ConstraintViolation<Employee> constraintViolation = iterator.next();
        System.out.println(constraintViolation.getMessage());
        System.out.println(constraintViolation.getConstraintDescriptor().getPayload());
    }

    @Test
    public void testNotManager(){
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee, OrderedChecks.class);
        assertEquals(1, constraintViolations.size());
        Iterator<ConstraintViolation<Employee>> iterator = constraintViolations.iterator();
        ConstraintViolation<Employee> constraintViolation = iterator.next();
        System.out.println(constraintViolation.getMessage());
        System.out.println(constraintViolation.getConstraintDescriptor().getPayload());
    }

    @Test
    public void testManager(){
        employee.setExperience(11);
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee, OrderedChecks.class);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testContractor(){
        Contractor contractor = new Contractor();
        contractor.setExperience(5);
        contractor.setAddress(new Address("Hyd", "TL", "IN"));
        contractor.setAge(24);
        contractor.setFullName("Saad Pathan");
        contractor.setActive(true);
        contractor.setDesignation("Development Engg");
        contractor.setCompany("Pramati");
        contractor.getPastCompanies().add(new Company("Cog"));
        Set<ConstraintViolation<Contractor>> constraintViolations = validator.validate(contractor);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testCreditCardNumber(){
        employee.setCreditCardNumber("4112111211121112");
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee, EmployeeChecks.class);
        assertEquals(1, constraintViolations.size());

        Iterator<ConstraintViolation<Employee>> iterator = constraintViolations.iterator();
        ConstraintViolation<Employee> constraintViolation = iterator.next();
        System.out.println(constraintViolation.getMessage());
        System.out.println(constraintViolation.getConstraintDescriptor().getPayload());
    }

    @Test
    public void testEmailID(){
        employee.setEmailID("#fginj .$^f fd.com");
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee, EmployeeChecks.class);
        assertEquals(1, constraintViolations.size());
        Iterator<ConstraintViolation<Employee>> iterator = constraintViolations.iterator();
        ConstraintViolation<Employee> constraintViolation = iterator.next();
        System.out.println(constraintViolation.getMessage());
        System.out.println(constraintViolation.getConstraintDescriptor().getPayload());
    }

    @Test
    public void testBlogURL(){
        employee.setBlogURL("https://www.saddham.com:80/profile#user=saddham222&loc=hyd");
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee, EmployeeChecks.class);
        assertEquals(1, constraintViolations.size());
        Iterator<ConstraintViolation<Employee>> iterator = constraintViolations.iterator();
        ConstraintViolation<Employee> constraintViolation = iterator.next();
        System.out.println(constraintViolation.getMessage());
        System.out.println(constraintViolation.getConstraintDescriptor().getPayload());
    }

    @Test
    public void testRegex(){
        Pattern pattern = Pattern.compile("^(?!\\s*$).+");
        Matcher matcher = pattern.matcher("fdgdfgg");
        if (matcher.find()){
            System.out.println("Match found");
        }
    }

    @Test
    public void testInheritance(){
        employee.setGender(null);
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee, EmployeeChecks.class);
        assertEquals(1, constraintViolations.size());
        Iterator<ConstraintViolation<Employee>> iterator = constraintViolations.iterator();
        ConstraintViolation<Employee> constraintViolation = iterator.next();
        System.out.println(constraintViolation.getMessage());
        System.out.println(constraintViolation.getConstraintDescriptor().getPayload());
    }
}