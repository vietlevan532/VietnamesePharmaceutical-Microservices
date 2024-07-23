package com.zezanziet.pharmaceutical.vn.ms.user_service.services;

import com.zezanziet.pharmaceutical.vn.ms.user_service.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
