package com.unipi.ipap.springreactiveexceptionhandling.dao;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookRepositoryTest {

    @Test
    public void bookRepositoryTest() {
        BookRepository bookRepository = new BookRepository();
        Mono<Long> count = bookRepository.getBooks().count();
        assertEquals(20, count.block());
    }
}
