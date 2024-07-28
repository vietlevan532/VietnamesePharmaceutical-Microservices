package com.zezanziet.pharmaceutical.vn.ms.user_service.controllers;

import com.zezanziet.pharmaceutical.vn.ms.user_service.models.User;
import com.zezanziet.pharmaceutical.vn.ms.user_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service/api/v1/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @GetMapping("/information")
    public ResponseEntity<User> getUserByUsername() {
        log.debug("REST request to get user by username from Security Context!");
        User user = userService.getUserByUsername(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found by authorities!"));
        CacheControl cacheControl = CacheControl
                .maxAge(0, TimeUnit.SECONDS)
                .cachePrivate()
                .mustRevalidate();
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .cacheControl(cacheControl)
                .body(user);
    }
}
