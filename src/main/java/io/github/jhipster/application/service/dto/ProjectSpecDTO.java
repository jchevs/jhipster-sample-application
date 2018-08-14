package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ProjectSpec entity.
 */
public class ProjectSpecDTO implements Serializable {

    private Long id;

    private String projectId;

    private String specName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectSpecDTO projectSpecDTO = (ProjectSpecDTO) o;
        if (projectSpecDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), projectSpecDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProjectSpecDTO{" +
            "id=" + getId() +
            ", projectId='" + getProjectId() + "'" +
            ", specName='" + getSpecName() + "'" +
            "}";
    }
}
