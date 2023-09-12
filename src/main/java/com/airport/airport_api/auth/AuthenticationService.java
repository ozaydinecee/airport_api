package com.airport.airport_api.auth;


import com.airport.airport_api.auth.request.AuthenticationRequest;
import com.airport.airport_api.auth.request.RegisterRequest;
import com.airport.airport_api.auth.response.AuthenticationResponse;
import com.airport.airport_api.config.JwtService;
import com.airport.airport_api.model.Role;
import com.airport.airport_api.model.User;
import com.airport.airport_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
           var user= User.builder()
                   .id(UUID.randomUUID())
                   .firstName(request.getFirstName())
                   .lastName(request.getLastName())
                   .email(request.getEmail())
                   .password(passwordEncoder.encode(request.getPassword()))
                   .role(Role.USER)
                   .build();
           userRepository.save(user);
           var jwtToken=jwtService.generateToken(user);
           return AuthenticationResponse.builder()
                   .token(jwtToken)
                   .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user=userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
