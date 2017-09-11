package com.roboresumesixchallenger.demo.SecurityLayer;


import com.roboresumesixchallenger.demo.RepositoryLayer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableGlobalMethodSecurity
public class SecurityConfigurationPage extends WebSecurityConfigurerAdapter {

//    @Autowired
//    public SSUserDetailService userDetailsService;
//
    @Autowired
    private UserDetailsService userDetails;

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsServiceBean()throws Exception
    {
        return new SSUserDetailService(userRepository);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/","/rgrec","/rgseek").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/loginpage").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/loginpage").permitAll().permitAll()
                .and()
                .httpBasic();


        http
                .csrf().disable();

        http
                .headers().frameOptions().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {

        auth
                .userDetailsService(userDetailsServiceBean());
    }



}
