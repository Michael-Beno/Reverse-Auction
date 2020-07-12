package ie.michael.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	
	
	private int    id;
	private String userName;
	private String userEmail;
	private String userPhone;

}
