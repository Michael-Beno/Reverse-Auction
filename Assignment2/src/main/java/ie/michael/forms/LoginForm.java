package ie.michael.forms;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginForm {

	@NotBlank
	@Email
	private String userEmail;
	@NotBlank
	private String userPassword;

}
