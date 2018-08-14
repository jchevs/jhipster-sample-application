package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.ProjectSpec;
import io.github.jhipster.application.repository.ProjectSpecRepository;
import io.github.jhipster.application.service.ProjectSpecService;
import io.github.jhipster.application.service.dto.ProjectSpecDTO;
import io.github.jhipster.application.service.mapper.ProjectSpecMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProjectSpecResource REST controller.
 *
 * @see ProjectSpecResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ProjectSpecResourceIntTest {

    private static final String DEFAULT_PROJECT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SPEC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPEC_NAME = "BBBBBBBBBB";

    @Autowired
    private ProjectSpecRepository projectSpecRepository;


    @Autowired
    private ProjectSpecMapper projectSpecMapper;
    

    @Autowired
    private ProjectSpecService projectSpecService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProjectSpecMockMvc;

    private ProjectSpec projectSpec;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProjectSpecResource projectSpecResource = new ProjectSpecResource(projectSpecService);
        this.restProjectSpecMockMvc = MockMvcBuilders.standaloneSetup(projectSpecResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectSpec createEntity(EntityManager em) {
        ProjectSpec projectSpec = new ProjectSpec()
            .projectId(DEFAULT_PROJECT_ID)
            .specName(DEFAULT_SPEC_NAME);
        return projectSpec;
    }

    @Before
    public void initTest() {
        projectSpec = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectSpec() throws Exception {
        int databaseSizeBeforeCreate = projectSpecRepository.findAll().size();

        // Create the ProjectSpec
        ProjectSpecDTO projectSpecDTO = projectSpecMapper.toDto(projectSpec);
        restProjectSpecMockMvc.perform(post("/api/project-specs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectSpecDTO)))
            .andExpect(status().isCreated());

        // Validate the ProjectSpec in the database
        List<ProjectSpec> projectSpecList = projectSpecRepository.findAll();
        assertThat(projectSpecList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectSpec testProjectSpec = projectSpecList.get(projectSpecList.size() - 1);
        assertThat(testProjectSpec.getProjectId()).isEqualTo(DEFAULT_PROJECT_ID);
        assertThat(testProjectSpec.getSpecName()).isEqualTo(DEFAULT_SPEC_NAME);
    }

    @Test
    @Transactional
    public void createProjectSpecWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectSpecRepository.findAll().size();

        // Create the ProjectSpec with an existing ID
        projectSpec.setId(1L);
        ProjectSpecDTO projectSpecDTO = projectSpecMapper.toDto(projectSpec);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectSpecMockMvc.perform(post("/api/project-specs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectSpecDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectSpec in the database
        List<ProjectSpec> projectSpecList = projectSpecRepository.findAll();
        assertThat(projectSpecList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProjectSpecs() throws Exception {
        // Initialize the database
        projectSpecRepository.saveAndFlush(projectSpec);

        // Get all the projectSpecList
        restProjectSpecMockMvc.perform(get("/api/project-specs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectSpec.getId().intValue())))
            .andExpect(jsonPath("$.[*].projectId").value(hasItem(DEFAULT_PROJECT_ID.toString())))
            .andExpect(jsonPath("$.[*].specName").value(hasItem(DEFAULT_SPEC_NAME.toString())));
    }
    

    @Test
    @Transactional
    public void getProjectSpec() throws Exception {
        // Initialize the database
        projectSpecRepository.saveAndFlush(projectSpec);

        // Get the projectSpec
        restProjectSpecMockMvc.perform(get("/api/project-specs/{id}", projectSpec.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectSpec.getId().intValue()))
            .andExpect(jsonPath("$.projectId").value(DEFAULT_PROJECT_ID.toString()))
            .andExpect(jsonPath("$.specName").value(DEFAULT_SPEC_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingProjectSpec() throws Exception {
        // Get the projectSpec
        restProjectSpecMockMvc.perform(get("/api/project-specs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectSpec() throws Exception {
        // Initialize the database
        projectSpecRepository.saveAndFlush(projectSpec);

        int databaseSizeBeforeUpdate = projectSpecRepository.findAll().size();

        // Update the projectSpec
        ProjectSpec updatedProjectSpec = projectSpecRepository.findById(projectSpec.getId()).get();
        // Disconnect from session so that the updates on updatedProjectSpec are not directly saved in db
        em.detach(updatedProjectSpec);
        updatedProjectSpec
            .projectId(UPDATED_PROJECT_ID)
            .specName(UPDATED_SPEC_NAME);
        ProjectSpecDTO projectSpecDTO = projectSpecMapper.toDto(updatedProjectSpec);

        restProjectSpecMockMvc.perform(put("/api/project-specs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectSpecDTO)))
            .andExpect(status().isOk());

        // Validate the ProjectSpec in the database
        List<ProjectSpec> projectSpecList = projectSpecRepository.findAll();
        assertThat(projectSpecList).hasSize(databaseSizeBeforeUpdate);
        ProjectSpec testProjectSpec = projectSpecList.get(projectSpecList.size() - 1);
        assertThat(testProjectSpec.getProjectId()).isEqualTo(UPDATED_PROJECT_ID);
        assertThat(testProjectSpec.getSpecName()).isEqualTo(UPDATED_SPEC_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectSpec() throws Exception {
        int databaseSizeBeforeUpdate = projectSpecRepository.findAll().size();

        // Create the ProjectSpec
        ProjectSpecDTO projectSpecDTO = projectSpecMapper.toDto(projectSpec);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restProjectSpecMockMvc.perform(put("/api/project-specs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectSpecDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectSpec in the database
        List<ProjectSpec> projectSpecList = projectSpecRepository.findAll();
        assertThat(projectSpecList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectSpec() throws Exception {
        // Initialize the database
        projectSpecRepository.saveAndFlush(projectSpec);

        int databaseSizeBeforeDelete = projectSpecRepository.findAll().size();

        // Get the projectSpec
        restProjectSpecMockMvc.perform(delete("/api/project-specs/{id}", projectSpec.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProjectSpec> projectSpecList = projectSpecRepository.findAll();
        assertThat(projectSpecList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectSpec.class);
        ProjectSpec projectSpec1 = new ProjectSpec();
        projectSpec1.setId(1L);
        ProjectSpec projectSpec2 = new ProjectSpec();
        projectSpec2.setId(projectSpec1.getId());
        assertThat(projectSpec1).isEqualTo(projectSpec2);
        projectSpec2.setId(2L);
        assertThat(projectSpec1).isNotEqualTo(projectSpec2);
        projectSpec1.setId(null);
        assertThat(projectSpec1).isNotEqualTo(projectSpec2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectSpecDTO.class);
        ProjectSpecDTO projectSpecDTO1 = new ProjectSpecDTO();
        projectSpecDTO1.setId(1L);
        ProjectSpecDTO projectSpecDTO2 = new ProjectSpecDTO();
        assertThat(projectSpecDTO1).isNotEqualTo(projectSpecDTO2);
        projectSpecDTO2.setId(projectSpecDTO1.getId());
        assertThat(projectSpecDTO1).isEqualTo(projectSpecDTO2);
        projectSpecDTO2.setId(2L);
        assertThat(projectSpecDTO1).isNotEqualTo(projectSpecDTO2);
        projectSpecDTO1.setId(null);
        assertThat(projectSpecDTO1).isNotEqualTo(projectSpecDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(projectSpecMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(projectSpecMapper.fromId(null)).isNull();
    }
}
