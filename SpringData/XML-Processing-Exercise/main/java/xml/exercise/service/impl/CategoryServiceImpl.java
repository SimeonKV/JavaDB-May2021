package xml.exercise.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import xml.exercise.model.dto.CategorySeedDto;
import xml.exercise.model.entity.Category;
import xml.exercise.repository.CategoryRepository;
import xml.exercise.service.CategoryService;
import xml.exercise.util.ValidationUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;

    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepository categoryRepository, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategories(List<CategorySeedDto> categories) {
        categories
                .stream()
                .filter(category -> this.validationUtil.isValid(category))
                .map(category -> modelMapper.map(category, Category.class))
                .forEach(category -> this.categoryRepository.save(category));


    }

    @Override
    public long getEntityCount() {
        return this.categoryRepository.count();
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> randomCategories = new HashSet<>();

        for (int i = 1; i < 4 ; i++) {
            long randomId = ThreadLocalRandom.current()
                    .nextLong(1, this.categoryRepository.count() + 1);

            Category category = this.categoryRepository.findById(randomId).orElse(null);
            randomCategories.add(category);
        }
        return randomCategories;
    }
}
