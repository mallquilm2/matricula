package edu.cibertec.matricula.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    
    @Autowired
    private SecurityService securityService;
    
    public SecurityConfig(SecurityService userDetailsService){
        this.securityService = userDetailsService;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); 
    }
    
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(securityService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
    
    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.authorizeHttpRequests().requestMatchers("/usuarioEli").hasRole("ADMIN")
                .requestMatchers("/usuarioCrear").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .failureUrl("/login?msgError=true")
                .usernameParameter("usuario")
                .passwordParameter("clave")
                .defaultSuccessUrl("/menu",true);
        return http.build();
    }
    */
    
    @Bean
    public SecurityFilterChain filterChainRest(HttpSecurity http) throws Exception{
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers(HttpMethod.DELETE, "/cursos").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/cursos").hasRole("ADMIN")
                .requestMatchers("/**").permitAll()
                .and()
                .httpBasic();
        return http.build();
    }
    
}
