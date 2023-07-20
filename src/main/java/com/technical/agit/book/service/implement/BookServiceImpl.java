package com.technical.agit.book.service.implement;

import com.technical.agit.book.model.dao.BookDao;
import com.technical.agit.book.model.dto.BookDto;
import com.technical.agit.book.repository.BookRepository;
import com.technical.agit.book.service.BookService;
import com.technical.agit.book.util.CommonUtil;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<BookDto> getAllBook(String filter) {
        List<BookDao> bookDaos;
        if (Objects.isNull(filter)) {
            bookDaos = bookRepository.findAll();
        } else
            bookDaos = bookRepository.findByStatus(Integer.parseInt(filter));

        return mappingToDto(bookDaos);
    }

    @Override
    public ResponseEntity<?> createBook(BookDto request) {
        try {
            bookRepository.save((BookDao) CommonUtil.constructData(request, new BookDao()));
            return ResponseEntity.ok("book uploaded successfully!!");
        } catch (Exception e) {
            log.info(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<?> updateBook(BookDto request) {
        try {
            BookDao bookDao = bookRepository.findByIdBuku(request.getIdBuku());
            if (Objects.isNull(bookDao))
                throw new NotFoundException("");
            else if (Objects.equals(request.getStatus(), 0)) {
                request.setId(bookDao.getId());
                request.setPeminjam("-");
                request.setTanggalPinjam("-");
                request.setTanggalKembali("-");
            }
            request.setTebalBuku(Objects.equals(request.getTebalBuku(), 0) ? bookDao.getTebalBuku() : request.getTebalBuku());
            bookRepository.saveAndFlush((BookDao) CommonUtil.constructData(request, bookDao));
            return ResponseEntity.ok("book update successfully!!");
        } catch (Exception e) {
            log.info(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

    @Override
    public ResponseEntity<?> borrowABook(BookDto request) {
        try {
            BookDao bookDao = bookRepository.findByIdBuku(request.getIdBuku());
            if (Objects.isNull(bookDao))
                throw new NotFoundException("");
            else
                request.setId(bookDao.getId());
            request.setTebalBuku(bookDao.getTebalBuku());
            request.setStatus(1);
            bookRepository.saveAndFlush((BookDao) CommonUtil.constructData(request, bookDao));
            return ResponseEntity.ok("book status update successfully!!");

        } catch (Exception e) {
            log.info(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

    @Override
    public ResponseEntity<?> deleteBook(String request) {
        try {
            var data = bookRepository.findByIdBuku(request);
            if (Objects.isNull(data))
                throw new NotFoundException("");
            else
                bookRepository.deleteById(data.getId());
            return ResponseEntity.ok("book delete successfully!!");
        } catch (Exception e) {
            log.info(e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

    private List<BookDto> mappingToDto(List<BookDao> bookDaos) {
        List<BookDto> bookDtos = new ArrayList<>();
        if (Objects.nonNull(bookDaos)) {
            for (BookDao bookDao : bookDaos)
                bookDtos.add((BookDto) CommonUtil.constructData(bookDao, new BookDto()));
        }
        return bookDtos;
    }
}
