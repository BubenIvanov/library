package com.example.library.books;

import com.example.library.books.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BooksRepository extends MongoRepository<Book, String> {

    Page<Book> findAllByAuthorIsLike(String author, Pageable pageable);

    Page<Book> findAllByGenreIsLike(String genre, Pageable pageable);

    Page<Book> findAll(Pageable sort);

}
