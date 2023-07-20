package com.technical.agit.book.repository;

import com.technical.agit.book.model.dao.BookDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookDao, Long> {

    @Query(value = "SELECT * FROM book b WHERE b.status = :status", nativeQuery = true)
    List<BookDao> findByStatus(int status);

    BookDao findByIdBuku(String idBuku);

    @Query(value = "DELETE FROM book b WHERE b.idBuku = :idBuku", nativeQuery = true)
    void deleteByIdBuku(String idBuku);
}