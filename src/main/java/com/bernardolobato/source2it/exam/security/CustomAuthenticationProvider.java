 package com.bernardolobato.source2it.exam.security;

import java.util.Collections;
import com.bernardolobato.source2it.exam.model.Usuario;
import com.bernardolobato.source2it.exam.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
    
    @Autowired
    UsuarioRepository usuarioRepository;
        
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      String username = authentication.getName();
      String password = authentication.getCredentials().toString();

      Usuario u = this.usuarioRepository.findFirstByEmail(username);

      if (u!= null && passwordEncoder().matches(password, u.getPassword())) {
            log.info("User Authenticated: {}", username);
            return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
       } else {
           log.info("Authentication failed: {}", username);
            throw new BadCredentialsException("Authentication failed");
       }
    }

    @Override
    public boolean supports(Class<?>aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}