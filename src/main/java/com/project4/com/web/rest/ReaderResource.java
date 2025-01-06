package com.project4.com.web.rest;

import com.project4.com.service.InvalidInputException;
import com.project4.com.service.ReadersService;
import com.project4.com.service.dto.BookDTO;
import com.project4.com.service.dto.ReadersDTO;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/readers")
public class ReaderResource {

    private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(
        Arrays.asList(
            "id",
            "login",
            "firstName",
            "lastName",
            "email",
            "activated",
            "langKey",
            "createdBy",
            "createdDate",
            "lastModifiedBy",
            "lastModifiedDate"
        )
    );

    private static final Logger LOG = LoggerFactory.getLogger(ReaderResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReadersService readerService;

    public ReaderResource(ReadersService readersService) {
        this.readerService = readersService;
    }

    /**
     * {@code GET /admin/users} : get all users with all the details - calling this are only allowed for the administrators.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    //    @GetMapping
    //    public ResponseEntity<Page<ReadersDTO>> getAll(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
    //        LOG.debug("REST request to get all readers for an admin");
    //        if (!onlyContainsAllowedProperties(pageable)) {
    //            return ResponseEntity.badRequest().build();
    //        }
    //
    //        final Page<ReadersDTO> page = readerService.getAllReaders(pageable);
    //        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    //        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    //    }

    private boolean onlyContainsAllowedProperties(Pageable pageable) {
        return pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(ALLOWED_ORDERED_PROPERTIES::contains);
    }

    /**
     * {@code POST /api/readers} : create a new reader.
     *
     * @param readersDTO the reader data to be created.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reader.
     */
    @PostMapping
    public ResponseEntity<ReadersDTO> create(@RequestBody ReadersDTO readersDTO) {
        LOG.debug("REST request to save a new reader");

        // Call the service layer to add the reader
        ReadersDTO result = readerService.addReader(readersDTO);

        // Set the URI for the newly created reader
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getMaDocGia()).toUri();

        // Return a ResponseEntity with status 201 (Created)
        return ResponseEntity.created(location).body(result);
    }

    @PutMapping
    public ResponseEntity<ReadersDTO> update(@RequestBody ReadersDTO readersDTO) throws InvalidInputException {
        LOG.debug("REST request to update");

        // Call the service layer to add the reader
        var result = readerService.update(readersDTO);

        return ResponseUtil.wrapOrNotFound(
            Optional.of(result),
            HeaderUtil.createAlert(applicationName, "reader.updated", String.valueOf(readersDTO.getMaDocGia()))
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ReadersDTO> delete(@PathVariable("id") Integer readerId) throws InvalidInputException {
        LOG.debug("REST request to delete id=" + readerId);

        // Call the service layer to add the reader
        readerService.delete(readerId);

        return ResponseEntity.noContent()
            .headers(HeaderUtil.createAlert(applicationName, "reader.deleted", String.valueOf(readerId)))
            .build();
    }

    @GetMapping
    public ResponseEntity<Page<ReadersDTO>> search(@org.springdoc.core.annotations.ParameterObject Pageable pageable, String query) {
        LOG.debug("REST request to search books");

        final Page<ReadersDTO> page = readerService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
}
