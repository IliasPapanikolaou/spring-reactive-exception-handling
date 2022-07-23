package com.unipi.ipap.springreactiveexceptionhandling.dao;

import com.unipi.ipap.springreactiveexceptionhandling.dto.Book;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Random;

@Repository
public class BookRepository {

    public Flux<Book> getBooks() {
        return Flux.range(1, 20)
                .map(i -> new Book(i, "Book" + i, new Random().nextDouble()));
    }
}
