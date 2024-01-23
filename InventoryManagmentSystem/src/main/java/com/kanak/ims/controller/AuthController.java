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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger LOGGER= LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginDto loginDto){
        String token=authService.login(loginDto);
        JWTAuthResponse jwtAuthResponse=new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpDTO signUpDTO) {
        if (userRepository.existsByEmail(signUpDTO.getEmail())) {
            LOGGER.error("User already exist with given email, try another email id");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();//403 return
        }
        if(userRepository.existsByUsername(signUpDTO.getUsername())) {
            LOGGER.error("User already exist with given username, try another username");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();//403 return
            }


        User user = new User();
        Role role=new Role("USER");
        user.setRole(role);
        System.out.println(user.getRole());
        user.setEmail(signUpDTO.getEmail());
        user.setUsername(signUpDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(signUpDTO.getPassword()));
        try {
            userRepository.save(user);
            LOGGER.info("User saved into database successfully...");
            return new ResponseEntity<>("User Added successfully", HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error while adding user into database {}",e.getStackTrace());
            return new ResponseEntity<>("Error while adding user",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
