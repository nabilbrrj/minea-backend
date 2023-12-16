package com.ecommerce.services.metier;

import com.ecommerce.dtos.ProductDTO;
import com.ecommerce.exceptions.ProductAlreadyExistException;

public interface IProductService {
    ProductDTO addProduct(ProductDTO dto) throws ProductAlreadyExistException;
}
