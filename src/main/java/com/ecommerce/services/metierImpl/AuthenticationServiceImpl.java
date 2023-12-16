package com.ecommerce.services.metierImpl;

import com.ecommerce.config.JwtTokenProvider;
import com.ecommerce.dtos.LoginDTO;
import com.ecommerce.dtos.UserDTO;
import com.ecommerce.entities.users.UserEntity;
import com.ecommerce.exceptions.UserAlreadyExistException;
import com.ecommerce.mappers.IApplicationMappers;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.services.metier.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IApplicationMappers mappers;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public Map<Object, Object> loginAndGenerateToken(LoginDTO data) {
        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        String token = jwtTokenProvider.createToken(authentication);
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userEntity.getEmail());
        model.put("token", token);
        return model;

    }

    @Override
    public void updateClientAccount(String identityCode, String phoneNumber, String password, String email) {

    }

    @Override
    public UserDTO signup(UserDTO signupDTO) throws UserAlreadyExistException {
        boolean isUserExist = userRepository.findByEmail(signupDTO.getEmail()).isPresent();
        if(isUserExist)
            throw new UserAlreadyExistException("EMAIL ALREADY EXIST ");
            UserEntity user = mappers.fromUserDTO(signupDTO);
            userRepository.save(user);
         return signupDTO;

    }

}
