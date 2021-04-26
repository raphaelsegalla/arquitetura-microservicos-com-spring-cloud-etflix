package com.raphaelsegalla.auth.controller;

import com.raphaelsegalla.auth.dto.UserDto;
import com.raphaelsegalla.auth.jwt.JwtTokenProvider;
import com.raphaelsegalla.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @RequestMapping("/testeSecurity")
    public String teste() {
        return "testado";
    }

    @PostMapping(
            produces = {"application/json","application/xml", "application/x-yaml"},
            consumes = {"application/json","application/xml", "application/x-yaml"})
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        try {
            var username = userDto.getUsername();
            var password = userDto.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUsername(username);

            var token = "";

            if (user != null) {
                token = jwtTokenProvider.createToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username not found");
            }

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);

            return ResponseEntity.ok(model);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalida username or password");
        }
    }
}
