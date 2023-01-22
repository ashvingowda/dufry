package com.dufry.dufry.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long skuId;

    @NotEmpty(message = "Please enter name")
    @NotBlank(message = "Please enter name")
    private String name;

    @NotNull(message = "Please enter price")
    private long price;

    private LocalDateTime created;



}
