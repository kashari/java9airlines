package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.dto.AppRoleDTO;
import com.sda.java9.finalproject.dto.AppUserDTO;
import com.sda.java9.finalproject.entity.AppRole;
import com.sda.java9.finalproject.entity.AppUser;
import com.sda.java9.finalproject.entity.RoleName;
import com.sda.java9.finalproject.generics.AirlinesMapper;
import com.sda.java9.finalproject.security.jwtutils.JwtUtil;
import com.sda.java9.finalproject.security.model.LoginModel;
import com.sda.java9.finalproject.security.model.SignupModel;
import com.sda.java9.finalproject.repository.AppRoleRepository;
import com.sda.java9.finalproject.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

@RestController @RequestMapping("/api/auth") @RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtils;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginModel loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok("Bearer " +jwt);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupModel signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }
        AppUserDTO user = new AppUserDTO(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<AppRoleDTO> roles = new HashSet<>();
            AppRoleDTO userRole = roleRepository.findByName(RoleName.USER).map(AirlinesMapper::mapAppRoleToDTO)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        user.setRoles(roles);
        user.setIsEnabled(true);
        userRepository.save(AirlinesMapper.mapAppUserDTOToEntity(user));
        return ResponseEntity.ok("User registered successfully!");
    }
}
