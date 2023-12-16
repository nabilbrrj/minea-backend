package com.ecommerce.entities.users;

import com.ecommerce.entities.users.enums.RolesEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@Builder
@Getter
@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "registration_date")
    private LocalDate registrationDate;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;

    @ElementCollection(targetClass = RolesEnum.class)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Collection<RolesEnum> roles;
    private boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(role -> (GrantedAuthority) role::name).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
