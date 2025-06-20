package com.tune.library.service;

import com.tune.library.dto.BookDTO;
import com.tune.library.entity.Book;
import com.tune.library.repository.BookRepository;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Builder
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(BookDTO bookDTO) {
        // avoid adding the duplicate entries
        if (bookRepository.existsByIsbn(bookDTO.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN already exists.");
        }

        Book book = Book.builder()
                .title(bookDTO.getTitle())
                .isbn(bookDTO.getIsbn())
                .publisher(bookDTO.getPublisher())
                .year(bookDTO.getYear())
                .copiesAvailable(bookDTO.getCopiesAvailable())
                .author(bookDTO.getAuthor())
                .build();

        return bookRepository.save(book);
    }
}
