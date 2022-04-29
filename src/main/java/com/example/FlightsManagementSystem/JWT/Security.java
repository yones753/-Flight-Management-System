package com.example.FlightsManagementSystem.JWT;

//import com.example.demo.BusniessLogics.UserRole;
//import com.example.demo.jwt.JWTFilter;
//import com.example.demo.jwt.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//import com.example.springsecurityjwttutorial.repository.UserRepo;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;


//import javax.sql.DataSource;
import java.util.Locale;

@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    //    @Autowired
//    DataSource dataSource;
    @Autowired
    private JWTFilter filter;
    @Autowired
    private MyUserDetailsService uds;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    //jwt
    @Bean
    public PasswordEncoder getpassEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .httpBasic().disable()
                .cors()
                .and().authorizeHttpRequests()
                .antMatchers("/admin/authenticate").permitAll()
                .antMatchers("/airline/authenticate").permitAll()
                .antMatchers("/customer/authenticate").permitAll()
                .antMatchers("/admin/**").hasRole("Admin")
                .antMatchers("/airline/**").hasAnyRole("Airline Company","Admin")
                .antMatchers("/customer/**").hasAnyRole("User","Admin")
                .antMatchers("/").permitAll() //allow any user to connect to the page
                .and()
                .userDetailsService(uds)
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                )
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}