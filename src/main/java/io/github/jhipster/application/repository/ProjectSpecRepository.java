package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ProjectSpec;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProjectSpec entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectSpecRepository extends JpaRepository<ProjectSpec, Long> {

}
