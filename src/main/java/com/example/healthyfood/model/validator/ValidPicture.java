package com.example.healthyfood.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPictureValidator.class)
public @interface ValidPicture {

    String message() default "Picture file with one of the following types (.jpg, .jpeg, .png) is required! ";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
