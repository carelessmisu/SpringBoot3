package com.example.samplesecurityapp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SampleSecurityConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests(authorize -> {
            authorize
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/js/**").permitAll()
                    .requestMatchers("/css/**").permitAll()
                    .requestMatchers("/img/**").permitAll()
                    .requestMatchers("/admin").hasRole("ADMIN")
                    .anyRequest().authenticated();
        });
        http.formLogin(form -> {
            form.defaultSuccessUrl("/secret");
        });
        return http.build();
    }

    // @Bean
    // public InMemoryUserDetailsManager userDetailsService() {
    // String username = "user";
    // String password = "pass";

    // UserDetails userDetails = User.withUsername(username)
    // .password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password)).roles("USER")
    // .build();

    // return new InMemoryUserDetailsManager(userDetails);// {noop} is used to
    // indicate that the password is stored in
    // }

    @Bean
    public UserDetailsManager userDetailsManager() {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(this.dataSource);

        // create users
        // users.createUser(makeUser("taro", "yamada", "USER"));
        // users.createUser(makeUser("hanako", "flower", "USER"));
        // users.createUser(makeUser("sachiko", "happy", "USER"));
        // users.createUser(makeUser("admin", "kanri", "ADMIN"));

        return users;
    }

    // private UserDetails makeUser(String username, String password, String role) {
    //     return User.withUsername(username)
    //             .password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
    //             .roles(role)
    //             .build();
    // }
}
