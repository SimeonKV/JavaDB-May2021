package springintro.exercise.services;

import org.springframework.stereotype.Service;
import springintro.exercise.enteties.Author;
import springintro.exercise.repositories.AuthorRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService{
    private final static String AUTHOR_PATH = "src\\main\\" +
            "resources\\Exercise-Resources\\files\\authors.txt";
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void writeData() throws IOException {

        if(this.authorRepository.count() > 0){
            return;
        }

        Path path = Path.of(AUTHOR_PATH);
         Files.readAllLines(path)
                .stream()
                .filter(row -> !row.trim().equals(""))
                .forEach(row -> {
                    String[] name = row.trim().split(" ");
                    String firstName = name[0];
                    String lastName = name[1];
                    Author author = new Author(firstName,lastName);
                    this.authorRepository.save(author);

                });

    }

    @Override
    public Author getRandomAuthor() {
       Long randomNumber = createRandomNumber();
        Author author = this.authorRepository.findById(randomNumber).orElse(null);

        return author;
    }

    public Long createRandomNumber() {
        Random random = new Random();
        int upperbound = Integer.parseInt(this.authorRepository.count() + "");


        return Long.parseLong(random.nextInt(upperbound) + 1 + "");
    }
}
