package com.seanans.HibernateCityApi.Controllers;

import com.seanans.HibernateCityApi.Controllers.Authentication.AuthenticationRequest;
import com.seanans.HibernateCityApi.Controllers.Authentication.AuthenticationResponse;
import com.seanans.HibernateCityApi.Controllers.Authentication.RegisterRequest;
import com.seanans.HibernateCityApi.DAOServices.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        log.info("New registration: email: {} , password: {}", request.getEmail(), request.getPassword());
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request) {
        log.info("New authentication: email: {} , password: {}", request.getEmail(), request.getPassword());
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}

