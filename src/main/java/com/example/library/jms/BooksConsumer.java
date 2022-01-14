package com.example.library.jms;

import com.example.library.dao.BooksRepository;
import com.example.library.entity.Book;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.Session;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BooksConsumer {

    BooksRepository booksRepository;

    @JmsListener(destination = "books-queue")
    public void receiveMessage(@Payload Book book, @Headers MessageHeaders headers, Message message, Session session) {
        booksRepository.save(book);
        log.info("received & saved <" + book.getName() + ">");
    }
}
