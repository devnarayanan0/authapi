package com.devash.authapi.service;

import com.devash.authapi.dto.UserProfileDTO;
import com.devash.authapi.entity.User;
import com.devash.authapi.dto.AuthDTO;
import com.devash.authapi.dto.LoginDTO;
import com.devash.authapi.dto.RegisterDTO;
import com.devash.authapi.repository.UserRepository;
import com.devash.authapi.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    @Autowired
    JWTService jwtService;
    @Autowired
    UserRepository userRepository;

    public AuthDTO login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        //
        if (username == null || password == null) {
            return new AuthDTO(
                    null,
                    "Username or Password cannot be null",
                    LocalDateTime.now().toString()
            );
        }

        User user = userRepository.findByUsername(username);

        if (user == null) {
            return new AuthDTO(
                    null,
                    "Username not found",
                    LocalDateTime.now().toString()
            );
        }

        if (!password.equals(user.getPassword())) {
            return new AuthDTO(
                    null,
                    "Incorrect password",
                    LocalDateTime.now().toString()
            );
        }

        String tok = jwtService.generateToken(username);

        return new AuthDTO(
                tok,
                "Login Successful",
                LocalDateTime.now().toString()
        );
    }

    public AuthDTO register(RegisterDTO registerDTO) {
        String username = registerDTO.getUsername();
        String password = registerDTO.getPassword();
        //
        Boolean dev = userRepository.existsByUsername(username);
        if(dev) {
            return new AuthDTO(
                    null, "UserName Exist!", LocalDateTime.now().toString()
            );
        }else{
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            //
            userRepository.save(user);
            String token = jwtService.generateToken(username);
            return new AuthDTO(
                    token,
                    "Register Successful",
                    LocalDateTime.now().toString()
            );
        }
    }

    public UserProfileDTO getCurrentUser(String username){
        return new UserProfileDTO(
                username
        );
    }
}
