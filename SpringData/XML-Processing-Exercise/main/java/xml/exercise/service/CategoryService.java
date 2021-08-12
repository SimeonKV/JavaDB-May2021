package xml.exercise.service;

import xml.exercise.model.dto.CategorySeedDto;
import xml.exercise.model.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories(List<CategorySeedDto> categories);

    long getEntityCount();

    Set<Category> getRandomCategories();
}
