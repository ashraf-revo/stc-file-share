package com.asrevo.stcfileshare.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class WebfluxSecurity {

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails viewer = User
                .withUsername("viewer")
                .password("viewer")
                .build();
        UserDetails bviewer = User
                .withUsername("bviewer")
                .password("bviewer")
                .build();

        UserDetails editor = User
                .withUsername("editor")
                .password("editor")
                .build();

        UserDetails beditor = User
                .withUsername("beditor")
                .password("beditor")
                .build();
        return new MapReactiveUserDetailsService(viewer, editor, bviewer, beditor);
    }

    @Bean
    PasswordEncoder encoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
                .httpBasic(withDefaults())
                .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }
}
