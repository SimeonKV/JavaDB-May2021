package com.json.lab.service.impl;

import com.google.gson.Gson;
import com.json.lab.constants.GlobalConstants;
import com.json.lab.model.dto.CategorySeedDto;
import com.json.lab.model.entity.Category;
import com.json.lab.repository.CategoryRepository;
import com.json.lab.service.CategoryService;
import com.json.lab.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String CATEGORIES_FILE = "categories.json";
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(Gson gson, ValidationUtil validationUtil,
                               CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories() throws IOException {
    if(categoryRepository.count() > 0){
        return;
    }

      String categoryJson =  Files
                .readString(Path.of(GlobalConstants.RESOURCES_FILE_PATH + CATEGORIES_FILE ));

        CategorySeedDto[] categorySeedDtos =
                gson.fromJson(categoryJson,CategorySeedDto[].class);


        Arrays.stream(categorySeedDtos)
                .filter(catEntityToBe -> validationUtil.isValid(catEntityToBe))
                .map(categoryEntityToBe -> modelMapper.map(categoryEntityToBe, Category.class))
                .forEach(this.categoryRepository::save);

        System.out.println();


    }

    @Override
    public Set<Category> findRandomCategories() {
      Set<Category> categories = new HashSet<>();

        int catCount = ThreadLocalRandom
                .current()
                .nextInt(1,4);

        long randomId = ThreadLocalRandom
                .current()
                .nextLong(1,this.categoryRepository.count() + 1);

        for (int i = 0; i < catCount ; i++) {
            Category category = this.categoryRepository
                    .findById(randomId)
                    .orElse(null);

            categories.add(category);
        }

        return categories;
    }
}
