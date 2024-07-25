package com.zezanziet.pharmaceutical.vn.ms.user_service.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service/api/v1/test")
public class TestController {

    @GetMapping("/")
    public ResponseEntity<String> test() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok().body(username);
    }

}
