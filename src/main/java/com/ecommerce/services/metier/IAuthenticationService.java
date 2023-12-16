package com.ecommerce.services.metier;

import com.ecommerce.dtos.LoginDTO;
import com.ecommerce.dtos.UserDTO;
import com.ecommerce.exceptions.UserAlreadyExistException;

import java.util.Map;

public interface IAuthenticationService {
    public Map<Object, Object>loginAndGenerateToken(LoginDTO data);
    public void updateClientAccount(String identityCode, String phoneNumber, String password, String email);
    public UserDTO signup(UserDTO signupDTO) throws UserAlreadyExistException;
}
