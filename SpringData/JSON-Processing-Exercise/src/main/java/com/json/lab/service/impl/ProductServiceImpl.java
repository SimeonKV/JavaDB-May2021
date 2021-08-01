package com.json.lab.service.impl;

import com.google.gson.Gson;
import com.json.lab.constants.GlobalConstants;
import com.json.lab.model.dto.ProductNameAndPriceDto;
import com.json.lab.model.dto.ProductSeedDto;
import com.json.lab.model.entity.Product;
import com.json.lab.repository.ProductRepository;
import com.json.lab.service.CategoryService;
import com.json.lab.service.ProductService;
import com.json.lab.service.UserService;
import com.json.lab.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String PRODUCTS_FILE = "products.json";
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson, ProductRepository productRepository, UserService userService, CategoryService categoryService) {
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedProducts() throws IOException {
        if(this.productRepository.count() > 0 ){
            return;
        }

        String productContent =
                Files.readString(Paths.get(GlobalConstants.RESOURCES_FILE_PATH + PRODUCTS_FILE));

        ProductSeedDto[] productSeedDtos =
                gson.fromJson(productContent,ProductSeedDto[].class);

        Arrays.stream(productSeedDtos)
                .filter(productEntityToBe -> this.validationUtil.isValid(productEntityToBe))
                .map(productEntityToBe -> {
                    Product product = modelMapper.map(productEntityToBe,Product.class);
                    product.setSeller(this.userService.findRandomUser());

                    if(product.getPrice().compareTo(BigDecimal.valueOf(900L)) > 0){
                        product.setBuyer(this.userService.findRandomUser());
                    }

                    product.setCategories(this.categoryService.findRandomCategories());
                    return product;
                })
                .forEach(product -> this.productRepository.save(product));


    }

    @Override
    public List<ProductNameAndPriceDto> findAllProductsInRange(BigDecimal lowerBound, BigDecimal upperBound) {
        List<ProductNameAndPriceDto> productNameAndPriceDtos =
                this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(BigDecimal.valueOf(500), BigDecimal.valueOf(1000))
                .stream()
                .map(entity -> {
                    ProductNameAndPriceDto transformedEntity = modelMapper.map(entity,ProductNameAndPriceDto.class);
                    String fullName = entity.getSeller().getFirstName() + " " + entity.getSeller().getLastName();
                    transformedEntity.setSeller(fullName);

                    return transformedEntity;
                }).collect(Collectors.toList());

        return productNameAndPriceDtos;
    }
}
