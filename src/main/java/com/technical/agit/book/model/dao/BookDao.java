package com.technical.agit.book.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
public class BookDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "id_buku", unique = true)
    private String idBuku;

    @Column(name = "judul")
    private String judul;

    @Column(name = "pengarang")
    private String pengarang;

    @Column(name = "penerbit")
    private String penerbit;

    @Column(name = "tanggal_terbit")
    private String tanggalTerbit;

    @Column(name = "tebal_buku")
    private int tebalBuku;

    @Column(name = "status")
    private int status;

    @Column(name = "peminjam")
    private String peminjam;

    @Column(name = "tanggal_pinjam")
    private String tanggalPinjam;

    @Column(name = "tanggal_kembali")
    private String tanggalKembali;
}