package xml.exercise.service;

import xml.exercise.model.dto.ProductSeedDto;
import xml.exercise.model.dto.ProductViewRootDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedData(List<ProductSeedDto> products);

    long getEntityCount();

    ProductViewRootDto findProductInRangeWithNoBuyer(BigDecimal lowerBound,BigDecimal upperBound);
}
