package com.project4.com.service;

import com.project4.com.domain.Authority;
import com.project4.com.domain.Readers;
import com.project4.com.domain.User;
import com.project4.com.repository.ReadersRepository;
import com.project4.com.security.AuthoritiesConstants;
import com.project4.com.service.dto.BookDTO;
import com.project4.com.service.dto.ReadersDTO;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.security.RandomUtil;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class ReadersService {

    private static final Logger LOG = LoggerFactory.getLogger(ReadersService.class);
    private final ReadersRepository readerRepository;

    public ReadersService(ReadersRepository readersRepository) {
        this.readerRepository = readersRepository;
    }

    @Transactional(readOnly = true)
    public Page<ReadersDTO> getAllReaders(Pageable pageable) {
        return readerRepository
            .findAll(pageable)
            .map(readersEntity ->
                new ReadersDTO(
                    readersEntity.getMaDocGia(),
                    readersEntity.getTenDocGia(),
                    readersEntity.getGioiTinh(),
                    readersEntity.getDiachi(),
                    readersEntity.getSdt()
                )
            );
    }

    /**
     * Add a new reader.
     *
     * @param dto the reader details to be added
     * @return the added reader as a DTO
     */
    @Transactional
    public ReadersDTO addReader(ReadersDTO dto) {
        Readers entity = new Readers();
        entity.setTenDocGia(dto.getTenDocGia());
        entity.setGioiTinh(dto.getGioiTinh());
        entity.setDiachi(dto.getDiachi());
        entity.setSdt(dto.getSdt());
        entity.setTrangThai(1); // 0: inactive, 1: active

        var createdEntity = readerRepository.saveAndFlush(entity);
        LOG.debug("Created Information: {}", createdEntity);
        return mapEntityToDto(createdEntity);
    }

    private ReadersDTO mapEntityToDto(Readers entity) {
        return new ReadersDTO(entity.getMaDocGia(), entity.getTenDocGia(), entity.getGioiTinh(), entity.getDiachi(), entity.getSdt());
    }

    /**
     * Update a new author.
     *
     * @param dto the author details to be update
     * @return the added author as a DTO
     */

    public ReadersDTO update(ReadersDTO dto) throws InvalidInputException {
        var existingEntity = readerRepository.findById(dto.getMaDocGia());

        if (existingEntity.isEmpty()) {
            throw new InvalidInputException("Cannot found reader id=" + dto.getMaDocGia());
        }

        var entity = existingEntity.get();
        entity.setTenDocGia(dto.getTenDocGia());
        entity.setGioiTinh(dto.getGioiTinh());
        entity.setDiachi(dto.getDiachi());
        entity.setSdt(dto.getSdt());

        var updatedEntity = readerRepository.saveAndFlush(entity);

        return mapEntityToDto(updatedEntity);
    }

    /**
     * Delete a new author.
     *
     * @param readerId the author details to be deleted
     */
    public void delete(Integer readerId) throws InvalidInputException {
        var existingEntity = readerRepository.findById(readerId);

        if (existingEntity.isEmpty()) {
            throw new InvalidInputException("Cannot found reader id=" + readerId);
        }

        readerRepository.delete(existingEntity.get());
    }

    public Page<ReadersDTO> search(String query, Pageable pageable) {
        Page<Object[]> rawResults = readerRepository.search(query, pageable);
        // Convert Object[] to BookDTO
        Page<ReadersDTO> dtoPage = rawResults.map(objects ->
            new ReadersDTO(
                (Integer) objects[0], // maDocGia
                (String) objects[1], // tenDocGia
                (String) objects[2], // gioiTinh
                (String) objects[3], // diachi
                (String) objects[4] // sdt
            )
        );

        return dtoPage;
    }
}
