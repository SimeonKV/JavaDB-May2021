package springintro.exercise.services;

import org.springframework.stereotype.Service;
import springintro.exercise.enteties.*;
import springintro.exercise.repositories.BookRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
  private final static String BOOK_PATH = "src\\main" +
          "\\resources\\Exercise-Resources\\files\\books.txt";
  private final AuthorService authorService;
  private final CategoryService categoryService;
  private final BookRepository bookRepository;

    public BookServiceImpl(AuthorService authorService, CategoryService categoryService, BookRepository bookRepository) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookRepository = bookRepository;
    }



    @Override
    public void writeData() throws IOException {
        if(this.bookRepository.count() > 0){
            return;
        }

        Path path = Path.of(BOOK_PATH);
        Files.readAllLines(path)
                .stream()
                .filter(row -> !row.trim().equals(""))
                .forEach( row -> {
                    Book book = bookInfo(row);
                    this.bookRepository.save(book);

                });

    }

    @Override
    public void extractAllBooksAfterYear(int year) {
        LocalDate theYear = LocalDate.of(year,12,31);
        List<Book> books = this.bookRepository.getAllBooksAfterYear(theYear);
        for(Book book : books){
            System.out.println(book.getTitle() + " " + book.getReleaseDate());
        }

    }

    @Override
    public void extractAllBooksBefore1990() {
        LocalDate theYear = LocalDate.of(1990,1,1);
        Set<Author> authors = this.bookRepository.getAllAuthorsWithBookReleaseDateBefore(theYear);

        for(Author author : authors){
            System.out.println(author.getFirstName() + " " + author.getLastName());
        }

    }

    @Override
    public void extractAuthorsWithBooksCount() {
       List<List<Object>> authors = this.bookRepository.getAllAuthorsOrderByTheirBookCountInDesc();
       for (List<Object> author : authors){
           String name = String.valueOf(author.get(0));
           String lastName = String.valueOf(author.get(1));
           long count = Long.parseLong(String.valueOf(author.get(2)));

           System.out.println(name + " " + lastName + " " + count);

       }

    }

    @Override
    public void extractAllBooksByGeorgePowell() {
        this.bookRepository.findAllByAuthor_FirstNameAndAuthor_LastName("George","Powell")
        .forEach(book -> System.out.println(String.format("%s %s %d",
                book.getTitle(),book.getReleaseDate(),book.getCopies())));
    }


    private Book bookInfo(String row) {
        String[] input = row.split(" ");

        EditionType editionType = EditionType.values()[Integer.parseInt(input[0])];
        LocalDate localDate = LocalDate.parse(input[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(input[2]);
        BigDecimal price = new BigDecimal(input[3]);
        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(input[4])];
        String title = Arrays.stream(input)
                .skip(5)
                .collect(Collectors.joining(" "));
        Author author = this.authorService.getRandomAuthor();
        Set<Category> categories  = this.categoryService.getRandomCategories();

        return new Book(editionType,localDate,copies,price,ageRestriction,title,author,categories);
    }
}
