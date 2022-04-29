//package com.example.FlightsManagementSystem.Security;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import javax.sql.DataSource;
//
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    DataSource dataSource;
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("SELECT \"username\", \"password\", true from \"Users\" where \"username\"=?")
//                .authoritiesByUsernameQuery("SELECT \"Users\".\"username\",\"User_roles\".\"role_name\" from \"Users\" join \"User_roles\" on \"Users\".\"user_role\" =\"User_roles\".\"id\"\n" + " where \"username\"=?")
//                .rolePrefix("ROLE_");
//    }
//    @Bean
//    public PasswordEncoder passwordEncoderEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable();
//        http.authorizeHttpRequests()
//                .antMatchers("/admin/login").permitAll()
//                .antMatchers("/admin/**").hasRole("Admin")
//                .antMatchers("/airline/**").hasRole("Airline Company")
//                .antMatchers("/customer/**").hasRole("User")
//                .antMatchers("/**").permitAll();
////        .and().formLogin();
//    }
//}
