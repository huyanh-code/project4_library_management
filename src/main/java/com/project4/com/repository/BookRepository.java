package com.project4.com.repository;

import com.project4.com.domain.Book;
import com.project4.com.service.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Book} entity.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(
        value = """
        SELECT
             s.ma_sach,
             s.tensach,
             s.matacgia,
             t.ten_tac_gia,
             s.manxb,
             n.TenNXB,
             s.maloai,
             l.ten_loai,
             s.ma_ke,
             k.vi_tri,
             s.NamXB,
             s.SoLuong,
             s.ghichu,
             s.hinhanh,
             s.trang_thai
         FROM
             tbl_sach s
         JOIN
             tbl_loai l ON s.MaLoai = l.ma_loai
         JOIN
             tbl_nhaxuatban n ON s.MaNXB = n.MaNXB
         JOIN
             tbl_tacgia t ON s.MaTacGia = t.ma_tac_gia
         JOIN
             tbl_kesach k ON s.ma_ke = k.MaKe
         WHERE s.tensach LIKE CONCAT('%', :query, '%')
        """,
        countQuery = """
        SELECT COUNT(*)
        FROM tbl_sach
        WHERE tensach LIKE CONCAT('%', :query, '%')
        """,
        nativeQuery = true
    )
    Page<Object[]> search(String query, Pageable pageable);
}
