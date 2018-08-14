package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ProjectSpecDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ProjectSpec and its DTO ProjectSpecDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectSpecMapper extends EntityMapper<ProjectSpecDTO, ProjectSpec> {



    default ProjectSpec fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProjectSpec projectSpec = new ProjectSpec();
        projectSpec.setId(id);
        return projectSpec;
    }
}
