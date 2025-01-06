package com.project4.com.web.rest;

import com.project4.com.service.AuthorService;
import com.project4.com.service.InvalidInputException;
import com.project4.com.service.dto.AuthorDTO;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
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
@RequestMapping("/api/author")
public class AuthorResource {

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

    private final AuthorService authorService;

    public AuthorResource(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * {@code GET /admin/users} : get all users with all the details - calling this are only allowed for the administrators.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    private boolean onlyContainsAllowedProperties(Pageable pageable) {
        return pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(ALLOWED_ORDERED_PROPERTIES::contains);
    }

    /**
     * {@code POST /api/readers} : create a new reader.
     *
     * @param authorDTO the reader data to be created.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reader.
     */
    @PostMapping
    public ResponseEntity<AuthorDTO> create(@RequestBody AuthorDTO authorDTO) {
        LOG.debug("REST request to save a new author");

        // Call the service layer to add the reader
        AuthorDTO result = authorService.addAuthor(authorDTO);

        // Set the URI for the newly created reader
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getMaTacGia()).toUri();

        // Return a ResponseEntity with status 201 (Created)
        return ResponseEntity.created(location).body(result);
    }

    @PutMapping
    public ResponseEntity<AuthorDTO> update(@RequestBody AuthorDTO authorDTO) throws InvalidInputException {
        LOG.debug("REST request to update");

        // Call the service layer to add the reader
        var result = authorService.update(authorDTO);

        return ResponseUtil.wrapOrNotFound(
            Optional.of(result),
            HeaderUtil.createAlert(applicationName, "reader.updated", String.valueOf(authorDTO.getMaTacGia()))
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AuthorDTO> delete(@PathVariable("id") Integer authorId) throws InvalidInputException {
        LOG.debug("REST request to delete id=" + authorId);

        // Call the service layer to add the reader
        authorService.delete(authorId);

        return ResponseEntity.noContent()
            .headers(HeaderUtil.createAlert(applicationName, "reader.deleted", String.valueOf(authorId)))
            .build();
    }

    @GetMapping
    public ResponseEntity<Page<AuthorDTO>> search(@ParameterObject Pageable pageable, String query) {
        LOG.debug("REST request to search books");

        final Page<AuthorDTO> page = authorService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
}
