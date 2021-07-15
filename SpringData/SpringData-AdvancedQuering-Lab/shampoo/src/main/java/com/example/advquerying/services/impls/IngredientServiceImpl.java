package com.example.advquerying.services.impls;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import com.example.advquerying.services.IngredientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void printAllIngredientsStartingWithLetter(String character) {
        this.ingredientRepository.getAllIngredientByNamesStartingWithTheLetter(character)
                .stream().forEach(ingredient -> System.out.println(ingredient.getName()));
    }

    @Override
    @Transactional
    public void deleteIngredientByName(String ingredientName) {
        int recordDeleted = this.ingredientRepository.deleteIngredientByName(ingredientName);
        System.out.println(String.format("%d ingredients deleted",recordDeleted));
    }

    @Override
    @Transactional
    public void upIngredientsPriceWith10Percent() {

        this.ingredientRepository.upIngredientsPriceBy10Percent();

    }

    @Override
    @Transactional
    public void setNewPriceToIngredients(BigDecimal price,Set<String> ingredientsName) {
        int updatePricesRecord = this.ingredientRepository.updateIngredientPriceByGivenNames(price,ingredientsName);
        System.out.println(updatePricesRecord + " records updated!");

    }
}
