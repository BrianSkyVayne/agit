package com.technical.agit.book.controller;

import com.technical.agit.book.service.BookService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BookController.class)
@WebMvcTest(controllers = BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void getAllBooks() {
        callBookController_success("/get_all_daftar_buku");
    }

    @Test
    void createBook() {
        callBookController_success("/create_book");
    }

    @Test
    void updateBook() {
        callBookController_success("/update_book");
    }

    @Test
    void borrowABook() {
        callBookController_success("/borrow_book");
    }

    @Test
    void deleteBook() {
        callBookController_success("/delete_book");
    }

    @Test
    void getAllBooks_failed() {
        callBookController_failed("/get_all_daftar_buku");
    }

    @Test
    void createBook_failed() {
        callBookController_failed("/create_book");
    }

    @Test
    void updateBook_failed() {
        callBookController_failed("/update_book");
    }

    @Test
    void borrowABook_failed() {
        callBookController_failed("/borrow_book");
    }

    @Test
    void deleteBook_failed() {
        callBookController_failed("/delete_book");
    }

    @SneakyThrows
    private void callBookController_success(String url) {
        when(bookService.getAllBook(any())).thenReturn(List.of());
        when(bookService.createBook(any())).thenReturn(new ResponseEntity<>(null, HttpStatus.OK));
        when(bookService.borrowABook(any())).thenReturn(new ResponseEntity<>(null, HttpStatus.OK));
        when(bookService.deleteBook(any())).thenReturn(new ResponseEntity<>(null, HttpStatus.OK));
        when(bookService.updateBook(any())).thenReturn(new ResponseEntity<>(null, HttpStatus.OK));
        if (url.equals("/get_all_daftar_buku")) {
            mockMvc.perform(
                    get(url)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}")
            ).andExpect(status().isOk());
        } else
            mockMvc.perform(
                    post(url)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}")
            ).andExpect(status().isOk());
    }

    @SneakyThrows
    private void callBookController_failed(String url) {
        when(bookService.getAllBook(any())).thenThrow(new NullPointerException());
        when(bookService.createBook(any())).thenThrow(new NullPointerException());
        when(bookService.borrowABook(any())).thenThrow(new NullPointerException());
        when(bookService.deleteBook(any())).thenThrow(new NullPointerException());
        when(bookService.updateBook(any())).thenThrow(new NullPointerException());
        if (url.equals("/get_all_daftar_buku")) {
            mockMvc.perform(
                    get(url)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}")
            );
        } else
            mockMvc.perform(
                    post(url)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}")
            );
    }
}