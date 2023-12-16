package com.ecommerce.api.controllers;

import com.ecommerce.dtos.LoginDTO;
import com.ecommerce.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("auth")
public interface IAuthenticationApi {
    @PostMapping ("signup")
    ResponseEntity<?> signup(@RequestBody UserDTO dto);
    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginDTO dto);
    @GetMapping("get_user_info")
    ResponseEntity<?> fetchUserInfo();
}
