package com.budget.budget2.controller;

import com.budget.budget2.dto.AuthRequestDTO;
import com.budget.budget2.entity.Role;
import com.budget.budget2.entity.User;
import com.budget.budget2.response.Response;
import com.budget.budget2.security.jwt.JwtTokenProvider;
import com.budget.budget2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequestDTO) {
        String username = authRequestDTO.getUsername();
        String password = authRequestDTO.getPassword();

        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());

            List<String> roleList = new ArrayList<>();

            for(Role currentRole: user.getRoles()) {
                roleList.add(currentRole.getName());
            }

            Map<Object, Object> responseContent = new HashMap<>();
            responseContent.put("username", username);
            responseContent.put("token", token);
            responseContent.put("roles", roleList);

            Response<Object> response = new Response(responseContent);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
