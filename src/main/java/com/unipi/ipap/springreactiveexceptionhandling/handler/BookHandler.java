package com.unipi.ipap.springreactiveexceptionhandling.handler;

import com.unipi.ipap.springreactiveexceptionhandling.dao.BookRepository;
import com.unipi.ipap.springreactiveexceptionhandling.dto.Book;
import com.unipi.ipap.springreactiveexceptionhandling.exception.BookAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookHandler {

    @Autowired
    private BookRepository bookRepository;


    public Mono<ServerResponse> getBooks(ServerRequest request) {
        Flux<Book> books = bookRepository.getBooks();
        return ServerResponse.ok().body(books,Book.class);
    }

    public Mono<ServerResponse> getBookById(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("bookId"));
        Mono<Book> bookMono = bookRepository.getBooks()
                .filter(book -> book.bookId() == id)
                .next().switchIfEmpty(Mono.error(new BookAPIException("Book not found with bookId : " + id)));
        return ServerResponse.ok().body(bookMono, Book.class);
    }
}
