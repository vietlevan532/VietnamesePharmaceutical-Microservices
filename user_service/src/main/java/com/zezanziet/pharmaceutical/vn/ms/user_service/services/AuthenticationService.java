package com.zezanziet.pharmaceutical.vn.ms.user_service.services;

import com.zezanziet.pharmaceutical.vn.ms.user_service.configurations.jwt.JwtService;
import com.zezanziet.pharmaceutical.vn.ms.user_service.dtos.AuthenticationRequest;
import com.zezanziet.pharmaceutical.vn.ms.user_service.dtos.AuthenticationResponse;
import com.zezanziet.pharmaceutical.vn.ms.user_service.dtos.RegisterRequest;
import com.zezanziet.pharmaceutical.vn.ms.user_service.models.Role;
import com.zezanziet.pharmaceutical.vn.ms.user_service.models.User;
import com.zezanziet.pharmaceutical.vn.ms.user_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    // todo: Sign-Up
    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getEmail().substring(0, request.getEmail().indexOf("@"))).isPresent()) {
            return AuthenticationResponse.builder()
                    .token("Email " + request.getEmail() + " has been used, please enter another email!")
                    .build();
        } else if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return AuthenticationResponse.builder()
                    .token("Email '" + request.getEmail() + "' has been used, please enter another email!")
                    .build();
        } else if (userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            return AuthenticationResponse.builder()
                    .token("Phone number '" + request.getPhoneNumber() + "' has been used, please enter another phone number!")
                    .build();
        } else {
            var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .username(request.getEmail().substring(0, request.getEmail().indexOf("@")))
                    .phoneNumber(request.getPhoneNumber())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .avatarUrl("https://thumbs.dreamstime.com/b/default-avatar-profile-icon-vector-social-media-user-image-182145777.jpg")
                    .birthOfDate(request.getBirthOfDate())
                    .gender(request.getGender())
                    .position(request.getPosition())
                    .certificateNumber(request.getCertificateNumber())
                    .address("")
                    .active(true)
                    .status("active")
                    .shop(null)
                    .role(Role.ROLE_USER)
                    .createdBy("")
                    .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("H:mm dd/MM/yyyy")))
                    .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("H:mm dd/MM/yyyy")))
                    .build();
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user, "", false);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
    }

    // todo: Sign-In
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getLogin())
                .orElseGet(() -> userRepository.findByPhoneNumber(request.getLogin())
                        .orElseGet(() -> userRepository.findByUsername(request.getLogin())
                                .orElseThrow(() -> new IllegalArgumentException("Invalid login information or password!"))));
        var jwtAccessToken = jwtService.generateToken(user , request.getLogin(), request.isRememberMe());
        return AuthenticationResponse.builder()
                .token(jwtAccessToken)
                .build();
    }

    // todo: Sign-Out
    public AuthenticationResponse signOut (AuthenticationRequest request) {
        return AuthenticationResponse.builder().build();
    }
}
