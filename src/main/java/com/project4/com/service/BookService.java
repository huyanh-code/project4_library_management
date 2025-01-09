package com.project4.com.service;

import com.project4.com.domain.*;
import com.project4.com.repository.*;
import com.project4.com.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing Books.
 */
@Service
@Transactional
public class BookService {

    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final CategoryRepository categoryRepository;
    private final ShelfRepository shelfRepository;

    public BookService(
        BookRepository bookRepository,
        AuthorRepository authorRepository,
        PublisherRepository publisherRepository,
        CategoryRepository categoryRepository,
        ShelfRepository shelfRepository
    ) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.categoryRepository = categoryRepository;
        this.shelfRepository = shelfRepository;
    }

    @Transactional(readOnly = true)
    public Page<BookDTO> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(bookEntity -> new BookDTO());
    }

    @Transactional(readOnly = true)
    public Page<BookDTO> search(String query, Pageable pageable) {
        Page<Object[]> rawResults = bookRepository.search(query, pageable);
        // Convert Object[] to BookDTO
        Page<BookDTO> bookDTOPage = rawResults.map(objects ->
            new BookDTO(
                (Integer) objects[0], // maSach
                (String) objects[1], // tenSach
                (Integer) objects[2], // ma loai
                (String) objects[3], // ten loai
                (Integer) objects[4], // ma nha XuatBan
                (String) objects[5], // ten nha XuatBan
                (Integer) objects[6], // ma tac gia
                (String) objects[7], // ten tac gia
                (Integer) objects[8], // ma ke
                (String) objects[9], // vi tri
                (Integer) objects[10], // nam xb
                (Integer) objects[11], // so luong
                (String) objects[12], // ghi chu
                (String) objects[13] // hinh anh
            )
        );

        return bookDTOPage;
    }

    @Transactional
    public BookDTO create(BookDTO dto) {
        Book entity = new Book();
        entity.setTensach(dto.getTensach());
        //  tac gia
        Author author = authorRepository
            .findById(dto.getIdTacgia())
            .orElseThrow(() -> new IllegalArgumentException("Tác giả với ID " + dto.getIdTacgia() + " không tồn tại."));
        entity.setMatacgia(author.getMaTacGia());
        //  nxb
        Publisher publisher = publisherRepository
            .findById(dto.getMaNXB())
            .orElseThrow(() -> new IllegalArgumentException("Nhà xuất bản với ID" + dto.getMaNXB() + "không tồn tại."));
        entity.setMaNXB(publisher.getMaNXB());
        // loai
        Category category = categoryRepository
            .findById(dto.getMaLoai())
            .orElseThrow(() -> new IllegalArgumentException("Mã loại với ID" + dto.getMaLoai() + "không tồn tại."));
        entity.setMaloai(category.getMaLoai());
        // ke sach
        Shelf shelf = shelfRepository
            .findById(dto.getMake())
            .orElseThrow(() -> new IllegalArgumentException("Mã kệ với ID" + dto.getMake() + "không tồn tại."));
        entity.setMaKe(shelf.getMaKeSach());

        entity.setNamxb(dto.getNamxb());
        entity.setSoluong(dto.getSoluong());
        entity.setGhichu(dto.getGhichu());
        entity.setHinhanh(dto.getHinhanh());

        var createdEntity = bookRepository.saveAndFlush(entity);
        LOG.debug("Created Information: {}", createdEntity);

        return mapEntityToDto(createdEntity, author, publisher, category, shelf);
    }

    /**
     * Chuyển đổi từ Book entity sang BookDTO.
     *
     * @param entity   thực thể Book
     * @param author   thực thể Author
     * @param publisher thực thể Publisher
     * @param category thực thể Category
     * @param shelf    thực thể Shelf
     * @return đối tượng BookDTO
     */
    private BookDTO mapEntityToDto(Book entity, Author author, Publisher publisher, Category category, Shelf shelf) {
        return new BookDTO(
            entity.getMaSoSach(),
            entity.getTensach(),
            author.getMaTacGia(),
            author.getTenTacGia(),
            publisher.getMaNXB(),
            publisher.getTenNXB(),
            category.getMaLoai(),
            category.getTenLoai(),
            shelf.getMaKeSach(),
            shelf.getViTri(),
            entity.getNamxb(),
            entity.getSoluong(),
            entity.getGhichu(),
            entity.getHinhanh()
        );
    }

    @Transactional
    public BookDTO update(BookDTO dto) {
        LOG.debug("Request to update Book: {}", dto);

        // Kiểm tra sự tồn tại của Book
        Book entity = bookRepository
            .findById(dto.getMaSoSach())
            .orElseThrow(() -> new IllegalArgumentException("Sách không tồn tại với mã số: " + dto.getMaSoSach()));

        // Cập nhật thông tin của Book
        entity.setTensach(dto.getTensach());

        // Cập nhật Author
        Author author = authorRepository
            .findById(dto.getIdTacgia())
            .orElseThrow(() -> new IllegalArgumentException("Tác giả không tồn tại: " + dto.getIdTacgia()));
        entity.setMatacgia(author.getMaTacGia());

        // Cập nhật Publisher
        Publisher publisher = publisherRepository
            .findById(dto.getMaNXB())
            .orElseThrow(() -> new IllegalArgumentException("Nhà xuất bản không tồn tại: " + dto.getMaNXB()));
        entity.setMaNXB(publisher.getMaNXB());

        // Cập nhật Category
        Category category = categoryRepository
            .findById(dto.getMaLoai())
            .orElseThrow(() -> new IllegalArgumentException("Thể loại không tồn tại: " + dto.getMaLoai()));
        entity.setMaloai(category.getMaLoai());

        // Cập nhật Shelf (kệ sách)
        Shelf shelf = shelfRepository
            .findById(dto.getMake())
            .orElseThrow(() -> new IllegalArgumentException("Kệ sách không tồn tại với tên: " + dto.getMake()));
        entity.setMaKe(shelf.getMaKeSach());

        entity.setNamxb(dto.getNamxb());
        entity.setSoluong(dto.getSoluong());
        entity.setGhichu(dto.getGhichu());
        entity.setHinhanh(dto.getHinhanh());

        // Lưu thông tin đã cập nhật vào cơ sở dữ liệu
        Book updatedEntity = bookRepository.saveAndFlush(entity);
        LOG.debug("Updated Information: {}", updatedEntity);

        // Trả về DTO đã cập nhật
        return mapEntityToDto(updatedEntity, author, publisher, category, shelf);
    }

    @Transactional
    public void delete(Integer bookId) {
        LOG.debug("Request to delete Book with ID: {}", bookId);

        // Kiểm tra sự tồn tại của Book trước khi xóa
        Book entity = bookRepository
            .findById(bookId)
            .orElseThrow(() -> new IllegalArgumentException("Sách không tồn tại với mã số: " + bookId));

        // Xóa Book
        bookRepository.delete(entity);
        LOG.debug("Deleted Book with ID: {}", bookId);
    }
}
