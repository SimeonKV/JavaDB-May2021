package xml.exercise;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xml.exercise.model.dto.*;
import xml.exercise.service.CategoryService;
import xml.exercise.service.ProductService;
import xml.exercise.service.UserService;
import xml.exercise.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {
    private final static String FOLDER_PATH = "src\\main\\resources\\file\\" ;
    private final static String OUTPUT_FOLDER_PATH = "src\\main\\resources\\file\\output\\";

    private final static String CATEGORIES_FILE_NAME = "categories.xml";
    private final static String USERS_FILE_NAME = "users.xml";
    private final static String PRODUCTS_FILE_NAME = "products.xml";

    private final static String PRODUCTS_IN_RANGE_FILE_NAME = "productsInRange.xml";
    private final static String USERS_WITH_SOLD_PRODUCTS = "usersWithSoldProducts.xml";

    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final XmlParser xmlParser;
    private final Scanner scanner;

    public Runner(CategoryService categoryService, UserService userService, ProductService productService, XmlParser xmlParser) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.xmlParser = xmlParser;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        productsInRange();

        usersWithSoldProducts();
    }

    private void usersWithSoldProducts() throws JAXBException, IOException {
        UserViewRootDto userViewRootDto =
                this.userService.findUsersWithMoreThanOneSoldProduct();

        xmlParser.writeToFile(OUTPUT_FOLDER_PATH + USERS_WITH_SOLD_PRODUCTS,userViewRootDto);

    }

    private void productsInRange() throws JAXBException, IOException {

        ProductViewRootDto productInRangeWithNoBuyer = this.productService.findProductInRangeWithNoBuyer(new BigDecimal(500), new BigDecimal(1000));
        this.xmlParser.writeToFile(OUTPUT_FOLDER_PATH + PRODUCTS_IN_RANGE_FILE_NAME,productInRangeWithNoBuyer);


    }

    private void seedData() throws JAXBException, FileNotFoundException {

       if(this.categoryService.getEntityCount() == 0) {
           CategorySeedRootDto categorySeedRootDto =
                   this.xmlParser
                           .fromFile(FOLDER_PATH + CATEGORIES_FILE_NAME, CategorySeedRootDto.class);

           this.categoryService.seedCategories(categorySeedRootDto.getCategories());

       }

       if(this.userService.getEntityCount() == 0) {
           UserSeedRootDto userSeedRootDto =
                   this.xmlParser.fromFile(FOLDER_PATH + USERS_FILE_NAME, UserSeedRootDto.class);

           this.userService.seedUsers(userSeedRootDto.getUsers());
       }

       if(this.productService.getEntityCount() == 0) {

           ProductSeedRootDto productSeedRootDto =
                   this.xmlParser.fromFile(FOLDER_PATH + PRODUCTS_FILE_NAME, ProductSeedRootDto.class);

           this.productService.seedData(productSeedRootDto.getProducts());
       }
        System.out.println();

    }
}
