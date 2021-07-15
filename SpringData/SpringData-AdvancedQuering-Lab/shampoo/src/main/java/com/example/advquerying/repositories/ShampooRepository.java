package com.example.advquerying.repositories;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooRepository extends JpaRepository<Shampoo,Long> {

    List<Shampoo> findAllBySizeOrderByIdAsc(Size size);


    @Query("SELECT s FROM Shampoo s WHERE s.size = :providedSize ORDER BY s.id ASC")
    List<Shampoo> getAllShampoosBySizeProvidedAndThenOrderById(@Param("providedSize")Size size);


    List<Shampoo> findAllBySizeOrLabel_IdOrderByPrice(Size size, Long labelId);


    @Query("SELECT s FROM Shampoo s WHERE s.size = :providedSize" +
            " OR s.label.id = :providedLabelId ORDER BY s.price")
    List<Shampoo> getAllShampoosBySizeOrLabelIdOrderByPrice(@Param("providedSize")Size size
            ,@Param("providedLabelId") Long id);


    List<Shampoo> findALLByPriceGreaterThanOrderByPriceDesc(BigDecimal price);


    @Query("SELECT s FROM Shampoo s WHERE s.price > :providedPrice ORDER BY s.price DESC")
    List<Shampoo> getAllShampoosByPriceGreaterThan(@Param("providedPrice") BigDecimal price);

    List<Shampoo> findAllByPriceLessThan(BigDecimal price);

    @Query("SELECT s FROM Shampoo s WHERE s.price < :providedPrice")
    List<Shampoo> getAllShampoosByPriceLowerThan(@Param("providedPrice") BigDecimal price);

    Set<Shampoo> findAllByIngredients_NameIn(Set<String> ingredientsName);

    @Query("SELECT s FROM Shampoo s JOIN s.ingredients i WHERE i.name IN :providedNames")
    Set<Shampoo> getAllShampoosBrandNameContainingProvidedIngredients(@Param("providedNames") Set<String> ingredientsName);

    @Query("SELECT s FROM Shampoo s WHERE s.ingredients.size < :providedNumber")
    Set<Shampoo> getAllShampoosWithIngredientsCountLessThan(@Param("providedNumber") int numberOfIngredients);



}
