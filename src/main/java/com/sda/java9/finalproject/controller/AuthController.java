package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.dao.AppRoleDAO;
import com.sda.java9.finalproject.dao.AppUserDAO;
import com.sda.java9.finalproject.dto.AppRoleDTO;
import com.sda.java9.finalproject.dto.AppUserDTO;
import com.sda.java9.finalproject.entity.RoleName;
import com.sda.java9.finalproject.security.jwtutils.JwtUtil;
import com.sda.java9.finalproject.security.model.LoginModel;
import com.sda.java9.finalproject.security.model.SignupModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

@RestController @RequestMapping("/api/auth") @RequiredArgsConstructor @CrossOrigin(origins = "http://localhost:4200", exposedHeaders = "token")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AppUserDAO userDAO;
    private final AppRoleDAO roleDAO;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtils;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginModel loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "Bearer " +jwt);
        AppUserDTO user = userDAO.findByUsername(loginRequest.getUsername());
        return ResponseEntity.ok().headers(headers).body(user);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return ResponseEntity.ok("Logout successful.");
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupModel signUpRequest) {
        if (userDAO.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }
        if (userDAO.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }
        AppUserDTO user = new AppUserDTO(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<AppRoleDTO> roles = new HashSet<>();
            AppRoleDTO userRole = roleDAO.findByName(RoleName.USER);
            roles.add(userRole);
        user.setRoles(roles);
        user.setIsEnabled(true);
        userDAO.save(user);
        return ResponseEntity.ok("Registration successful.");
    }
}
