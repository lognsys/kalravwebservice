package com.lognsys.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.core.userdetails.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lognsys.model.Drama;
import com.lognsys.model.Users;

/**
 * 
 * @author pdoshi
 * 
 *         TODO : Use this class for validation of forms attributes
 */

public class FormValidator implements Validator {

	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	String ID_PATTERN = "[0-9]+";
	String STRING_PATTERN = "[a-z \\s A-Z]+";
	String MOBILE_PATTERN = "[0-9]{10}";
	String RATING_PATTERN = "[0-5]{1}";
	String ZIPCODE_PATTERN = "[0-9]{6}";
	private static final String TIME12HOURS_PATTERN =
            "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)";

	@Override
	public boolean supports(Class clazz) {
		   return Users.class.equals(clazz) || Drama.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		System.out.println("Form Validation target "+target);
		if (target instanceof Users) {
			System.out.println("Form Validation target Users "+target);
			
			Users users = (Users) target;
			
			String device = UUID.randomUUID().toString();
			users.setDevice(device);
		
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);

			users.setBirthdate(currentTime);

			if (users.getFirstname() != null && users.getLastname() != null) {
				users.setRealname(users.getFirstname() + " " + users.getLastname());

			}

			validateFirstName(users, errors);
			validateLastName(users, errors);
			validateAddress(users, errors);
			validateCity(users, errors);
			validateState(users, errors);
			validateZipcode(users, errors);
			validatePhonenumber(users, errors);
			validateEmail(users, errors);
		}
		else if (target instanceof Drama) {
			System.out.println("Form Validation target Drama "+target);
			
			Drama dramas = (Drama) target;
			
			validateTitle(dramas,errors);
			validateDramaLength(dramas,errors);
			validateDramaGenre(dramas,errors);
			validateStarCast(dramas,errors);
			validateDirector(dramas,errors);
			validateWriter(dramas,errors);
			validateMusic(dramas,errors);
			validateAverageRating(dramas,errors);
			
		
		}

	}


	private void validateAverageRating(Drama dramas, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "avg_rating", "required.avg_rating", "Average rating is required.");

		// phone number validation
		if (!(dramas.getAvg_rating() != null && dramas.getAvg_rating().isEmpty())) {
			pattern = Pattern.compile(RATING_PATTERN);
			matcher = pattern.matcher(dramas.getAvg_rating());
			if (!matcher.matches()) {
				errors.rejectValue("avg_rating", "avg_rating.incorrect", "Enter a correct Average rating ");
			}
		}
	}

	private void validateMusic(Drama dramas, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "music", "required.music", "Drama Music is required.");

		// input string conatains characters only
		if (!(dramas.getMusic() != null && dramas.getMusic().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dramas.getMusic()));

			if (!matcher.matches()) {
				errors.rejectValue("music", "music.containNonChar", "Enter a valid Drama Music");
			}
		}
	}

	private void validateWriter(Drama dramas, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "writer", "required.writer", "Drama Writer is required.");

		// input string conatains characters only
		if (!(dramas.getWriter() != null && dramas.getWriter().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dramas.getWriter()));

			if (!matcher.matches()) {
				errors.rejectValue("writer", "writer.containNonChar", "Enter a valid Drama Writer");
			}
		}
	}

	private void validateDirector(Drama dramas, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "director", "required.director", "Drama Director is required.");

		// input string conatains characters only
		if (!(dramas.getDirector() != null && dramas.getDirector().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dramas.getDirector()));

			if (!matcher.matches()) {
				errors.rejectValue("director", "director.containNonChar", "Enter a valid Drama Director");
			}
		}
	}

	private void validateStarCast(Drama dramas, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "star_cast", "required.star_cast", "Drama Star cast is required.");

		// input string conatains characters only
		if (!(dramas.getStar_cast() != null && dramas.getStar_cast().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dramas.getStar_cast()));

			if (!matcher.matches()) {
				errors.rejectValue("star_cast", "star_cast.containNonChar", "Enter a valid Drama Star cast");
			}
		}
	}

	private void validateDramaGenre(Drama dramas, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "genre", "required.genre", "Drama Genre is required.");

		// input string conatains characters only
		if (!(dramas.getGenre() != null && dramas.getGenre().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dramas.getGenre()));

			if (!matcher.matches()) {
				errors.rejectValue("genre", "genre.containNonChar", "Enter a valid Drama Genre");
			}
		}
	}

	private void validateDramaLength(Drama dramas, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "drama_length", "required.drama_length", "Drama Length is required.");

		/*// input string conatains characters only
		if (!(dramas.getDrama_length() != null && dramas.getDrama_length().isEmpty())) {
			pattern = Pattern.compile(TIME12HOURS_PATTERN);
			matcher = pattern.matcher((dramas.getDrama_length()));

			if (!matcher.matches()) {
				errors.rejectValue("drama_length", "drama_length.incorrect", "Enter a valid Drama Length");
			}
		}*/
	}

	private void validateTitle(Drama dramas, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "title", "required.title", "Drama Name is required.");

		// input string conatains characters only
		if (!(dramas.getTitle() != null && dramas.getTitle().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((dramas.getTitle()));

			if (!matcher.matches()) {
				errors.rejectValue("title", "title.containNonChar", "Enter a valid Drama name");
			}
		}
	}

	/**
	 * 
	 * @param users
	 * @param errors
	 */
	private void validateEmail(Users users, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required.username", "Email is required.");

		// email validation in spring
		if (!(users.getUsername() != null && users.getUsername().isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(users.getUsername());
			if (!matcher.matches()) {
				errors.rejectValue("username", "username.incorrect", "Enter a correct Email");
			}
		}
	}

	private void validatePhonenumber(Users users, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "required.phone", "Phone is required.");

		// phone number validation
		if (!(users.getPhone() != null && users.getPhone().isEmpty())) {
			pattern = Pattern.compile(MOBILE_PATTERN);
			matcher = pattern.matcher(users.getPhone());
			if (!matcher.matches()) {
				errors.rejectValue("phone", "phone.incorrect", "Enter a correct phone number");
			}
		}
	}

	private void validateZipcode(Users users, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipcode", "required.zipcode", "Zipcode is required.");

		// phone number validation
		if (!(users.getZipcode() != null && users.getZipcode().isEmpty())) {
			pattern = Pattern.compile(ZIPCODE_PATTERN);
			matcher = pattern.matcher(users.getZipcode());
			if (!matcher.matches()) {
				errors.rejectValue("zipcode", "zipcode.incorrect", "Enter a correct Zipcode");
			}
		}

	}

	private void validateState(Users users, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "state", "required.state", "State is required.");

		// input string conatains characters only
		if (!(users.getState() != null && users.getState().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((users.getState()));

			if (!matcher.matches()) {
				errors.rejectValue("state", "state.containNonChar", "Enter a valid State");
			}
		}

	}

	private void validateCity(Users users, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors, "city", "required.city", "City is required.");

		// input string conatains characters only
		if (!(users.getCity() != null && users.getCity().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((users.getCity()));

			if (!matcher.matches()) {
				errors.rejectValue("city", "city.containNonChar", "Enter a valid City");
			}
		}

	}

	private void validateAddress(Users users, Errors errors) {
		// text area validation
		ValidationUtils.rejectIfEmpty(errors, "address", "required.address", "Address is required.");

	}

	private void validateLastName(Users users, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "lastname", "required.lastname", "Last Name is required.");

		// input string conatains characters only
		if (!(users.getLastname() != null && users.getLastname().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((users.getLastname()));

			if (!matcher.matches()) {
				errors.rejectValue("lastname", "lastname.containNonChar", "Enter a valid Last name");
			}
		}

	}

	private void validateFirstName(Users users, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "firstname", "required.firstname", "First Name is required.");

		// input string conatains characters only
		if (!(users.getFirstname() != null && users.getFirstname().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher((users.getFirstname()));

			if (!matcher.matches()) {
				errors.rejectValue("firstname", "firstname.containNonChar", "Enter a valid First name");
			}
		}

	}

}
