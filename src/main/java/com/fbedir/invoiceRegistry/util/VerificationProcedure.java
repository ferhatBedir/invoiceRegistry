package com.fbedir.invoiceRegistry.util;

import com.fbedir.invoiceRegistry.exception.BadRequestException;
import com.fbedir.invoiceRegistry.exception.ExceptionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.SmartValidator;

@Configuration
public class VerificationProcedure {

    @Autowired
    private SmartValidator smartValidator;


    public <T> void checkData(T type) {
        DataBinder binder = new DataBinder(type);
        binder.setValidator(smartValidator);
        binder.validate();
        BindingResult bindingResult = binder.getBindingResult();

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(ExceptionMessage.SOME_PARAMETERS_INVALID);
        }
    }
}
