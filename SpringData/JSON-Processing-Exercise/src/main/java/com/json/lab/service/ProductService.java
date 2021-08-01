package com.json.lab.service;

import com.json.lab.model.dto.ProductNameAndPriceDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts() throws IOException;

    List<ProductNameAndPriceDto> findAllProductsInRange(BigDecimal lowerBound, BigDecimal upperBound);
}
