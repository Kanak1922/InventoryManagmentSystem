package com.kanak.ims.controller;

import com.kanak.ims.dto.JWTAuthResponse;
import com.kanak.ims.dto.LoginDto;
import com.kanak.ims.dto.SignUpDTO;
import com.kanak.ims.model.Role;
import com.kanak.ims.model.User;
import com.kanak.ims.repository.RoleRepository;
import com.kanak.ims.repository.UserRepository;
import com.kanak.ims.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginDto loginDto){
        String token=authService.login(loginDto);
        JWTAuthResponse jwtAuthResponse=new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpDTO signUpDTO) {
        if (userRepository.existsByEmail(signUpDTO.getEmail()) || userRepository.existsByUsername(signUpDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();//403 return
        }
        User user = new User();
        Role role = new Role();

        role.setId(2L);
        role.setName("USER");

        user.setId(null);
        user.setRole(role);
        user.setEmail(signUpDTO.getEmail());
        user.setUsername(signUpDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(signUpDTO.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("User Added successfully", HttpStatus.OK);
    }
}
