package com.example.advquerying.services.impls;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import com.example.advquerying.services.ShampooService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShampooServiceImpl implements ShampooService {
    private final ShampooRepository shampooRepository;


    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }


    @Override
    public void printAllBySizeOrderedByIdInAsc(Size size) {
         this.shampooRepository.getAllShampoosBySizeProvidedAndThenOrderById(size)
                .stream().forEach(shampoo -> System.out.println(shampoo.toString()));
    }

    @Override
    public void printAllBySizeOrLabelOrderByPriceAsc(Size size, Long labelId) {
        this.shampooRepository.getAllShampoosBySizeOrLabelIdOrderByPrice(size,labelId)
                .stream()
                .forEach(shampoo -> System.out.println(shampoo.toString()));
    }

    @Override
    public void printALLByPriceGreaterThan(BigDecimal price) {
        this.shampooRepository.getAllShampoosByPriceGreaterThan(price)
        .stream().forEach(shampoo -> System.out.println(shampoo.toString()));
    }

    @Override
    public long countShampoosByPriceLessThan(BigDecimal price) {
        return this.shampooRepository.getAllShampoosByPriceLowerThan(price)
                .stream()
                .count();
    }

    @Override
    public void printShampoosContainingGivenIngredients(Set<String> ingredientsName) {
        Set<String> brandNames = new HashSet<>();

        this.shampooRepository.getAllShampoosBrandNameContainingProvidedIngredients(ingredientsName)
        .stream().forEach(shampoo -> brandNames.add(shampoo.getBrand()));

        brandNames.stream().forEach(System.out::println);

    }

    @Override
    public void printAllShampooBrandsWithIngredientsNumberLessThan(int numOfIngredientsInShampoo) {
        this.shampooRepository.getAllShampoosWithIngredientsCountLessThan(numOfIngredientsInShampoo)
                .forEach(shampoo -> System.out.println(shampoo.getBrand()));
    }


}
