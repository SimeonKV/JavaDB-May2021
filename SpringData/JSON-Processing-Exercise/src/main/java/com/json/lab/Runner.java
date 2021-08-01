package com.json.lab;


import com.google.gson.Gson;
import com.json.lab.model.dto.ProductNameAndPriceDto;
import com.json.lab.model.dto.UserSoldDto;
import com.json.lab.service.CategoryService;
import com.json.lab.service.ProductService;
import com.json.lab.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {
    private static final String OUTPUT_FILE_PATH = "src\\main\\resources\\files\\out\\";
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final Scanner scanner;
    private final Gson gson;

    public Runner(CategoryService categoryService, UserService userService, ProductService productService, Gson gson) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.gson = gson;
        this.scanner = new Scanner(System.in);
    }


    @Override
    public void run(String... args) throws Exception {
       // seedData();
       // productsInRange();
        soldProducts();
    }

    private void soldProducts() throws IOException {

        List<UserSoldDto> userSoldDtos =
        this.userService
                .findAllUsersWithMoreThanOneSoldProduct();

        String userSoldDtosJson = gson.toJson(userSoldDtos);
        String entireFilePath = OUTPUT_FILE_PATH + "soldProducts.json";

        writeToFile(entireFilePath,userSoldDtosJson);

        System.out.println();
    }

    private void productsInRange() throws IOException {
        List<ProductNameAndPriceDto> productsDtos =
                this.productService.findAllProductsInRange(BigDecimal.valueOf(500),BigDecimal.valueOf(1000));


        String fileName = "productsInRange.json";
        String entireFilePath = OUTPUT_FILE_PATH + fileName;

        String productsDtosJson = gson.toJson(productsDtos);
        writeToFile(entireFilePath,productsDtosJson);

        System.out.println();

    }

    private void writeToFile(String filePath,String productsDtosJson) throws IOException {

        Files.write(Paths.get(filePath), Collections.singleton(productsDtosJson));

    }

    private void seedData() throws IOException {
    this.categoryService.seedCategories();
    this.userService.seedUsers();
    this.productService.seedProducts();


    }
}
