package edu.cibertec.matricula.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    
    /*
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
    */
    
    /*
    @Bean
    public SecurityFilterChain filterChainJwt(HttpSecurity http)throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new LoginFilter("/login", authenticationManagerConfig(
                        http.getSharedObject(AuthenticationConfiguration.class))), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
*/
    
    @Bean
    public SecurityFilterChain filterChainOAuth2(HttpSecurity http)throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/","/login").permitAll()
                .anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                .loginPage("/login")
                .defaultSuccessUrl("/menu",true));
        return http.build();
    }
    
    @Bean
    public AuthenticationManager authenticationManagerConfig(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
