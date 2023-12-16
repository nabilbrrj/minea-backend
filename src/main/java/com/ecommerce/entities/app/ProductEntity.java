package com.ecommerce.entities.app;

import com.ecommerce.entities.app.enums.ProductSourceEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String video;
    private String description ;
    @Column(name = "destination_link")
    private String destinationLink;
    private boolean status;
    @Column(name = "audience_country")
    private String audienceCountry;
    @Column(name = "audience_age")
    private String audienceAge;
    @Column(name = "audience_gender")
    private String audienceGender;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private ProductSourceEnum source;
}
