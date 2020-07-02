package com.budget.budget2.helpers;

import com.budget.budget2.response.Response;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Service
public class ResponseHelper {

    public ResponseHelper() {
    }

    public Response<Object> addErrors(BindingResult bindingResult, Response<Object> response) {
        for (ObjectError errorObject: bindingResult.getAllErrors()) {
            FieldError fieldError = (FieldError) errorObject;
            response.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return response;
    }
}
