package com.technical.agit.book.controller;

import com.technical.agit.book.model.dto.BookDto;
import com.technical.agit.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/get_all_daftar_buku")
    public ResponseEntity<?> getAllBooks(@RequestBody String filter) {
        try {
            return ResponseEntity.ok().body(bookService.getAllBook(filter));
        } catch (Exception ex) {
            log.info(ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create_book")
    public ResponseEntity<?> createBook(@RequestBody BookDto request) {
        try {
            return ResponseEntity.ok().body(bookService.createBook(request));
        } catch (Exception ex) {
            log.info(ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/update_book")
    public ResponseEntity<?> updateBook(@RequestBody BookDto request) {
        try {
            return ResponseEntity.ok().body(bookService.updateBook(request));
        } catch (Exception ex) {
            log.info(ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/borrow_book")
    public ResponseEntity<?> borrowABook(@RequestBody BookDto request) {
        try {
            return ResponseEntity.ok().body(bookService.borrowABook(request));
        } catch (Exception ex) {
            log.info(ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/delete_book")
    public ResponseEntity<?> deleteBook(@RequestBody String request) {
        try {
            return ResponseEntity.ok().body(bookService.deleteBook(request));
        } catch (Exception ex) {
            log.info(ex.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
