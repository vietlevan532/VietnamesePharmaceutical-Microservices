package com.zezanziet.pharmaceutical.vn.ms.user_service.controllers;

import com.zezanziet.pharmaceutical.vn.ms.user_service.auth.AuthenticationRequest;
import com.zezanziet.pharmaceutical.vn.ms.user_service.auth.AuthenticationResponse;
import com.zezanziet.pharmaceutical.vn.ms.user_service.auth.AuthenticationService;
import com.zezanziet.pharmaceutical.vn.ms.user_service.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
