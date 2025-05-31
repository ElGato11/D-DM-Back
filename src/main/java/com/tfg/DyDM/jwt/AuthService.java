package com.tfg.DyDM.jwt;

import com.tfg.DyDM.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));//valida al usuario y contraseña
        UserDetails user=userRepository.findByNameUserDetails(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();

    }
}
