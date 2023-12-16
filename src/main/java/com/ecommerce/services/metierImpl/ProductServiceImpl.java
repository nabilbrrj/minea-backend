package com.ecommerce.services.metierImpl;

import com.ecommerce.dtos.ProductDTO;
import com.ecommerce.entities.app.ProductEntity;
import com.ecommerce.exceptions.ProductAlreadyExistException;
import com.ecommerce.mappers.ApplicationMappersImpl;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.services.metier.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final ApplicationMappersImpl mappers;
    @Override
    public ProductDTO addProduct(ProductDTO dto) throws ProductAlreadyExistException {
        boolean isProductExist = productRepository.findByDestinationLink(dto.getDestinationLink()).isPresent();
        //productRepository.findAll();
        if(isProductExist)
            throw new ProductAlreadyExistException("Product already exist");
        ProductEntity product = mappers.fromProductDTO(dto);
        productRepository.save(product);
        return dto;
    }

    public ProductDTO getProductByProductSource (String productSource,ProductDTO dto) throws ProductAlreadyExistException {
        String productName =  productSource;
        boolean isProductExist = productRepository.findByDestinationLink(dto.getDestinationLink()).isPresent();
        //productRepository.findAll();
        if(isProductExist)
            throw new ProductAlreadyExistException("Product already exist");
        ProductEntity product = mappers.fromProductDTO(dto);
        productRepository.save(product);
        return dto;
    }
}
