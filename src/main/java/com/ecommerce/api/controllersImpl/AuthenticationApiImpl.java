package com.ecommerce.api.controllersImpl;

import com.ecommerce.api.controllers.IAuthenticationApi;
import com.ecommerce.dtos.LoginDTO;
import com.ecommerce.dtos.UserDTO;
import com.ecommerce.exceptions.UserAlreadyExistException;
import com.ecommerce.exceptions.UserNotExistException;
import com.ecommerce.services.metier.IAuthenticationService;
import com.ecommerce.services.metierImpl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AuthenticationApiImpl implements IAuthenticationApi {
    private final IAuthenticationService authenticationService;
    @Override
    public ResponseEntity<?> signup(UserDTO dto) {
        try{
            UserDTO userDTO = authenticationService.signup(dto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userDTO);
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(e.getHttpCode())
                    .body(e.getMessage());
        }catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
                    .body("unhandled error : "+exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> login(LoginDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.loginAndGenerateToken(dto));
    }

    @Override
    public ResponseEntity<?> fetchUserInfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Map<String, Object> userInfo = Map.of(
                "principal", authentication.getPrincipal(),
                "login", authentication.getName(),
                "credentials", authentication.getCredentials(),
                "authorities", authentication.getAuthorities()
        );
        return ResponseEntity.ok(userInfo);    }

}
