package com.example.library.books;

import com.example.library.books.exceptions.UnexistedIdException;
import com.example.library.books.models.Book;
import com.example.library.books.models.BookDto;
import com.example.library.books.models.CreateBookDto;
import com.example.library.books.models.SearchRequest;
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
    public BookDto createBook(final CreateBookDto bookDto) {
        return new BookDto(repository.insert(new Book(bookDto)));
    }

    @Override
    public BookDto getBook(final String id) {
        return repository.findById(id)
                .orElseThrow(() -> new UnexistedIdException(UNEXISTED_ID_ERROR))
                .toDto();
    }

    @Override
    public BookDto updateBook(final BookDto bookDto) {
        if (repository.existsById(bookDto.getId())) {
            return repository.save(new Book(bookDto)).toDto();
        } else {
            throw new UnexistedIdException(UNEXISTED_ID_ERROR);
        }
    }

    @Override
    public void deleteBook(final String id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Book> getBooksBy(final SearchRequest request,final Pageable pageable) {
        Criteria regexCriteria = Criteria.where(request.getSearchField().getValue())
                //case sensitive
                .regex(request.getSearchValue());
        Query query = new Query()
                .with(pageable)
                .addCriteria(regexCriteria);
        List<Book> books = mongoTemplate.find(query, Book.class);
        return new PageImpl<>(books, pageable, books.size());
    }

}
