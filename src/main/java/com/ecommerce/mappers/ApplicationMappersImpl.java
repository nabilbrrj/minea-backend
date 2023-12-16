package com.ecommerce.mappers;

import com.ecommerce.dtos.ProductDTO;
import com.ecommerce.dtos.UserDTO;
import com.ecommerce.entities.app.ProductEntity;
import com.ecommerce.entities.users.UserEntity;
import com.ecommerce.entities.users.enums.RolesEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
@Service
@RequiredArgsConstructor
public class ApplicationMappersImpl implements IApplicationMappers{
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserEntity fromUserDTO(UserDTO dto) {
        return UserEntity.builder()
                .id(null)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .isActive(Boolean.TRUE)
                .password(passwordEncoder.encode(dto.getPassword()))
                .registrationDate(LocalDate.now())
                .roles(Arrays.asList(RolesEnum.ROLE_USER))
                .build();
    }

    @Override
    public ProductEntity fromProductDTO(ProductDTO dto) {
        return ProductEntity.builder()
                .id(null)
                .video(dto.getVideo())
                .description(dto.getDescription())
                .destinationLink(dto.getDestinationLink())
                .status(Boolean.TRUE)
                .audienceCountry(dto.getAudienceCountry())
                .audienceAge(dto.getAudienceAge())
                .audienceGender(dto.getAudienceGender())
                .createdAt(LocalDateTime.now())
                .source(dto.getSource())
                .build();
    }
}
