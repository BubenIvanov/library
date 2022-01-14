package com.example.library.dao;

import com.example.library.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BooksRepository extends MongoRepository<Book, String> {
}
