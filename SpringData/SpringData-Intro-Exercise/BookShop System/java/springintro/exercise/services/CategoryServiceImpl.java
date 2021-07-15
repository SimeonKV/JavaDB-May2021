package springintro.exercise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springintro.exercise.enteties.Category;
import springintro.exercise.repositories.CategoryRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService{
    private static final String CATEGORIES_FILE_PATH = "src\\main\\" +
            "resources\\Exercise-Resources\\files\\categories.txt";
    private  final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void writeData() throws IOException {
         if(this.categoryRepository.count() > 0){
             return;
         }

        Path path = Path.of(CATEGORIES_FILE_PATH);
        Files.readAllLines(path)
                .stream()
                .filter(row -> !row.trim().equals(""))
                .forEach(row ->{
                    Category category = new Category(row);
                    categoryRepository.save(category);

                });
        System.out.println();
    }

    @Override
    public Set<Category> getRandomCategories() {
       Set<Category>  categories = new HashSet<>();
        long count = this.categoryRepository.count() + 1;
        long randLong = ThreadLocalRandom
                .current().nextLong(1,count);

        for (int i = 1; i <= randLong ; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1,count);

            Category category = this.categoryRepository.findById(randomId).orElse(null);
            categories.add(category);
        }


        return categories;
    }
}
