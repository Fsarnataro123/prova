package com.example.oauth2_google.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/login", "/error").permitAll() // Permetti accesso a queste route
                        .anyRequest().authenticated() // Richiedi autenticazione per altre richieste
                )
                .oauth2Login(oauth2 -> oauth2 // Configura l'autenticazione OAuth2
                        .loginPage("/login") // Pagina di login personalizzata (se necessario)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Endpoint per il logout
                        .logoutSuccessUrl("/") // URL di reindirizzamento dopo il logout
                        .invalidateHttpSession(true) // Invalidare la sessione
                        .deleteCookies("JSESSIONID") // Eliminare i cookie
                );
        return http.build();


    }
}
