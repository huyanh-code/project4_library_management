package com.project4.com.service;

import com.project4.com.domain.Author;
import com.project4.com.domain.Readers;
import com.project4.com.repository.AuthorRepository;
import com.project4.com.repository.BookRepository;
import com.project4.com.service.dto.AuthorDTO;
import com.project4.com.service.dto.BookDTO;
import com.project4.com.service.dto.ReadersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing author.
 */
@Service
@Transactional
public class AuthorService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorService.class);
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public Page<AuthorDTO> getAllAuthors(Pageable pageable) {
        return authorRepository
            .findAll(pageable)
            .map(authorEntity ->
                new AuthorDTO(authorEntity.getMaTacGia(), authorEntity.getTenTacGia(), authorEntity.getNamSinh(), authorEntity.getQueQuan())
            );
    }

    /**
     * Add a new author.
     *
     * @param dto the author details to be added
     * @return the added author as a DTO
     */
    @Transactional
    public AuthorDTO addAuthor(AuthorDTO dto) {
        Author entity = new Author();
        entity.setTenTacGia(dto.getTenTacGia());
        entity.setNamSinh(dto.getNamSinh());
        entity.setQueQuan(dto.getQueQuan());
        entity.setTrangThai(1); // 0: inactive, 1: active

        var createdEntity = authorRepository.saveAndFlush(entity);
        LOG.debug("Created Information: {}", createdEntity);
        return mapEntityToDto(createdEntity);
    }

    private AuthorDTO mapEntityToDto(Author entity) {
        return new AuthorDTO(entity.getMaTacGia(), entity.getTenTacGia(), entity.getNamSinh(), entity.getQueQuan());
    }

    /**
     * Update author.
     *
     * @param dto the author details to be update author
     *
     */

    public AuthorDTO update(AuthorDTO dto) throws InvalidInputException {
        var existingEntity = authorRepository.findById(dto.getMaTacGia());

        if (existingEntity.isEmpty()) {
            throw new InvalidInputException("Cannot found author id=" + dto.getMaTacGia());
        }

        var entity = existingEntity.get();
        entity.setTenTacGia(dto.getTenTacGia());
        entity.setNamSinh(dto.getNamSinh());
        entity.setQueQuan(dto.getQueQuan());

        var updatedEntity = authorRepository.saveAndFlush(entity);

        return mapEntityToDto(updatedEntity);
    }

    /**
     * Delete author.
     *
     * @param authorId the author details to be deleted
     */
    public void delete(Integer authorId) throws InvalidInputException {
        var existingEntity = authorRepository.findById(authorId);

        if (existingEntity.isEmpty()) {
            throw new InvalidInputException("Cannot found author id=" + authorId);
        }

        authorRepository.delete(existingEntity.get());
    }

    public Page<AuthorDTO> search(String query, Pageable pageable) {
        Page<Object[]> rawResults = authorRepository.search(query, pageable);
        // Convert Object[] to BookDTO
        Page<AuthorDTO> dtoPage = rawResults.map(objects ->
            new AuthorDTO(
                (Integer) objects[0], // ma_tac_gia
                (String) objects[1], // ten_tac_gia
                (String) objects[2], // nam_sinh
                (String) objects[3] // que_quan
            )
        );

        return dtoPage;
    }
}
