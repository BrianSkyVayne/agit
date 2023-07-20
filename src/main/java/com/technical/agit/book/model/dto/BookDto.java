package com.technical.agit.book.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto implements Serializable {
    private static final long serialVersionUID = -4120222014340803928L;

    private long id;
    private String idBuku;
    private String judul;
    private String pengarang;
    private String penerbit;
    private String tanggalTerbit;
    private int tebalBuku;
    private int status;
    private String peminjam;
    private String tanggalPinjam;
    private String tanggalKembali;
}
