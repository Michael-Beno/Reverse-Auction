package ie.michael.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig_API_DB {

	@Configuration
	@Order(1)	// First to be checked....
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter{

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.csrf().disable()
			.antMatcher("/api/**")
			.authorizeRequests()
				.anyRequest().hasRole("API")
			.and()
				.httpBasic();
		}

		@Autowired
		DataSource dataSource;
				
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select api_User.user_Email, api_User.user_Password, api_User.user_Enabled from api_User where api_User.user_Email=?")
			.authoritiesByUsernameQuery("SELECT role.user_Email, role.role_Description FROM role WHERE role.user_Email=?");
		}
	}
	
	@Configuration
	public class FormLoginSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter  {
		@Autowired
		DataSource dataSource;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.antMatchers("/styles/**", "/" , "/login", "/register/**", "/h2/**").permitAll()
				//.antMatchers("/bid/**","/view-all", "/add-job", "/bid-for-job","/403").permitAll()
				//.antMatchers("/view-all", "/bid-for-job").hasAnyRole("USER_ROLE") 
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login").permitAll()
					.usernameParameter("email")
		    .and()
		    	.httpBasic()
			.and()
				.logout()
					.logoutSuccessUrl("/")
						.permitAll()
			.and()
				.exceptionHandling().accessDeniedPage("/403");
			
			http.csrf().disable();	// for h2 console
			http.headers().frameOptions().disable();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("SELECT users.user_Email, users.user_Password, users.user_Enabled FROM users WHERE users.user_Email=?")
			.authoritiesByUsernameQuery("SELECT role.user_Email, role.role_Description FROM role WHERE role.user_Email=?");
		}
	}
}
