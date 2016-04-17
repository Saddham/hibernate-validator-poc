package com.saddham;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by saddhamp on 9/3/16.
 */
public class FutureDateValidator implements ConstraintValidator<FutureDate, Calendar> {
    Calendar now;

    @Override
    public void initialize(FutureDate futureDate) {
        now = new GregorianCalendar();
    }

    @Override
    public boolean isValid(Calendar futureDate, ConstraintValidatorContext constraintValidatorContext) {
        if(futureDate == null)
            return true;

        boolean res = futureDate.compareTo(now)>0?true:false;

/*        if(!res){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("{com.saddham.future.date.message}");
        }*/

        System.out.println("Saddam: "+res);
        return res;
    }
}
