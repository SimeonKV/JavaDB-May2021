package xml.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xml.exercise.model.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByPriceBetweenAndBuyerIsNull(BigDecimal priceLower, BigDecimal priceUpper);
}
