package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    List<Ingredient> findAllByNameStartsWith(Character character);


    @Query("SELECT i FROM Ingredient i WHERE substring(i.name,1,1) = :providedChar ")
    List<Ingredient> getAllIngredientByNamesStartingWithTheLetter(@Param("providedChar") String providedChar);

    @Query("DELETE  FROM Ingredient i WHERE i.name = :providedName")
    @Modifying
    int deleteIngredientByName(@Param("providedName") String name);

    @Query("UPDATE Ingredient i SET i.price = i.price * 1.10 ")
    @Modifying
    int upIngredientsPriceBy10Percent();

    @Query("UPDATE Ingredient i SET i.price = :providedPrice " +
            "WHERE i.name IN(:providedNames)")
    @Modifying
    int updateIngredientPriceByGivenNames(@Param("providedPrice")BigDecimal price,
                                          @Param("providedNames") Set<String> names);
}
