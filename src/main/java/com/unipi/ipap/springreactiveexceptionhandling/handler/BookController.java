package com.unipi.ipap.springreactiveexceptionhandling.handler;

import com.unipi.ipap.springreactiveexceptionhandling.dao.BookRepository;
import com.unipi.ipap.springreactiveexceptionhandling.dto.Book;
import com.unipi.ipap.springreactiveexceptionhandling.exception.BookAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository repository;

    @GetMapping
    public Flux<Book> getBooks() {
        return repository.getBooks();
    }

    @GetMapping("/{id}") //21
    public Mono<Book> getBookById(@PathVariable int id) {
        return repository.getBooks()
                .filter(book -> book.bookId() == id)
                .next()
                .switchIfEmpty(Mono.error(new BookAPIException("Book not found with id " + id)));
    }
}
