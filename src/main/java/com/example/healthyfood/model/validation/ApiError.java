package com.example.healthyfood.model.validation;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ApiError {

    private final HttpStatus httpStatus;
    private List<String> fieldsWithErrors = new ArrayList<>();


    public ApiError(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public List<String> getFieldsWithErrors() {
        return fieldsWithErrors;
    }

    public ApiError setFieldsWithErrors(List<String> fieldsWithErrors) {
        this.fieldsWithErrors = fieldsWithErrors;
        return this;
    }

    public void addFieldWithError(String error) {
        this.fieldsWithErrors.add(error);
    }

}
