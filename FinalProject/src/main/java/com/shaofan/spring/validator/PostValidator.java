package com.shaofan.spring.validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shaofan.spring.pojo.*;
public class PostValidator implements Validator {
	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(Post.class);
	}
	@Override
	public void validate(Object obj, Errors errors) {
//		User user = (User) obj;
		Post post =(Post)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "postName", "error.invalid.postName", "Post Name Required");

		// check if group exists
		
	}
}
