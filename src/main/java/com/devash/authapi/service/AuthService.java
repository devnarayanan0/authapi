package com.devash.authapi.service;


import com.devash.authapi.dto.AuthDTO;
import com.devash.authapi.dto.LoginDTO;
import com.devash.authapi.dto.RegisterDTO;
import com.devash.authapi.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    @Autowired
    JWTService jwtService;

    public AuthDTO login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        //
        if(username == null || password == null) {
            return new AuthDTO(
                    null,"(Username or Password)?Val==NULL", LocalDateTime.now().toString()
            );
        }
        if(username.equals("devash")&& password.equals("devash")) {
            String tok=jwtService.generateToken(username);
            return new AuthDTO(
                    tok,"Login Successful",LocalDateTime.now().toString()
            );
        }
        //
        return new AuthDTO(
                null,"(Login Failed)? Invalid Credentials",LocalDateTime.now().toString()
        );
    }

    public AuthDTO register(RegisterDTO registerDTO) {
        String username = registerDTO.getUsername();
        String password = registerDTO.getPassword();
        //
        if(username == null || password == null) {
            return new AuthDTO(
                    null, "(Username or Password)?Val==NULL", LocalDateTime.now().toString()
            );
        }
        String token = jwtService.generateToken(username);
        return new AuthDTO(
                token,"Register Successful",LocalDateTime.now().toString()
        );
    }
}
