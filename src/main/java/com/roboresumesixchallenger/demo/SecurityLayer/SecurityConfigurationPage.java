package com.roboresumesixchallenger.demo.SecurityLayer;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurationPage extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .authorizeRequests()
                .antMatchers("/css/**","/js/**","/font-awesome/**","/fonts/**").permitAll()
                .antMatchers("/")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/loginpage").permitAll()
                .and()
                .httpBasic();

        //.logoutUrl("/logout").permitAll();


//logout().logoutSuccessUrl



    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception
    {
        auth.inMemoryAuthentication().withUser("newuser").password("newuserpa$$").roles("USER");
        auth.inMemoryAuthentication().withUser("tarig").password("pass").roles("Admin");

    }

}
