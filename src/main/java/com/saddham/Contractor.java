package com.saddham;

import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;

/**
 * Created by saddhamp on 8/3/16.
 */
//@GroupSequence({ContractorChecks.class, EmployeeChecks.class, Contractor.class})
@GroupSequenceProvider(ContractorGroupSequenceProvider.class)
public class Contractor extends Employee {

    private String company;

    @NotNull(
            message = "{com.saddham.invalid.null.message}",
            groups = ContractorChecks.class
    )
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
