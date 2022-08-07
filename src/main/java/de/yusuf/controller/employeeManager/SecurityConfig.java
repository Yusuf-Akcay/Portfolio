package de.yusuf.controller.employeeManager;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  final UserDetailsService userDetailsService;

  public SecurityConfig( UserDetailsService userDetailsService ) {
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
    auth.userDetailsService( userDetailsService ).passwordEncoder( NoOpPasswordEncoder.getInstance() );
  }

  @Override
  protected void configure( HttpSecurity http ) throws Exception {
    http.csrf().disable();
    http.authorizeRequests().antMatchers( "/userManager.xhtml" ).hasAnyRole( "ADMIN", "USER" );
    http.formLogin().loginPage( "/login.xhtml" ).permitAll().defaultSuccessUrl( "/" )
        .failureUrl( "/login.xhtml?error=true" );
    http.logout().logoutSuccessUrl( "/login.xhtml" ).deleteCookies( "JSESSIONID" );
  }


}
