package com.d210.moneymoa.config;

import com.d210.moneymoa.domain.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .headers()
                .contentTypeOptions().and()
                .frameOptions().deny().and()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**", "/v2/api-docs", "/webjars/**", "/swagger-resources/**").permitAll()
                .antMatchers("/api/auth/kakao", "/api/auth/naver", "/", "/login", "/signup", "/findpassword", "/emailauth", "/file/**", "/api/products/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().hasRole("MEMBER")
                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class).requestMatchers()
                .and()
                .logout()
                .permitAll();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();

        // Set the allowed origins
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));

        // Set the allowed methods
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        
        // Allow credentials
        configuration.setAllowCredentials(true);

        // Set the allowed headers
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // Expose the Authorization and RefreshToken headers
        configuration.setExposedHeaders(Arrays.asList("Authorization", "RefreshToken"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
