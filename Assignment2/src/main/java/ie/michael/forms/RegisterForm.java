package ie.michael.forms;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class RegisterForm {
	@NotNull
	@Size(min=3, max=20)
	private String userName;
	@NotBlank
	@Email
	private String userEmail;
	@DecimalMin(value = "00000000")
	private String userPhone;
	@Size(min=8, max=16)
	private String userPassword;
}
