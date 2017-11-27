package cn.ctodb.msp.web.rest;

import cn.ctodb.msp.MspApp;

import cn.ctodb.msp.domain.AppApps;
import cn.ctodb.msp.repository.AppAppsRepository;
import cn.ctodb.msp.service.AppAppsService;
import cn.ctodb.msp.repository.search.AppAppsSearchRepository;
import cn.ctodb.msp.service.dto.AppAppsDTO;
import cn.ctodb.msp.service.mapper.AppAppsMapper;
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

import cn.ctodb.msp.domain.enumeration.AppType;
import cn.ctodb.msp.domain.enumeration.ReqMode;
import cn.ctodb.msp.domain.enumeration.AppLevel;
import cn.ctodb.msp.domain.enumeration.AppPlatForm;
/**
 * Test class for the AppAppsResource REST controller.
 *
 * @see AppAppsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MspApp.class)
public class AppAppsResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final String DEFAULT_APP_DESC = "AAAAAAAAAA";
    private static final String UPDATED_APP_DESC = "BBBBBBBBBB";

    private static final AppType DEFAULT_APP_TYPE = AppType.APP;
    private static final AppType UPDATED_APP_TYPE = AppType.WEDGET;

    private static final ReqMode DEFAULT_REQ_MODE = ReqMode.URL;
    private static final ReqMode UPDATED_REQ_MODE = ReqMode.URL;

    private static final String DEFAULT_REQ_ADDR = "AAAAAAAAAA";
    private static final String UPDATED_REQ_ADDR = "BBBBBBBBBB";

    private static final AppLevel DEFAULT_APP_LEVEL = AppLevel.SYS;
    private static final AppLevel UPDATED_APP_LEVEL = AppLevel.USER;

    private static final AppPlatForm DEFAULT_PLATFORM = AppPlatForm.WEB;
    private static final AppPlatForm UPDATED_PLATFORM = AppPlatForm.APP;

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

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private AppAppsRepository appAppsRepository;

    @Autowired
    private AppAppsMapper appAppsMapper;

    @Autowired
    private AppAppsService appAppsService;

    @Autowired
    private AppAppsSearchRepository appAppsSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAppAppsMockMvc;

    private AppApps appApps;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AppAppsResource appAppsResource = new AppAppsResource(appAppsService);
        this.restAppAppsMockMvc = MockMvcBuilders.standaloneSetup(appAppsResource)
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
    public static AppApps createEntity(EntityManager em) {
        AppApps appApps = new AppApps()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .version(DEFAULT_VERSION)
            .appDesc(DEFAULT_APP_DESC)
            .appType(DEFAULT_APP_TYPE)
            .reqMode(DEFAULT_REQ_MODE)
            .reqAddr(DEFAULT_REQ_ADDR)
            .appLevel(DEFAULT_APP_LEVEL)
            .platform(DEFAULT_PLATFORM)
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
            .def9(DEFAULT_DEF_9)
            .status(DEFAULT_STATUS);
        return appApps;
    }

    @Before
    public void initTest() {
        appAppsSearchRepository.deleteAll();
        appApps = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppApps() throws Exception {
        int databaseSizeBeforeCreate = appAppsRepository.findAll().size();

        // Create the AppApps
        AppAppsDTO appAppsDTO = appAppsMapper.toDto(appApps);
        restAppAppsMockMvc.perform(post("/api/app-apps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appAppsDTO)))
            .andExpect(status().isCreated());

        // Validate the AppApps in the database
        List<AppApps> appAppsList = appAppsRepository.findAll();
        assertThat(appAppsList).hasSize(databaseSizeBeforeCreate + 1);
        AppApps testAppApps = appAppsList.get(appAppsList.size() - 1);
        assertThat(testAppApps.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testAppApps.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAppApps.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testAppApps.getAppDesc()).isEqualTo(DEFAULT_APP_DESC);
        assertThat(testAppApps.getAppType()).isEqualTo(DEFAULT_APP_TYPE);
        assertThat(testAppApps.getReqMode()).isEqualTo(DEFAULT_REQ_MODE);
        assertThat(testAppApps.getReqAddr()).isEqualTo(DEFAULT_REQ_ADDR);
        assertThat(testAppApps.getAppLevel()).isEqualTo(DEFAULT_APP_LEVEL);
        assertThat(testAppApps.getPlatform()).isEqualTo(DEFAULT_PLATFORM);
        assertThat(testAppApps.getSort()).isEqualTo(DEFAULT_SORT);
        assertThat(testAppApps.getIcon1()).isEqualTo(DEFAULT_ICON_1);
        assertThat(testAppApps.getIcon2()).isEqualTo(DEFAULT_ICON_2);
        assertThat(testAppApps.getIcon3()).isEqualTo(DEFAULT_ICON_3);
        assertThat(testAppApps.getDef1()).isEqualTo(DEFAULT_DEF_1);
        assertThat(testAppApps.getDef2()).isEqualTo(DEFAULT_DEF_2);
        assertThat(testAppApps.getDef3()).isEqualTo(DEFAULT_DEF_3);
        assertThat(testAppApps.getDef4()).isEqualTo(DEFAULT_DEF_4);
        assertThat(testAppApps.getDef5()).isEqualTo(DEFAULT_DEF_5);
        assertThat(testAppApps.getDef6()).isEqualTo(DEFAULT_DEF_6);
        assertThat(testAppApps.getDef7()).isEqualTo(DEFAULT_DEF_7);
        assertThat(testAppApps.getDef8()).isEqualTo(DEFAULT_DEF_8);
        assertThat(testAppApps.getDef9()).isEqualTo(DEFAULT_DEF_9);
        assertThat(testAppApps.getStatus()).isEqualTo(DEFAULT_STATUS);

        // Validate the AppApps in Elasticsearch
        AppApps appAppsEs = appAppsSearchRepository.findOne(testAppApps.getId());
        assertThat(appAppsEs).isEqualToComparingFieldByField(testAppApps);
    }

    @Test
    @Transactional
    public void createAppAppsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appAppsRepository.findAll().size();

        // Create the AppApps with an existing ID
        appApps.setId(1L);
        AppAppsDTO appAppsDTO = appAppsMapper.toDto(appApps);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppAppsMockMvc.perform(post("/api/app-apps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appAppsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppApps in the database
        List<AppApps> appAppsList = appAppsRepository.findAll();
        assertThat(appAppsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAppApps() throws Exception {
        // Initialize the database
        appAppsRepository.saveAndFlush(appApps);

        // Get all the appAppsList
        restAppAppsMockMvc.perform(get("/api/app-apps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appApps.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].appDesc").value(hasItem(DEFAULT_APP_DESC.toString())))
            .andExpect(jsonPath("$.[*].appType").value(hasItem(DEFAULT_APP_TYPE.toString())))
            .andExpect(jsonPath("$.[*].reqMode").value(hasItem(DEFAULT_REQ_MODE.toString())))
            .andExpect(jsonPath("$.[*].reqAddr").value(hasItem(DEFAULT_REQ_ADDR.toString())))
            .andExpect(jsonPath("$.[*].appLevel").value(hasItem(DEFAULT_APP_LEVEL.toString())))
            .andExpect(jsonPath("$.[*].platform").value(hasItem(DEFAULT_PLATFORM.toString())))
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
            .andExpect(jsonPath("$.[*].def9").value(hasItem(DEFAULT_DEF_9.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getAppApps() throws Exception {
        // Initialize the database
        appAppsRepository.saveAndFlush(appApps);

        // Get the appApps
        restAppAppsMockMvc.perform(get("/api/app-apps/{id}", appApps.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(appApps.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.appDesc").value(DEFAULT_APP_DESC.toString()))
            .andExpect(jsonPath("$.appType").value(DEFAULT_APP_TYPE.toString()))
            .andExpect(jsonPath("$.reqMode").value(DEFAULT_REQ_MODE.toString()))
            .andExpect(jsonPath("$.reqAddr").value(DEFAULT_REQ_ADDR.toString()))
            .andExpect(jsonPath("$.appLevel").value(DEFAULT_APP_LEVEL.toString()))
            .andExpect(jsonPath("$.platform").value(DEFAULT_PLATFORM.toString()))
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
            .andExpect(jsonPath("$.def9").value(DEFAULT_DEF_9.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAppApps() throws Exception {
        // Get the appApps
        restAppAppsMockMvc.perform(get("/api/app-apps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppApps() throws Exception {
        // Initialize the database
        appAppsRepository.saveAndFlush(appApps);
        appAppsSearchRepository.save(appApps);
        int databaseSizeBeforeUpdate = appAppsRepository.findAll().size();

        // Update the appApps
        AppApps updatedAppApps = appAppsRepository.findOne(appApps.getId());
        updatedAppApps
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .version(UPDATED_VERSION)
            .appDesc(UPDATED_APP_DESC)
            .appType(UPDATED_APP_TYPE)
            .reqMode(UPDATED_REQ_MODE)
            .reqAddr(UPDATED_REQ_ADDR)
            .appLevel(UPDATED_APP_LEVEL)
            .platform(UPDATED_PLATFORM)
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
            .def9(UPDATED_DEF_9)
            .status(UPDATED_STATUS);
        AppAppsDTO appAppsDTO = appAppsMapper.toDto(updatedAppApps);

        restAppAppsMockMvc.perform(put("/api/app-apps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appAppsDTO)))
            .andExpect(status().isOk());

        // Validate the AppApps in the database
        List<AppApps> appAppsList = appAppsRepository.findAll();
        assertThat(appAppsList).hasSize(databaseSizeBeforeUpdate);
        AppApps testAppApps = appAppsList.get(appAppsList.size() - 1);
        assertThat(testAppApps.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testAppApps.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAppApps.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testAppApps.getAppDesc()).isEqualTo(UPDATED_APP_DESC);
        assertThat(testAppApps.getAppType()).isEqualTo(UPDATED_APP_TYPE);
        assertThat(testAppApps.getReqMode()).isEqualTo(UPDATED_REQ_MODE);
        assertThat(testAppApps.getReqAddr()).isEqualTo(UPDATED_REQ_ADDR);
        assertThat(testAppApps.getAppLevel()).isEqualTo(UPDATED_APP_LEVEL);
        assertThat(testAppApps.getPlatform()).isEqualTo(UPDATED_PLATFORM);
        assertThat(testAppApps.getSort()).isEqualTo(UPDATED_SORT);
        assertThat(testAppApps.getIcon1()).isEqualTo(UPDATED_ICON_1);
        assertThat(testAppApps.getIcon2()).isEqualTo(UPDATED_ICON_2);
        assertThat(testAppApps.getIcon3()).isEqualTo(UPDATED_ICON_3);
        assertThat(testAppApps.getDef1()).isEqualTo(UPDATED_DEF_1);
        assertThat(testAppApps.getDef2()).isEqualTo(UPDATED_DEF_2);
        assertThat(testAppApps.getDef3()).isEqualTo(UPDATED_DEF_3);
        assertThat(testAppApps.getDef4()).isEqualTo(UPDATED_DEF_4);
        assertThat(testAppApps.getDef5()).isEqualTo(UPDATED_DEF_5);
        assertThat(testAppApps.getDef6()).isEqualTo(UPDATED_DEF_6);
        assertThat(testAppApps.getDef7()).isEqualTo(UPDATED_DEF_7);
        assertThat(testAppApps.getDef8()).isEqualTo(UPDATED_DEF_8);
        assertThat(testAppApps.getDef9()).isEqualTo(UPDATED_DEF_9);
        assertThat(testAppApps.getStatus()).isEqualTo(UPDATED_STATUS);

        // Validate the AppApps in Elasticsearch
        AppApps appAppsEs = appAppsSearchRepository.findOne(testAppApps.getId());
        assertThat(appAppsEs).isEqualToComparingFieldByField(testAppApps);
    }

    @Test
    @Transactional
    public void updateNonExistingAppApps() throws Exception {
        int databaseSizeBeforeUpdate = appAppsRepository.findAll().size();

        // Create the AppApps
        AppAppsDTO appAppsDTO = appAppsMapper.toDto(appApps);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAppAppsMockMvc.perform(put("/api/app-apps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appAppsDTO)))
            .andExpect(status().isCreated());

        // Validate the AppApps in the database
        List<AppApps> appAppsList = appAppsRepository.findAll();
        assertThat(appAppsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAppApps() throws Exception {
        // Initialize the database
        appAppsRepository.saveAndFlush(appApps);
        appAppsSearchRepository.save(appApps);
        int databaseSizeBeforeDelete = appAppsRepository.findAll().size();

        // Get the appApps
        restAppAppsMockMvc.perform(delete("/api/app-apps/{id}", appApps.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean appAppsExistsInEs = appAppsSearchRepository.exists(appApps.getId());
        assertThat(appAppsExistsInEs).isFalse();

        // Validate the database is empty
        List<AppApps> appAppsList = appAppsRepository.findAll();
        assertThat(appAppsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchAppApps() throws Exception {
        // Initialize the database
        appAppsRepository.saveAndFlush(appApps);
        appAppsSearchRepository.save(appApps);

        // Search the appApps
        restAppAppsMockMvc.perform(get("/api/_search/app-apps?query=id:" + appApps.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appApps.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].appDesc").value(hasItem(DEFAULT_APP_DESC.toString())))
            .andExpect(jsonPath("$.[*].appType").value(hasItem(DEFAULT_APP_TYPE.toString())))
            .andExpect(jsonPath("$.[*].reqMode").value(hasItem(DEFAULT_REQ_MODE.toString())))
            .andExpect(jsonPath("$.[*].reqAddr").value(hasItem(DEFAULT_REQ_ADDR.toString())))
            .andExpect(jsonPath("$.[*].appLevel").value(hasItem(DEFAULT_APP_LEVEL.toString())))
            .andExpect(jsonPath("$.[*].platform").value(hasItem(DEFAULT_PLATFORM.toString())))
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
            .andExpect(jsonPath("$.[*].def9").value(hasItem(DEFAULT_DEF_9.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppApps.class);
        AppApps appApps1 = new AppApps();
        appApps1.setId(1L);
        AppApps appApps2 = new AppApps();
        appApps2.setId(appApps1.getId());
        assertThat(appApps1).isEqualTo(appApps2);
        appApps2.setId(2L);
        assertThat(appApps1).isNotEqualTo(appApps2);
        appApps1.setId(null);
        assertThat(appApps1).isNotEqualTo(appApps2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppAppsDTO.class);
        AppAppsDTO appAppsDTO1 = new AppAppsDTO();
        appAppsDTO1.setId(1L);
        AppAppsDTO appAppsDTO2 = new AppAppsDTO();
        assertThat(appAppsDTO1).isNotEqualTo(appAppsDTO2);
        appAppsDTO2.setId(appAppsDTO1.getId());
        assertThat(appAppsDTO1).isEqualTo(appAppsDTO2);
        appAppsDTO2.setId(2L);
        assertThat(appAppsDTO1).isNotEqualTo(appAppsDTO2);
        appAppsDTO1.setId(null);
        assertThat(appAppsDTO1).isNotEqualTo(appAppsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(appAppsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(appAppsMapper.fromId(null)).isNull();
    }
}
