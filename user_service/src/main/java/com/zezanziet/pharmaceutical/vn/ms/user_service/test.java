package com.zezanziet.pharmaceutical.vn.ms.user_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/userService")
public class test {
    @GetMapping("/test")
    public ResponseEntity<String> testFirstEndpoint() {
        return new ResponseEntity<>("Hello world!", HttpStatus.OK);
    }
}
