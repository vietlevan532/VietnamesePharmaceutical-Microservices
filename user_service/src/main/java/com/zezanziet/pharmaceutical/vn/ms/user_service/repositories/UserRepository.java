package com.zezanziet.pharmaceutical.vn.ms.user_service.repositories;

import com.zezanziet.pharmaceutical.vn.ms.user_service.models.Role;
import com.zezanziet.pharmaceutical.vn.ms.user_service.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByUsername(String username);
    Optional<User> findByRole(Role role);
}
