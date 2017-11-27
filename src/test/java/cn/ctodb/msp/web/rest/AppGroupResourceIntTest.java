package cn.ctodb.msp.web.rest;

import cn.ctodb.msp.MspApp;

import cn.ctodb.msp.domain.AppGroup;
import cn.ctodb.msp.repository.AppGroupRepository;
import cn.ctodb.msp.service.AppGroupService;
import cn.ctodb.msp.repository.search.AppGroupSearchRepository;
import cn.ctodb.msp.service.dto.AppGroupDTO;
import cn.ctodb.msp.service.mapper.AppGroupMapper;
import cn.ctodb.msp.web.rest.errors.ExceptionTranslator;

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

import static cn.ctodb.msp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AppGroupResource REST controller.
 *
 * @see AppGroupResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MspApp.class)
public class AppGroupResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_SORT = 1;
    private static final Integer UPDATED_SORT = 2;

    private static final String DEFAULT_ICON_1 = "AAAAAAAAAA";
    private static final String UPDATED_ICON_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ICON_2 = "AAAAAAAAAA";
    private static final String UPDATED_ICON_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ICON_3 = "AAAAAAAAAA";
    private static final String UPDATED_ICON_3 = "BBBBBBBBBB";

    private static final String DEFAULT_DEF_1 = "AAAAAAAAAA";
    private static final String UPDATED_DEF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_DEF_2 = "AAAAAAAAAA";
    private static final String UPDATED_DEF_2 = "BBBBBBBBBB";

    private static final String DEFAULT_DEF_3 = "AAAAAAAAAA";
    private static final String UPDATED_DEF_3 = "BBBBBBBBBB";

    private static final String DEFAULT_DEF_4 = "AAAAAAAAAA";
    private static final String UPDATED_DEF_4 = "BBBBBBBBBB";

    private static final String DEFAULT_DEF_5 = "AAAAAAAAAA";
    private static final String UPDATED_DEF_5 = "BBBBBBBBBB";

    private static final String DEFAULT_DEF_6 = "AAAAAAAAAA";
    private static final String UPDATED_DEF_6 = "BBBBBBBBBB";

    private static final String DEFAULT_DEF_7 = "AAAAAAAAAA";
    private static final String UPDATED_DEF_7 = "BBBBBBBBBB";

    private static final String DEFAULT_DEF_8 = "AAAAAAAAAA";
    private static final String UPDATED_DEF_8 = "BBBBBBBBBB";

    private static final String DEFAULT_DEF_9 = "AAAAAAAAAA";
    private static final String UPDATED_DEF_9 = "BBBBBBBBBB";

    @Autowired
    private AppGroupRepository appGroupRepository;

    @Autowired
    private AppGroupMapper appGroupMapper;

    @Autowired
    private AppGroupService appGroupService;

    @Autowired
    private AppGroupSearchRepository appGroupSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAppGroupMockMvc;

    private AppGroup appGroup;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AppGroupResource appGroupResource = new AppGroupResource(appGroupService);
        this.restAppGroupMockMvc = MockMvcBuilders.standaloneSetup(appGroupResource)
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
    public static AppGroup createEntity(EntityManager em) {
        AppGroup appGroup = new AppGroup()
            .name(DEFAULT_NAME)
            .sort(DEFAULT_SORT)
            .icon1(DEFAULT_ICON_1)
            .icon2(DEFAULT_ICON_2)
            .icon3(DEFAULT_ICON_3)
            .def1(DEFAULT_DEF_1)
            .def2(DEFAULT_DEF_2)
            .def3(DEFAULT_DEF_3)
            .def4(DEFAULT_DEF_4)
            .def5(DEFAULT_DEF_5)
            .def6(DEFAULT_DEF_6)
            .def7(DEFAULT_DEF_7)
            .def8(DEFAULT_DEF_8)
            .def9(DEFAULT_DEF_9);
        return appGroup;
    }

    @Before
    public void initTest() {
        appGroupSearchRepository.deleteAll();
        appGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppGroup() throws Exception {
        int databaseSizeBeforeCreate = appGroupRepository.findAll().size();

        // Create the AppGroup
        AppGroupDTO appGroupDTO = appGroupMapper.toDto(appGroup);
        restAppGroupMockMvc.perform(post("/api/app-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the AppGroup in the database
        List<AppGroup> appGroupList = appGroupRepository.findAll();
        assertThat(appGroupList).hasSize(databaseSizeBeforeCreate + 1);
        AppGroup testAppGroup = appGroupList.get(appGroupList.size() - 1);
        assertThat(testAppGroup.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAppGroup.getSort()).isEqualTo(DEFAULT_SORT);
        assertThat(testAppGroup.getIcon1()).isEqualTo(DEFAULT_ICON_1);
        assertThat(testAppGroup.getIcon2()).isEqualTo(DEFAULT_ICON_2);
        assertThat(testAppGroup.getIcon3()).isEqualTo(DEFAULT_ICON_3);
        assertThat(testAppGroup.getDef1()).isEqualTo(DEFAULT_DEF_1);
        assertThat(testAppGroup.getDef2()).isEqualTo(DEFAULT_DEF_2);
        assertThat(testAppGroup.getDef3()).isEqualTo(DEFAULT_DEF_3);
        assertThat(testAppGroup.getDef4()).isEqualTo(DEFAULT_DEF_4);
        assertThat(testAppGroup.getDef5()).isEqualTo(DEFAULT_DEF_5);
        assertThat(testAppGroup.getDef6()).isEqualTo(DEFAULT_DEF_6);
        assertThat(testAppGroup.getDef7()).isEqualTo(DEFAULT_DEF_7);
        assertThat(testAppGroup.getDef8()).isEqualTo(DEFAULT_DEF_8);
        assertThat(testAppGroup.getDef9()).isEqualTo(DEFAULT_DEF_9);

        // Validate the AppGroup in Elasticsearch
        AppGroup appGroupEs = appGroupSearchRepository.findOne(testAppGroup.getId());
        assertThat(appGroupEs).isEqualToComparingFieldByField(testAppGroup);
    }

    @Test
    @Transactional
    public void createAppGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appGroupRepository.findAll().size();

        // Create the AppGroup with an existing ID
        appGroup.setId(1L);
        AppGroupDTO appGroupDTO = appGroupMapper.toDto(appGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppGroupMockMvc.perform(post("/api/app-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppGroup in the database
        List<AppGroup> appGroupList = appGroupRepository.findAll();
        assertThat(appGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAppGroups() throws Exception {
        // Initialize the database
        appGroupRepository.saveAndFlush(appGroup);

        // Get all the appGroupList
        restAppGroupMockMvc.perform(get("/api/app-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sort").value(hasItem(DEFAULT_SORT)))
            .andExpect(jsonPath("$.[*].icon1").value(hasItem(DEFAULT_ICON_1.toString())))
            .andExpect(jsonPath("$.[*].icon2").value(hasItem(DEFAULT_ICON_2.toString())))
            .andExpect(jsonPath("$.[*].icon3").value(hasItem(DEFAULT_ICON_3.toString())))
            .andExpect(jsonPath("$.[*].def1").value(hasItem(DEFAULT_DEF_1.toString())))
            .andExpect(jsonPath("$.[*].def2").value(hasItem(DEFAULT_DEF_2.toString())))
            .andExpect(jsonPath("$.[*].def3").value(hasItem(DEFAULT_DEF_3.toString())))
            .andExpect(jsonPath("$.[*].def4").value(hasItem(DEFAULT_DEF_4.toString())))
            .andExpect(jsonPath("$.[*].def5").value(hasItem(DEFAULT_DEF_5.toString())))
            .andExpect(jsonPath("$.[*].def6").value(hasItem(DEFAULT_DEF_6.toString())))
            .andExpect(jsonPath("$.[*].def7").value(hasItem(DEFAULT_DEF_7.toString())))
            .andExpect(jsonPath("$.[*].def8").value(hasItem(DEFAULT_DEF_8.toString())))
            .andExpect(jsonPath("$.[*].def9").value(hasItem(DEFAULT_DEF_9.toString())));
    }

    @Test
    @Transactional
    public void getAppGroup() throws Exception {
        // Initialize the database
        appGroupRepository.saveAndFlush(appGroup);

        // Get the appGroup
        restAppGroupMockMvc.perform(get("/api/app-groups/{id}", appGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(appGroup.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.sort").value(DEFAULT_SORT))
            .andExpect(jsonPath("$.icon1").value(DEFAULT_ICON_1.toString()))
            .andExpect(jsonPath("$.icon2").value(DEFAULT_ICON_2.toString()))
            .andExpect(jsonPath("$.icon3").value(DEFAULT_ICON_3.toString()))
            .andExpect(jsonPath("$.def1").value(DEFAULT_DEF_1.toString()))
            .andExpect(jsonPath("$.def2").value(DEFAULT_DEF_2.toString()))
            .andExpect(jsonPath("$.def3").value(DEFAULT_DEF_3.toString()))
            .andExpect(jsonPath("$.def4").value(DEFAULT_DEF_4.toString()))
            .andExpect(jsonPath("$.def5").value(DEFAULT_DEF_5.toString()))
            .andExpect(jsonPath("$.def6").value(DEFAULT_DEF_6.toString()))
            .andExpect(jsonPath("$.def7").value(DEFAULT_DEF_7.toString()))
            .andExpect(jsonPath("$.def8").value(DEFAULT_DEF_8.toString()))
            .andExpect(jsonPath("$.def9").value(DEFAULT_DEF_9.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAppGroup() throws Exception {
        // Get the appGroup
        restAppGroupMockMvc.perform(get("/api/app-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppGroup() throws Exception {
        // Initialize the database
        appGroupRepository.saveAndFlush(appGroup);
        appGroupSearchRepository.save(appGroup);
        int databaseSizeBeforeUpdate = appGroupRepository.findAll().size();

        // Update the appGroup
        AppGroup updatedAppGroup = appGroupRepository.findOne(appGroup.getId());
        updatedAppGroup
            .name(UPDATED_NAME)
            .sort(UPDATED_SORT)
            .icon1(UPDATED_ICON_1)
            .icon2(UPDATED_ICON_2)
            .icon3(UPDATED_ICON_3)
            .def1(UPDATED_DEF_1)
            .def2(UPDATED_DEF_2)
            .def3(UPDATED_DEF_3)
            .def4(UPDATED_DEF_4)
            .def5(UPDATED_DEF_5)
            .def6(UPDATED_DEF_6)
            .def7(UPDATED_DEF_7)
            .def8(UPDATED_DEF_8)
            .def9(UPDATED_DEF_9);
        AppGroupDTO appGroupDTO = appGroupMapper.toDto(updatedAppGroup);

        restAppGroupMockMvc.perform(put("/api/app-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appGroupDTO)))
            .andExpect(status().isOk());

        // Validate the AppGroup in the database
        List<AppGroup> appGroupList = appGroupRepository.findAll();
        assertThat(appGroupList).hasSize(databaseSizeBeforeUpdate);
        AppGroup testAppGroup = appGroupList.get(appGroupList.size() - 1);
        assertThat(testAppGroup.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAppGroup.getSort()).isEqualTo(UPDATED_SORT);
        assertThat(testAppGroup.getIcon1()).isEqualTo(UPDATED_ICON_1);
        assertThat(testAppGroup.getIcon2()).isEqualTo(UPDATED_ICON_2);
        assertThat(testAppGroup.getIcon3()).isEqualTo(UPDATED_ICON_3);
        assertThat(testAppGroup.getDef1()).isEqualTo(UPDATED_DEF_1);
        assertThat(testAppGroup.getDef2()).isEqualTo(UPDATED_DEF_2);
        assertThat(testAppGroup.getDef3()).isEqualTo(UPDATED_DEF_3);
        assertThat(testAppGroup.getDef4()).isEqualTo(UPDATED_DEF_4);
        assertThat(testAppGroup.getDef5()).isEqualTo(UPDATED_DEF_5);
        assertThat(testAppGroup.getDef6()).isEqualTo(UPDATED_DEF_6);
        assertThat(testAppGroup.getDef7()).isEqualTo(UPDATED_DEF_7);
        assertThat(testAppGroup.getDef8()).isEqualTo(UPDATED_DEF_8);
        assertThat(testAppGroup.getDef9()).isEqualTo(UPDATED_DEF_9);

        // Validate the AppGroup in Elasticsearch
        AppGroup appGroupEs = appGroupSearchRepository.findOne(testAppGroup.getId());
        assertThat(appGroupEs).isEqualToComparingFieldByField(testAppGroup);
    }

    @Test
    @Transactional
    public void updateNonExistingAppGroup() throws Exception {
        int databaseSizeBeforeUpdate = appGroupRepository.findAll().size();

        // Create the AppGroup
        AppGroupDTO appGroupDTO = appGroupMapper.toDto(appGroup);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAppGroupMockMvc.perform(put("/api/app-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the AppGroup in the database
        List<AppGroup> appGroupList = appGroupRepository.findAll();
        assertThat(appGroupList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAppGroup() throws Exception {
        // Initialize the database
        appGroupRepository.saveAndFlush(appGroup);
        appGroupSearchRepository.save(appGroup);
        int databaseSizeBeforeDelete = appGroupRepository.findAll().size();

        // Get the appGroup
        restAppGroupMockMvc.perform(delete("/api/app-groups/{id}", appGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean appGroupExistsInEs = appGroupSearchRepository.exists(appGroup.getId());
        assertThat(appGroupExistsInEs).isFalse();

        // Validate the database is empty
        List<AppGroup> appGroupList = appGroupRepository.findAll();
        assertThat(appGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchAppGroup() throws Exception {
        // Initialize the database
        appGroupRepository.saveAndFlush(appGroup);
        appGroupSearchRepository.save(appGroup);

        // Search the appGroup
        restAppGroupMockMvc.perform(get("/api/_search/app-groups?query=id:" + appGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sort").value(hasItem(DEFAULT_SORT)))
            .andExpect(jsonPath("$.[*].icon1").value(hasItem(DEFAULT_ICON_1.toString())))
            .andExpect(jsonPath("$.[*].icon2").value(hasItem(DEFAULT_ICON_2.toString())))
            .andExpect(jsonPath("$.[*].icon3").value(hasItem(DEFAULT_ICON_3.toString())))
            .andExpect(jsonPath("$.[*].def1").value(hasItem(DEFAULT_DEF_1.toString())))
            .andExpect(jsonPath("$.[*].def2").value(hasItem(DEFAULT_DEF_2.toString())))
            .andExpect(jsonPath("$.[*].def3").value(hasItem(DEFAULT_DEF_3.toString())))
            .andExpect(jsonPath("$.[*].def4").value(hasItem(DEFAULT_DEF_4.toString())))
            .andExpect(jsonPath("$.[*].def5").value(hasItem(DEFAULT_DEF_5.toString())))
            .andExpect(jsonPath("$.[*].def6").value(hasItem(DEFAULT_DEF_6.toString())))
            .andExpect(jsonPath("$.[*].def7").value(hasItem(DEFAULT_DEF_7.toString())))
            .andExpect(jsonPath("$.[*].def8").value(hasItem(DEFAULT_DEF_8.toString())))
            .andExpect(jsonPath("$.[*].def9").value(hasItem(DEFAULT_DEF_9.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppGroup.class);
        AppGroup appGroup1 = new AppGroup();
        appGroup1.setId(1L);
        AppGroup appGroup2 = new AppGroup();
        appGroup2.setId(appGroup1.getId());
        assertThat(appGroup1).isEqualTo(appGroup2);
        appGroup2.setId(2L);
        assertThat(appGroup1).isNotEqualTo(appGroup2);
        appGroup1.setId(null);
        assertThat(appGroup1).isNotEqualTo(appGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppGroupDTO.class);
        AppGroupDTO appGroupDTO1 = new AppGroupDTO();
        appGroupDTO1.setId(1L);
        AppGroupDTO appGroupDTO2 = new AppGroupDTO();
        assertThat(appGroupDTO1).isNotEqualTo(appGroupDTO2);
        appGroupDTO2.setId(appGroupDTO1.getId());
        assertThat(appGroupDTO1).isEqualTo(appGroupDTO2);
        appGroupDTO2.setId(2L);
        assertThat(appGroupDTO1).isNotEqualTo(appGroupDTO2);
        appGroupDTO1.setId(null);
        assertThat(appGroupDTO1).isNotEqualTo(appGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(appGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(appGroupMapper.fromId(null)).isNull();
    }
}
