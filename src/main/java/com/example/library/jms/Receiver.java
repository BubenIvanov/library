package com.example.library.jms;

import com.example.library.books.BooksRepository;
import com.example.library.books.models.Book;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Receiver {

    BooksRepository booksRepository;

    @JmsListener(destination = "library", containerFactory = "myFactory")
    public void receiveMessage(Book book) {
        log.info("Received <" + book + ">");
        booksRepository.save(book);
    }

}
