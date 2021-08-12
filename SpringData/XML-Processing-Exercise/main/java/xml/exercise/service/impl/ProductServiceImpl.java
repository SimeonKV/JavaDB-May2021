package xml.exercise.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import xml.exercise.model.dto.ProductSeedDto;
import xml.exercise.model.dto.ProductViewRootDto;
import xml.exercise.model.dto.ProductWithSellerDto;
import xml.exercise.model.entity.Category;
import xml.exercise.model.entity.Product;
import xml.exercise.repository.ProductRepository;
import xml.exercise.service.CategoryService;
import xml.exercise.service.ProductService;
import xml.exercise.service.UserService;
import xml.exercise.util.ValidationUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, CategoryService categoryService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedData(List<ProductSeedDto> products) {
        products
                .stream()
                .filter(productDto -> this.validationUtil.isValid(productDto))
                .map(productDto -> {
                    Product product = modelMapper.map(productDto, Product.class);
                    product.setSeller(this.userService.getRandomUser());

                    if(product.getPrice().compareTo(new BigDecimal(700)) > 0) {
                        product.setBuyer(this.userService.getRandomUser());
                    }

                    Set<Category> categories = this.categoryService.getRandomCategories();
                    product.setCategories(categories);

                    return product;
                })
                .forEach(product -> this.productRepository.save(product));
    }

    @Override
    public long getEntityCount() {
        return this.productRepository.count();
    }

    @Override
    public ProductViewRootDto findProductInRangeWithNoBuyer(BigDecimal lowerBound, BigDecimal upperBound) {
        List<ProductWithSellerDto> productWithSellerDtos =
                this.productRepository.findAllByPriceBetweenAndBuyerIsNull(lowerBound,upperBound)
                .stream()
                .map(product -> {
                 ProductWithSellerDto productWithSeller = modelMapper.map(product, ProductWithSellerDto.class);
                 productWithSeller.setSeller(String.format("%s %s",product.getSeller().getFirstName(),product.getSeller().getLastName()));

                 return  productWithSeller;

                }).collect(Collectors.toList());


        ProductViewRootDto productViewRootDto = new ProductViewRootDto();
        productViewRootDto.setProducts(productWithSellerDtos);

        return productViewRootDto;
    }




}
