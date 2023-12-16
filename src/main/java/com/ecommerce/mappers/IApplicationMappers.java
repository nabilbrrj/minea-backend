package com.ecommerce.mappers;

import com.ecommerce.dtos.ProductDTO;
import com.ecommerce.dtos.UserDTO;
import com.ecommerce.entities.app.ProductEntity;
import com.ecommerce.entities.users.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface IApplicationMappers {
    UserEntity fromUserDTO (UserDTO dto);
    ProductEntity fromProductDTO(ProductDTO dto);
}
