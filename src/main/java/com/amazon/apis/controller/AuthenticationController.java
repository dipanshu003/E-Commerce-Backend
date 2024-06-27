package com.amazon.apis.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.apis.dto.AuthenticationRequest;
import com.amazon.apis.model.User;
import com.amazon.apis.repo.UserRepository;
import com.amazon.apis.service.jwt.UserDetailsServiceImpl;
import com.amazon.apis.utility.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userServiceImpl;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserRepository userRepo;

    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect email or password...");
        } catch (DisabledException e) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "User not activated");
            return;
        }

        final UserDetails userDetails = userServiceImpl.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        User user = userRepo.findByEmail(authenticationRequest.getEmail());
        
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonResponse = mapper.createObjectNode();
        jsonResponse.put("userId", user.getId());
        jsonResponse.put("role", user.getUserRole().toString());
        jsonResponse.put("jwt", jwt);

        response.setContentType("application/json");
        response.setHeader("Authorization", "Bearer " + jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization, X-PINGOTHER, ORIGIN, X-Requested-With, Content");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, ORIGIN, X-Requested-With, Content");
        response.getWriter().write(jsonResponse.toString());
    }
}
