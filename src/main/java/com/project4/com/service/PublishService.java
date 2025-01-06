package com.project4.com.service;

import com.project4.com.domain.Publisher;
import com.project4.com.repository.PublisherRepository;
import com.project4.com.service.dto.PublisherDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing publisher.
 */
@Service
@Transactional
public class PublishService {

    private static final Logger LOG = LoggerFactory.getLogger(PublishService.class);
    private final PublisherRepository publisherRepository;

    public PublishService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    /**
     * Add a new publisher.
     *
     * @param dto the author details to be added
     * @return the added author as a DTO
     */
    @Transactional
    public PublisherDTO addPublisher(PublisherDTO dto) {
        Publisher entity = new Publisher();
        entity.setTenNXB(dto.getTenNXB());
        entity.setDiaChi(dto.getDiaChi());
        entity.setSdt(dto.getSdt());
        entity.setTrangThai(1); // 0: inactive, 1: active

        var createdEntity = publisherRepository.saveAndFlush(entity);
        LOG.debug("Created Information: {}", createdEntity);
        return mapEntityToDto(createdEntity);
    }

    private PublisherDTO mapEntityToDto(Publisher entity) {
        return new PublisherDTO(entity.getMaNXB(), entity.getTenNXB(), entity.getDiaChi(), entity.getSdt());
    }

    /**
     * Update publisher.
     *
     * @param dto the publisher details to be update publisher
     *
     */

    public PublisherDTO update(PublisherDTO dto) throws InvalidInputException {
        var existingEntity = publisherRepository.findById(dto.getMaNXB());

        if (existingEntity.isEmpty()) {
            throw new InvalidInputException("Cannot found publisher id=" + dto.getMaNXB());
        }

        var entity = existingEntity.get();
        entity.setTenNXB(dto.getTenNXB());
        entity.setDiaChi(dto.getDiaChi());
        entity.setSdt(dto.getSdt());

        var updatedEntity = publisherRepository.saveAndFlush(entity);

        return mapEntityToDto(updatedEntity);
    }

    /**
     * Delete author.
     *
     * @param publisherId the author details to be deleted
     */
    public void delete(Integer publisherId) throws InvalidInputException {
        var existingEntity = publisherRepository.findById(publisherId);

        if (existingEntity.isEmpty()) {
            throw new InvalidInputException("Cannot found publisher id=" + publisherId);
        }

        publisherRepository.delete(existingEntity.get());
    }

    public Page<PublisherDTO> search(String query, Pageable pageable) {
        Page<Object[]> rawResults = publisherRepository.search(query, pageable);
        // Convert Object[] to BookDTO
        Page<PublisherDTO> dtoPage = rawResults.map(objects ->
            new PublisherDTO(
                (Integer) objects[0], // maNXB
                (String) objects[1], // tenNXB
                (String) objects[2], // diachi
                (String) objects[3] // sdt
            )
        );

        return dtoPage;
    }
}
