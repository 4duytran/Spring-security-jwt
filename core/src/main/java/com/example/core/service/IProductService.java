package com.example.core.service;

import com.example.core.entities.Products;

public interface IProductService {

    Products createProduct(Products product);
    Products FindProductById(Products product);

}
