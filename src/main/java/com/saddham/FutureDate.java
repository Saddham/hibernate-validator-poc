package com.saddham;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by saddhamp on 9/3/16.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FutureDateValidator.class)
@Documented
public @interface FutureDate {
    String message() default "{com.saddham.invalid.future.date.message}";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};

}
