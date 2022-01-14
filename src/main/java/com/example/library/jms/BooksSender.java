package com.example.library.jms;

import com.example.library.entity.Book;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BooksSender {

    JmsTemplate jmsTemplate;

    public void send(Book book) {
        log.info("Sending book: {}", book.getName());
        jmsTemplate.convertAndSend("books-queue", book);
    }
}
