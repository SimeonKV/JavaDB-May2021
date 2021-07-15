package springintro.exercise.services;

import springintro.exercise.enteties.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void writeData() throws IOException;

    Set<Category> getRandomCategories();
}
