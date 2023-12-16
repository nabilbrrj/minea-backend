package com.ecommerce.api.controllersImpl;

import com.ecommerce.api.controllers.IAdminProductApi;
import com.ecommerce.dtos.ProductDTO;
import com.ecommerce.exceptions.ProductAlreadyExistException;
import com.ecommerce.services.metier.IAuthenticationService;
import com.ecommerce.services.metier.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminProductImpl implements IAdminProductApi {
   private final IProductService productService;
    @Override
    public ResponseEntity<?> addProduct(ProductDTO dto) {
        try{
            ProductDTO productDTO = productService.addProduct(dto);
            return ResponseEntity.status(HttpStatus.OK).body(productDTO);
        } catch (ProductAlreadyExistException e) {
            return ResponseEntity.status(e.getHttpCode()).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long id) {
        return null;
    }
    public ResponseEntity<?> updateProduct(Long id){return null;}
}
