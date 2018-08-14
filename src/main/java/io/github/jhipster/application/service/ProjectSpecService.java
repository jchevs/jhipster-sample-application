package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.ProjectSpecDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ProjectSpec.
 */
public interface ProjectSpecService {

    /**
     * Save a projectSpec.
     *
     * @param projectSpecDTO the entity to save
     * @return the persisted entity
     */
    ProjectSpecDTO save(ProjectSpecDTO projectSpecDTO);

    /**
     * Get all the projectSpecs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProjectSpecDTO> findAll(Pageable pageable);


    /**
     * Get the "id" projectSpec.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProjectSpecDTO> findOne(Long id);

    /**
     * Delete the "id" projectSpec.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
