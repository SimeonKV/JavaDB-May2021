package com.example.advquerying.services;


import com.example.advquerying.entities.Ingredient;

import java.math.BigDecimal;
import java.util.Set;

public interface IngredientService {

    void printAllIngredientsStartingWithLetter(String singleLetter);

    void deleteIngredientByName(String ingredientName);

    void upIngredientsPriceWith10Percent();

    void setNewPriceToIngredients(BigDecimal price, Set<String> ingredientsName);
}
