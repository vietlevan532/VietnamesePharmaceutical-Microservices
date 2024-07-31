package com.zezanziet.pharmaceutical.vn.ms.user_service;

import com.zezanziet.pharmaceutical.vn.ms.user_service.models.Role;
import com.zezanziet.pharmaceutical.vn.ms.user_service.models.User;
import com.zezanziet.pharmaceutical.vn.ms.user_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@RequiredArgsConstructor
public class UserServiceApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        User adminAccount = userRepository.findByRole(Role.ROLE_ADMIN).orElse(null);
        if (adminAccount == null) {
            var admin = User.builder()
                    .firstName("PharmaceuticalVN")
                    .lastName("Admin")
                    .username("admin-pharmaceutical-vn")
                    .phoneNumber("19001221")
                    .email("admin@pharmaceuticalvn.com")
                    .password(passwordEncoder.encode("admin"))
                    .avatarUrl("https://thumbs.dreamstime.com/b/default-avatar-profile-icon-vector-social-media-user-image-182145777.jpg")
                    .birthOfDate("05-03-2002")
                    .gender("male")
                    .position("Founder")
                    .certificateNumber("Unavailable")
                    .address("")
                    .active(true)
                    .status("active")
                    .shop(null)
                    .role(Role.ROLE_ADMIN)
                    .createdBy("SYSTEM")
                    .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("H:mm dd/MM/yyyy")))
                    .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("H:mm dd/MM/yyyy")))
                    .build();
            userRepository.save(admin);
        }
    }
}
