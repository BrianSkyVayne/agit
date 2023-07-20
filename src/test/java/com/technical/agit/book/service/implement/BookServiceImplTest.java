package com.technical.agit.book.service.implement;

import com.technical.agit.book.model.dao.BookDao;
import com.technical.agit.book.model.dto.BookDto;
import com.technical.agit.book.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BookServiceImpl.class)
@WebMvcTest(controllers = BookServiceImpl.class)
class BookServiceImplTest {

    @Autowired
    BookServiceImpl bookService;

    @MockBean
    BookRepository bookRepository;

    BookDao bookDao() {
        return BookDao.builder()
                .id(0)
                .idBuku("123")
                .tebalBuku(123)
                .status(0)
                .build();
    }

    BookDto bookDto() {
        return BookDto.builder()
                .idBuku("123")
                .tebalBuku(123)
                .status(0)
                .build();
    }

    @Test
    void getAllBook() {
        callBookService_success();
        bookService.getAllBook("1");
    }
    @Test
    void getAllBookV2() {
        callBookService_success();
        bookService.getAllBook(null);
    }

    @Test
    void createBook() {
        callBookService_success();
        bookService.createBook(bookDto());
    }

    @Test
    void createBook_failed() {
        callBookService_success();
        bookService.createBook(null);
    }

    @Test
    void updateBook() {
        callBookService_success();
        bookService.updateBook(bookDto());
    }

    @Test
    void updateBook_failed() {
        callBookService_success();
        bookService.updateBook(null);
    }

    @Test
    void borrowABook() {
        callBookService_success();
        bookService.borrowABook(bookDto());
    }

    @Test
    void borrowABook_failed() {
        callBookService_success();
        bookService.borrowABook(null);
    }

    @Test
    void deleteBook() {
        callBookService_success();
        bookService.deleteBook("1");
    }

    @Test
    void deleteBook_failed() {
        bookService.deleteBook(null);
    }

    private void callBookService_success() {
        when(bookRepository.findAll()).thenReturn(List.of(bookDao()));
        when(bookRepository.findByIdBuku(any())).thenReturn(bookDao());
        when(bookRepository.findByStatus(anyInt())).thenReturn(List.of(bookDao()));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(bookDao()));
    }
}