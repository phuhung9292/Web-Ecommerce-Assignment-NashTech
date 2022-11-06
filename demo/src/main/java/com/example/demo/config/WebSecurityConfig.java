package com.example.demo.config;

import com.example.demo.Repository.IUserRepository;
import com.example.demo.security.jwt.JwtEntryPoint;
import com.example.demo.security.jwt.JwtTokenFilter;
import com.example.demo.security.userPrincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    private JwtEntryPoint jwtEntryPoint;
    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }
    @Override
    protected void configure(HttpSecurity httpSecurity)throws Exception{
        CorsConfiguration config = new CorsConfiguration();
        List<String> newlist = new ArrayList<>();
        config.setAllowCredentials(true);
        List<String> newList2 = new ArrayList<>();
        newList2.add("Authorization");
        newList2.add("Cache-Control");
        newList2.add("Content-Type");


        config.setAllowedHeaders(newList2);
        config.addAllowedMethod("*");
        newlist.add("http://localhost:3000");
        config.setAllowedOrigins(newlist);
//        CorsConfiguration config = new CorsConfiguration();
//
//        config.setAllowCredentials(true);
//        config.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "token"));
//        config.addAllowedMethod("*");
//        config.setAllowedOrigins(List.of("http://localhost:3000"));

            httpSecurity.cors().and().csrf().disable().cors().configurationSource(request -> config).and()
                .authorizeRequests().antMatchers("/api/v1/user/signin").permitAll()
//                .antMatchers(GET,"/api/v1/productItem").permitAll()
                .antMatchers("/api/v1/cart/**").hasAuthority("Customer")
                    .antMatchers(POST,"/api/v1/category/update/**").hasAuthority("Admin")

                    .antMatchers(POST,"/api/v1/category/createcategory").hasAuthority("Admin")
                .antMatchers(DELETE,"/api/v1/category/createcategory/**").hasAuthority("Admin")
                .antMatchers(GET,"/api/v1/admin/order").hasAuthority("Admin")
                    .antMatchers(GET,"/api/v1/admin/order/detail/**").hasAuthority("Customer")
                    .antMatchers(GET,"/api/v1/admin/order/status/**").hasAuthority("Customer")
                    .antMatchers(POST,"/api/v1/product").hasAuthority("Admin")
                .antMatchers(PUT,"/api/v1/product/**").hasAuthority("Admin")
                .antMatchers(PUT,"/api/v1/productitem").hasAuthority("Admin")
                .antMatchers(POST,"/api/v1/productitem/**").hasAuthority("Admin")
                .antMatchers(GET,"/api/v1/user/users").hasAuthority("Admin")
                .antMatchers(DELETE,"/api/v1/user/**").hasAuthority("Admin")
                .antMatchers(GET,"/api/v1/user").hasAuthority("Customer")
                .antMatchers(POST,"/api/v1/variationoption/**").hasAuthority("Admin")
                .antMatchers("/api/v1/variation").hasAuthority("Admin")
                .antMatchers(POST,"/api/v1/productitem").hasAuthority("Admin")
//                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                httpSecurity.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

//        httpSecurity.csrf().disable();
//        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        httpSecurity.authorizeRequests().antMatchers("/api/v1/signin/**").permitAll();
//        httpSecurity.authorizeRequests().antMatchers(GET,"/api/v1/**").hasAnyAuthority("Customer");
//        httpSecurity.authorizeRequests().antMatchers(POST,"/api/v1/admin").hasAnyAuthority("Admin");
//        httpSecurity.authorizeRequests().anyRequest().authenticated();
//        httpSecurity.addFilterBefore(jwtTokenFilter(),UsernamePasswordAuthenticationFilter.class);

    }
}
