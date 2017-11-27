package cn.ctodb.msp.web.rest;

import cn.ctodb.msp.MspApp;

import cn.ctodb.msp.domain.AppMenu;
import cn.ctodb.msp.repository.AppMenuRepository;
import cn.ctodb.msp.service.AppMenuService;
import cn.ctodb.msp.repository.search.AppMenuSearchRepository;
import cn.ctodb.msp.service.dto.AppMenuDTO;
import cn.ctodb.msp.service.mapper.AppMenuMapper;
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

import cn.ctodb.msp.domain.enumeration.ReqMode;
import cn.ctodb.msp.domain.enumeration.AppLevel;
/**
 * Test class for the AppMenuResource REST controller.
 *
 * @see AppMenuResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MspApp.class)
public class AppMenuResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final ReqMode DEFAULT_REQ_MODE = ReqMode.URL;
    private static final ReqMode UPDATED_REQ_MODE = ReqMode.URL;

    private static final String DEFAULT_REQ_ADDR = "AAAAAAAAAA";
    private static final String UPDATED_REQ_ADDR = "BBBBBBBBBB";

    private static final AppLevel DEFAULT_APP_LEVEL = AppLevel.SYS;
    private static final AppLevel UPDATED_APP_LEVEL = AppLevel.USER;

    private static final Integer DEFAULT_SORT = 1;
    private static final Integer UPDATED_SORT = 2;

    private static final String DEFAULT_ICON_1 = "AAAAAAAAAA";
    private static final String UPDATED_ICON_1 = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private AppMenuRepository appMenuRepository;

    @Autowired
    private AppMenuMapper appMenuMapper;

    @Autowired
    private AppMenuService appMenuService;

    @Autowired
    private AppMenuSearchRepository appMenuSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAppMenuMockMvc;

    private AppMenu appMenu;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AppMenuResource appMenuResource = new AppMenuResource(appMenuService);
        this.restAppMenuMockMvc = MockMvcBuilders.standaloneSetup(appMenuResource)
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
    public static AppMenu createEntity(EntityManager em) {
        AppMenu appMenu = new AppMenu()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .reqMode(DEFAULT_REQ_MODE)
            .reqAddr(DEFAULT_REQ_ADDR)
            .appLevel(DEFAULT_APP_LEVEL)
            .sort(DEFAULT_SORT)
            .icon1(DEFAULT_ICON_1)
            .status(DEFAULT_STATUS);
        return appMenu;
    }

    @Before
    public void initTest() {
        appMenuSearchRepository.deleteAll();
        appMenu = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppMenu() throws Exception {
        int databaseSizeBeforeCreate = appMenuRepository.findAll().size();

        // Create the AppMenu
        AppMenuDTO appMenuDTO = appMenuMapper.toDto(appMenu);
        restAppMenuMockMvc.perform(post("/api/app-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appMenuDTO)))
            .andExpect(status().isCreated());

        // Validate the AppMenu in the database
        List<AppMenu> appMenuList = appMenuRepository.findAll();
        assertThat(appMenuList).hasSize(databaseSizeBeforeCreate + 1);
        AppMenu testAppMenu = appMenuList.get(appMenuList.size() - 1);
        assertThat(testAppMenu.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testAppMenu.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAppMenu.getReqMode()).isEqualTo(DEFAULT_REQ_MODE);
        assertThat(testAppMenu.getReqAddr()).isEqualTo(DEFAULT_REQ_ADDR);
        assertThat(testAppMenu.getAppLevel()).isEqualTo(DEFAULT_APP_LEVEL);
        assertThat(testAppMenu.getSort()).isEqualTo(DEFAULT_SORT);
        assertThat(testAppMenu.getIcon1()).isEqualTo(DEFAULT_ICON_1);
        assertThat(testAppMenu.getStatus()).isEqualTo(DEFAULT_STATUS);

        // Validate the AppMenu in Elasticsearch
        AppMenu appMenuEs = appMenuSearchRepository.findOne(testAppMenu.getId());
        assertThat(appMenuEs).isEqualToComparingFieldByField(testAppMenu);
    }

    @Test
    @Transactional
    public void createAppMenuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appMenuRepository.findAll().size();

        // Create the AppMenu with an existing ID
        appMenu.setId(1L);
        AppMenuDTO appMenuDTO = appMenuMapper.toDto(appMenu);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppMenuMockMvc.perform(post("/api/app-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appMenuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppMenu in the database
        List<AppMenu> appMenuList = appMenuRepository.findAll();
        assertThat(appMenuList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAppMenus() throws Exception {
        // Initialize the database
        appMenuRepository.saveAndFlush(appMenu);

        // Get all the appMenuList
        restAppMenuMockMvc.perform(get("/api/app-menus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appMenu.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].reqMode").value(hasItem(DEFAULT_REQ_MODE.toString())))
            .andExpect(jsonPath("$.[*].reqAddr").value(hasItem(DEFAULT_REQ_ADDR.toString())))
            .andExpect(jsonPath("$.[*].appLevel").value(hasItem(DEFAULT_APP_LEVEL.toString())))
            .andExpect(jsonPath("$.[*].sort").value(hasItem(DEFAULT_SORT)))
            .andExpect(jsonPath("$.[*].icon1").value(hasItem(DEFAULT_ICON_1.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getAppMenu() throws Exception {
        // Initialize the database
        appMenuRepository.saveAndFlush(appMenu);

        // Get the appMenu
        restAppMenuMockMvc.perform(get("/api/app-menus/{id}", appMenu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(appMenu.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.reqMode").value(DEFAULT_REQ_MODE.toString()))
            .andExpect(jsonPath("$.reqAddr").value(DEFAULT_REQ_ADDR.toString()))
            .andExpect(jsonPath("$.appLevel").value(DEFAULT_APP_LEVEL.toString()))
            .andExpect(jsonPath("$.sort").value(DEFAULT_SORT))
            .andExpect(jsonPath("$.icon1").value(DEFAULT_ICON_1.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAppMenu() throws Exception {
        // Get the appMenu
        restAppMenuMockMvc.perform(get("/api/app-menus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppMenu() throws Exception {
        // Initialize the database
        appMenuRepository.saveAndFlush(appMenu);
        appMenuSearchRepository.save(appMenu);
        int databaseSizeBeforeUpdate = appMenuRepository.findAll().size();

        // Update the appMenu
        AppMenu updatedAppMenu = appMenuRepository.findOne(appMenu.getId());
        updatedAppMenu
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .reqMode(UPDATED_REQ_MODE)
            .reqAddr(UPDATED_REQ_ADDR)
            .appLevel(UPDATED_APP_LEVEL)
            .sort(UPDATED_SORT)
            .icon1(UPDATED_ICON_1)
            .status(UPDATED_STATUS);
        AppMenuDTO appMenuDTO = appMenuMapper.toDto(updatedAppMenu);

        restAppMenuMockMvc.perform(put("/api/app-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appMenuDTO)))
            .andExpect(status().isOk());

        // Validate the AppMenu in the database
        List<AppMenu> appMenuList = appMenuRepository.findAll();
        assertThat(appMenuList).hasSize(databaseSizeBeforeUpdate);
        AppMenu testAppMenu = appMenuList.get(appMenuList.size() - 1);
        assertThat(testAppMenu.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testAppMenu.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAppMenu.getReqMode()).isEqualTo(UPDATED_REQ_MODE);
        assertThat(testAppMenu.getReqAddr()).isEqualTo(UPDATED_REQ_ADDR);
        assertThat(testAppMenu.getAppLevel()).isEqualTo(UPDATED_APP_LEVEL);
        assertThat(testAppMenu.getSort()).isEqualTo(UPDATED_SORT);
        assertThat(testAppMenu.getIcon1()).isEqualTo(UPDATED_ICON_1);
        assertThat(testAppMenu.getStatus()).isEqualTo(UPDATED_STATUS);

        // Validate the AppMenu in Elasticsearch
        AppMenu appMenuEs = appMenuSearchRepository.findOne(testAppMenu.getId());
        assertThat(appMenuEs).isEqualToComparingFieldByField(testAppMenu);
    }

    @Test
    @Transactional
    public void updateNonExistingAppMenu() throws Exception {
        int databaseSizeBeforeUpdate = appMenuRepository.findAll().size();

        // Create the AppMenu
        AppMenuDTO appMenuDTO = appMenuMapper.toDto(appMenu);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAppMenuMockMvc.perform(put("/api/app-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appMenuDTO)))
            .andExpect(status().isCreated());

        // Validate the AppMenu in the database
        List<AppMenu> appMenuList = appMenuRepository.findAll();
        assertThat(appMenuList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAppMenu() throws Exception {
        // Initialize the database
        appMenuRepository.saveAndFlush(appMenu);
        appMenuSearchRepository.save(appMenu);
        int databaseSizeBeforeDelete = appMenuRepository.findAll().size();

        // Get the appMenu
        restAppMenuMockMvc.perform(delete("/api/app-menus/{id}", appMenu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean appMenuExistsInEs = appMenuSearchRepository.exists(appMenu.getId());
        assertThat(appMenuExistsInEs).isFalse();

        // Validate the database is empty
        List<AppMenu> appMenuList = appMenuRepository.findAll();
        assertThat(appMenuList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchAppMenu() throws Exception {
        // Initialize the database
        appMenuRepository.saveAndFlush(appMenu);
        appMenuSearchRepository.save(appMenu);

        // Search the appMenu
        restAppMenuMockMvc.perform(get("/api/_search/app-menus?query=id:" + appMenu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appMenu.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].reqMode").value(hasItem(DEFAULT_REQ_MODE.toString())))
            .andExpect(jsonPath("$.[*].reqAddr").value(hasItem(DEFAULT_REQ_ADDR.toString())))
            .andExpect(jsonPath("$.[*].appLevel").value(hasItem(DEFAULT_APP_LEVEL.toString())))
            .andExpect(jsonPath("$.[*].sort").value(hasItem(DEFAULT_SORT)))
            .andExpect(jsonPath("$.[*].icon1").value(hasItem(DEFAULT_ICON_1.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppMenu.class);
        AppMenu appMenu1 = new AppMenu();
        appMenu1.setId(1L);
        AppMenu appMenu2 = new AppMenu();
        appMenu2.setId(appMenu1.getId());
        assertThat(appMenu1).isEqualTo(appMenu2);
        appMenu2.setId(2L);
        assertThat(appMenu1).isNotEqualTo(appMenu2);
        appMenu1.setId(null);
        assertThat(appMenu1).isNotEqualTo(appMenu2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppMenuDTO.class);
        AppMenuDTO appMenuDTO1 = new AppMenuDTO();
        appMenuDTO1.setId(1L);
        AppMenuDTO appMenuDTO2 = new AppMenuDTO();
        assertThat(appMenuDTO1).isNotEqualTo(appMenuDTO2);
        appMenuDTO2.setId(appMenuDTO1.getId());
        assertThat(appMenuDTO1).isEqualTo(appMenuDTO2);
        appMenuDTO2.setId(2L);
        assertThat(appMenuDTO1).isNotEqualTo(appMenuDTO2);
        appMenuDTO1.setId(null);
        assertThat(appMenuDTO1).isNotEqualTo(appMenuDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(appMenuMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(appMenuMapper.fromId(null)).isNull();
    }
}
