package com.example.advquerying;

import com.example.advquerying.entities.Size;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Component
public class ConsoleRunner implements CommandLineRunner {
    private ShampooService shampooService;
    private IngredientService ingredientService;

    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        // taskOne();
        // taskTwo();
        // taskThree();
        //taskFour();
        //taskFix();
        //taskSeven();
        //taskEight();
        //taskNine();
        //taskTen();
        taskEleven();
    }

    private void taskEleven() {
        Set<String> ingredientsName = new HashSet<>();
        ingredientsName.add("Nettle");
        ingredientsName.add("Macadamia Oil");

        this.ingredientService.setNewPriceToIngredients(new BigDecimal("12"),ingredientsName);


    }

    private void taskTen() {
        this.ingredientService.upIngredientsPriceWith10Percent();
    }

    private void taskNine() {
        this.ingredientService.deleteIngredientByName("Apple");
    }

    private void taskEight() {
        this.shampooService.printAllShampooBrandsWithIngredientsNumberLessThan(2);

    }

    private void taskSeven() {
        Set<String> ingredientsName = new HashSet<>();
        ingredientsName.add("Berry");
        ingredientsName.add("Mineral-Collagen");
        this.shampooService.printShampoosContainingGivenIngredients(ingredientsName);
    }

    private void taskFix() {
        System.out.println(this.shampooService.countShampoosByPriceLessThan(new BigDecimal("8.50")));
    }

    private void taskFour() {
        this.ingredientService.printAllIngredientsStartingWithLetter("M");
    }

    private void taskThree() {
        this.shampooService.printALLByPriceGreaterThan(new BigDecimal(5));
    }

    private void taskTwo() {
        this.shampooService.printAllBySizeOrLabelOrderByPriceAsc(Size.MEDIUM, 10l);
    }


    public void taskOne() {
        this.shampooService.printAllBySizeOrderedByIdInAsc(Size.MEDIUM);
    }
}
