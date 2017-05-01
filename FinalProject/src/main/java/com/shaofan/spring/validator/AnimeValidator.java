package com.shaofan.spring.validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shaofan.spring.pojo.*;
public class AnimeValidator implements Validator {
	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(Anime.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
//		User user = (User) obj;
		Anime anime=(Anime)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.title", "Title Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "artSupervising", "error.invalid.artSupervising", "ArtSupervising Required");
		
		
		// check if user exists
		
	}
}
