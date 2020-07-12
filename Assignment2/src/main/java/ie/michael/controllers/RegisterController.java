package ie.michael.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.michael.entities.Role;
import ie.michael.entities.Users;
import ie.michael.forms.RegisterForm;
import ie.michael.service.RoleService;
import ie.michael.service.UsersService;

@Controller
public class RegisterController {
	
	@Autowired 
	private UsersService usersService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		RegisterForm registerForm = new RegisterForm();
		model.addAttribute("registerForm",registerForm);
		return "register";
	}
	
	@PostMapping("/register")
	public String addNewUserSave(@Valid RegisterForm registerForm, BindingResult binding, RedirectAttributes redirectAttributes)
	{
		if (binding.hasErrors())
			return "register";
		
		Role role = new Role(registerForm.getUserEmail(), "ROLE_USER");
		roleService.save(role);
		Users user = new Users(registerForm.getUserName(), registerForm.getUserEmail(), passwordEncoder.encode(registerForm.getUserPassword()), registerForm.getUserPhone(), role, true);
		usersService.save(user);
		
		return "index";
	}
	
}
