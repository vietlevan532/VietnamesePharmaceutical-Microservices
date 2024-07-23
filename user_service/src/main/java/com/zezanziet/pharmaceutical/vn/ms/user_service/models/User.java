package com.zezanziet.pharmaceutical.vn.ms.user_service.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    private ObjectId userId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Indexed(unique = true)
    private String username;

    @NotNull
    @Indexed(unique = true)
    @Pattern(regexp = "^[0-9]{10}$")
    private String phoneNumber;

    @NotNull
    @Email
    @Indexed(unique = true)
    private String email;

    @NotNull
    private String password;

    private String avatarUrl;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String birthOfDate;

    private String gender;

    private String position;

    @NotNull
    @Indexed(unique = true)
    private String certificateNumber;

    private String address;

    private Boolean active; // "is_online" or "is_offline"

    private String status; // "locked", "active", "delete"

    @DocumentReference
    private Shop shop;

    private Role role;

    @Field("created_by")
    private String createdBy;

    @CreatedDate
    @Field("created_at")
    private String createdAt;

    @LastModifiedDate
    @Field("updated_at")
    private String updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        //return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        //return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        //return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return true;
        //return UserDetails.super.isEnabled();
    }
}
