package com.shaofan.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;
import com.shaofan.spring.pojo.*;
@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return clazz.equals(User.class);
	}

	@Override
	public void validate(Object obj, Errors error) {
		
		
		// TODO Auto-generated method stub
		
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "email","error.invalid.email","Email Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "userName", "error.invalid.user", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "password", "error.invalid.password", "Password Required");
		User userAccount = (User) obj;
		
		System.out.println(error);
		if(error.hasErrors()){
            return;
        }
        if(!userAccount.getEmail().contains("@")){
        	error.rejectValue("email", "email-invalid","Email is not valid!");
        }
	}
	

}

