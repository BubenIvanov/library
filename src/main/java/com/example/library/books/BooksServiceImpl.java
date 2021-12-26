package com.example.library.books;

import com.example.library.books.models.Book;
import com.example.library.books.models.BookDto;
import com.example.library.books.models.CreateBookDto;
import com.example.library.books.models.Field;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BooksServiceImpl implements BookService {

    private static final String UNEXISTED_ID_ERROR = "Unexisted id!";
    BooksRepository repository;
    MongoTemplate mongoTemplate;

    @Override
    public BookDto updateBook(BookDto bookDto) {
        if (repository.existsById(bookDto.getId())) {
            return repository.save(new Book(bookDto)).toDto();
        } else {
            throw new IllegalArgumentException(UNEXISTED_ID_ERROR);
        }
    }

    @Override
    public BookDto getBook(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(UNEXISTED_ID_ERROR))
                .toDto();
    }

    @Override
    public void deleteBook(String id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Book> getBooksBy(String searchField, String searchValue, Pageable pageable) {
        Query query = new Query().with(pageable);
        query.addCriteria(Criteria.where(getFieldName(searchField)).regex(searchValue));
        List<Book> books = mongoTemplate.find(query, Book.class);
        return new PageImpl<>(books, pageable, books.size());
    }

    private String getFieldName(String searchField) {
        if (Field.GENRE.getValue().equalsIgnoreCase(searchField)) {
            return Field.GENRE.getValue();
        } else if (Field.AUTHOR.getValue().equalsIgnoreCase(searchField)) {
            return Field.AUTHOR.getValue();
        } else {
            throw new IllegalArgumentException("Invalid search field!");
        }
    }
/*    @Override
    public Page<Book> getBooksBy(String searchField, String searchValue, Pageable pageable) {
        if (Field.GENRE.getValue().equalsIgnoreCase(searchField)) {
            return repository.findAllByGenreIsLike(searchValue, pageable);
        } else if (Field.AUTHOR.getValue().equalsIgnoreCase(searchField)) {
            return repository.findAllByAuthorIsLike(searchValue, pageable);
        } else {
            throw new IllegalArgumentException("Invalid search field!");
        }
    }*/

    @Override
    public BookDto createBook(CreateBookDto bookDto) {
        return new BookDto(repository.insert(new Book(bookDto)));
    }

}
