package io.medsys.opteamer.config;

import io.medsys.opteamer.config.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true,jsr250Enabled = true)
public class SecurityConfig {
    @Qualifier("OpteamerDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public AuthenticationManager authManager(){
        var authProvider=new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }

    @Bean
    PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests)->requests
                        .requestMatchers("/api/patients").permitAll()
                        .requestMatchers("/api/patients/**").permitAll()
                        .requestMatchers("/api/operationproviders").permitAll()
                        .requestMatchers("/api/operationproviders/**").permitAll()
                        .requestMatchers("/api/preoperativeassessments").permitAll()
                        .requestMatchers("/api/preoperativeassessments/**").permitAll()
                        .requestMatchers("/api/teammembers").permitAll()
                        .requestMatchers("/api/teammembers/**").permitAll()
                        .requestMatchers("/api/inventories").permitAll()
                        .requestMatchers("/api/inventories/**").permitAll()
                        .requestMatchers("/api/operationrooms").permitAll()
                        .requestMatchers("/api/operationrooms/**").permitAll()
                        .requestMatchers("/api/roominventories").permitAll()
                        .requestMatchers("/api/roominventories/**").permitAll()
                        .requestMatchers("/api/assessments").permitAll()
                        .requestMatchers("/api/assessments/**").permitAll()
                        .requestMatchers("/api/operationtypes").permitAll()
                        .requestMatchers("/api/operationtypes/**").permitAll()
                        .requestMatchers("/api/operations").permitAll()
                        .requestMatchers("/api/operations/**").permitAll()
                        .requestMatchers("/api/operationreports").permitAll()
                        .requestMatchers("/api/operationreports/**").permitAll()
                        .requestMatchers("/api/userregistration").permitAll()
                        .requestMatchers("/api/authenticate").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .logout((logout)->logout.permitAll());
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration=new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization","content-type","x-auth-token"));
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }
}
