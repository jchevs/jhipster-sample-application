package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ProjectSpecService;
import io.github.jhipster.application.domain.ProjectSpec;
import io.github.jhipster.application.repository.ProjectSpecRepository;
import io.github.jhipster.application.service.dto.ProjectSpecDTO;
import io.github.jhipster.application.service.mapper.ProjectSpecMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing ProjectSpec.
 */
@Service
@Transactional
public class ProjectSpecServiceImpl implements ProjectSpecService {

    private final Logger log = LoggerFactory.getLogger(ProjectSpecServiceImpl.class);

    private final ProjectSpecRepository projectSpecRepository;

    private final ProjectSpecMapper projectSpecMapper;

    public ProjectSpecServiceImpl(ProjectSpecRepository projectSpecRepository, ProjectSpecMapper projectSpecMapper) {
        this.projectSpecRepository = projectSpecRepository;
        this.projectSpecMapper = projectSpecMapper;
    }

    /**
     * Save a projectSpec.
     *
     * @param projectSpecDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectSpecDTO save(ProjectSpecDTO projectSpecDTO) {
        log.debug("Request to save ProjectSpec : {}", projectSpecDTO);
        ProjectSpec projectSpec = projectSpecMapper.toEntity(projectSpecDTO);
        projectSpec = projectSpecRepository.save(projectSpec);
        return projectSpecMapper.toDto(projectSpec);
    }

    /**
     * Get all the projectSpecs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProjectSpecDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProjectSpecs");
        return projectSpecRepository.findAll(pageable)
            .map(projectSpecMapper::toDto);
    }


    /**
     * Get one projectSpec by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectSpecDTO> findOne(Long id) {
        log.debug("Request to get ProjectSpec : {}", id);
        return projectSpecRepository.findById(id)
            .map(projectSpecMapper::toDto);
    }

    /**
     * Delete the projectSpec by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProjectSpec : {}", id);
        projectSpecRepository.deleteById(id);
    }
}
