package com.ecommerce.api.controllers;

import com.ecommerce.dtos.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("admin")
public interface IAdminProductApi {
    @PostMapping("add_product")
    ResponseEntity<?> addProduct(@RequestBody ProductDTO dto);
    @PostMapping("delete_product/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Long id);

}
