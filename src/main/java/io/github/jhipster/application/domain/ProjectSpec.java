package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ProjectSpec.
 */
@Entity
@Table(name = "project_spec")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProjectSpec implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "spec_name")
    private String specName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public ProjectSpec projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getSpecName() {
        return specName;
    }

    public ProjectSpec specName(String specName) {
        this.specName = specName;
        return this;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectSpec projectSpec = (ProjectSpec) o;
        if (projectSpec.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), projectSpec.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProjectSpec{" +
            "id=" + getId() +
            ", projectId='" + getProjectId() + "'" +
            ", specName='" + getSpecName() + "'" +
            "}";
    }
}
