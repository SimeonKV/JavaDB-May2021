package com.example.advquerying.services;

import com.example.advquerying.entities.Size;

import java.math.BigDecimal;
import java.util.Set;

public interface ShampooService {

    void printAllBySizeOrderedByIdInAsc(Size size);

    void printAllBySizeOrLabelOrderByPriceAsc(Size size,Long labelId);

    void printALLByPriceGreaterThan(BigDecimal price);

    long countShampoosByPriceLessThan(BigDecimal price);

    void printShampoosContainingGivenIngredients(Set<String> ingredientsName);

    void printAllShampooBrandsWithIngredientsNumberLessThan(int numOfIngredientsInShampoo);
}
