package ie.michael.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	
	
	@Id
	@GeneratedValue
	private int    id;
	private String userName;
	
	@Column
	@NotNull
	@Email
	private String userEmail;
	@JsonIgnore
	@Column
	@Size(min=8)
	private String userPassword;
	private String userPhone;
	
	@JsonIgnore
	@OneToOne
    @JoinColumn(name = "roleEmail", nullable = false)
	private Role userRole;
	@JsonIgnore
	@Column
	boolean userEnabled;
	
	
	
	public Users(String userName, @NotNull @Email String userEmail, @Size(min = 8) String userPassword, String userPhone, Role userRole, boolean userEnabled) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userEnabled = userEnabled;
		this.userPhone = userPhone;
		this.userRole = userRole;
		
	}
	
	@JsonIgnore
	@OneToMany(mappedBy="addedBy")
	List<Jobs> jobs = new ArrayList<Jobs>();


}
