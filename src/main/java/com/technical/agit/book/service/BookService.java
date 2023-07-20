package com.technical.agit.book.service;

import com.technical.agit.book.model.dto.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBook(@Nullable String filter);

    ResponseEntity<?> createBook(BookDto request);

    ResponseEntity<?> updateBook(BookDto request);

    ResponseEntity<?> borrowABook(BookDto request);

    ResponseEntity<?> deleteBook(String request);
}
