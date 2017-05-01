package com.shaofan.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shaofan.spring.pojo.*;
public class ActualPersonValidator implements Validator {
	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(ActualPerson.class);
	}
	@Override
	public void validate(Object obj, Errors errors) {
//		User user = (User) obj;
		ActualPerson actualPerson =(ActualPerson)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.firstName", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.lastName", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email","Email Required");
		
		// check if user exists
		
	}
	

}
