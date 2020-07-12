package ie.michael.forms;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AddJobForm {

	@NotBlank
	private String jobName;
	@NotBlank
	private String jobDescription;
}
