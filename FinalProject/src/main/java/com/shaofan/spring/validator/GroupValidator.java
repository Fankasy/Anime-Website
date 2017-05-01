package com.shaofan.spring.validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shaofan.spring.pojo.*;
public class GroupValidator implements Validator{
	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(Group.class);
	}
	@Override
	public void validate(Object obj, Errors errors) {
//		User user = (User) obj;
		Group group =(Group)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "groupName", "error.invalid.groupName", "Group Name Required");

		// check if group exists
		
	}
	
}
