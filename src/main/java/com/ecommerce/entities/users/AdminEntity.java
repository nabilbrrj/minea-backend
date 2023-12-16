package com.ecommerce.entities.users;

import com.ecommerce.entities.users.enums.RolesEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@Entity

public class AdminEntity extends  UserEntity{
    private String login;

    AdminEntity(Long id, String firstName, String lastName, LocalDate registrationDate, String password, String email, Collection<RolesEnum> roles, boolean isActive) {
        super(id, firstName, lastName, registrationDate, password, email, roles, isActive);
    }
}
