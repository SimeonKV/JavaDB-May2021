package springintro.exercise.services;

import springintro.exercise.enteties.Author;

import java.io.IOException;

public interface AuthorService {
    void writeData() throws IOException;
    Author getRandomAuthor();
}
