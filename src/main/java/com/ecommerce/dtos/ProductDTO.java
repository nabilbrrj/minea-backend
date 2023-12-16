package com.ecommerce.dtos;

import com.ecommerce.entities.app.enums.ProductSourceEnum;
import lombok.Getter;

@Getter
public class ProductDTO {
    private String video;
    private String description;
    private String destinationLink;
    private String audienceCountry;
    private String audienceAge;
    private String audienceGender;
    private ProductSourceEnum source;
}