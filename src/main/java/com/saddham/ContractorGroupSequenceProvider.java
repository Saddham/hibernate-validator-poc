package com.saddham;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saddhamp on 9/3/16.
 */
public class ContractorGroupSequenceProvider implements DefaultGroupSequenceProvider<Contractor>{
    @Override
    public List<Class<?>> getValidationGroups(Contractor contractor) {
        List<Class<?>> defaultGroupSequence = new ArrayList<Class<?>>();
        defaultGroupSequence.add(Contractor.class);

        if(contractor != null && contractor.getCompany() == null){
            defaultGroupSequence.add(EmployeeChecks.class);
        }

        return defaultGroupSequence;
    }
}
