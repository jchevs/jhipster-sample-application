package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.ProjectSpecService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.ProjectSpecDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ProjectSpec.
 */
@RestController
@RequestMapping("/api")
public class ProjectSpecResource {

    private final Logger log = LoggerFactory.getLogger(ProjectSpecResource.class);

    private static final String ENTITY_NAME = "projectSpec";

    private final ProjectSpecService projectSpecService;

    public ProjectSpecResource(ProjectSpecService projectSpecService) {
        this.projectSpecService = projectSpecService;
    }

    /**
     * POST  /project-specs : Create a new projectSpec.
     *
     * @param projectSpecDTO the projectSpecDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectSpecDTO, or with status 400 (Bad Request) if the projectSpec has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/project-specs")
    @Timed
    public ResponseEntity<ProjectSpecDTO> createProjectSpec(@RequestBody ProjectSpecDTO projectSpecDTO) throws URISyntaxException {
        log.debug("REST request to save ProjectSpec : {}", projectSpecDTO);
        if (projectSpecDTO.getId() != null) {
            throw new BadRequestAlertException("A new projectSpec cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectSpecDTO result = projectSpecService.save(projectSpecDTO);
        return ResponseEntity.created(new URI("/api/project-specs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /project-specs : Updates an existing projectSpec.
     *
     * @param projectSpecDTO the projectSpecDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectSpecDTO,
     * or with status 400 (Bad Request) if the projectSpecDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectSpecDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/project-specs")
    @Timed
    public ResponseEntity<ProjectSpecDTO> updateProjectSpec(@RequestBody ProjectSpecDTO projectSpecDTO) throws URISyntaxException {
        log.debug("REST request to update ProjectSpec : {}", projectSpecDTO);
        if (projectSpecDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjectSpecDTO result = projectSpecService.save(projectSpecDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectSpecDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /project-specs : get all the projectSpecs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of projectSpecs in body
     */
    @GetMapping("/project-specs")
    @Timed
    public ResponseEntity<List<ProjectSpecDTO>> getAllProjectSpecs(Pageable pageable) {
        log.debug("REST request to get a page of ProjectSpecs");
        Page<ProjectSpecDTO> page = projectSpecService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/project-specs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /project-specs/:id : get the "id" projectSpec.
     *
     * @param id the id of the projectSpecDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectSpecDTO, or with status 404 (Not Found)
     */
    @GetMapping("/project-specs/{id}")
    @Timed
    public ResponseEntity<ProjectSpecDTO> getProjectSpec(@PathVariable Long id) {
        log.debug("REST request to get ProjectSpec : {}", id);
        Optional<ProjectSpecDTO> projectSpecDTO = projectSpecService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectSpecDTO);
    }

    /**
     * DELETE  /project-specs/:id : delete the "id" projectSpec.
     *
     * @param id the id of the projectSpecDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/project-specs/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectSpec(@PathVariable Long id) {
        log.debug("REST request to delete ProjectSpec : {}", id);
        projectSpecService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
