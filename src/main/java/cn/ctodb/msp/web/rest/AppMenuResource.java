package cn.ctodb.msp.web.rest;

import com.codahale.metrics.annotation.Timed;
import cn.ctodb.msp.service.AppMenuService;
import cn.ctodb.msp.web.rest.errors.BadRequestAlertException;
import cn.ctodb.msp.web.rest.util.HeaderUtil;
import cn.ctodb.msp.web.rest.util.PaginationUtil;
import cn.ctodb.msp.service.dto.AppMenuDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing AppMenu.
 */
@RestController
@RequestMapping("/api")
public class AppMenuResource {

    private final Logger log = LoggerFactory.getLogger(AppMenuResource.class);

    private static final String ENTITY_NAME = "appMenu";

    private final AppMenuService appMenuService;

    public AppMenuResource(AppMenuService appMenuService) {
        this.appMenuService = appMenuService;
    }

    /**
     * POST  /app-menus : Create a new appMenu.
     *
     * @param appMenuDTO the appMenuDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new appMenuDTO, or with status 400 (Bad Request) if the appMenu has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/app-menus")
    @Timed
    public ResponseEntity<AppMenuDTO> createAppMenu(@RequestBody AppMenuDTO appMenuDTO) throws URISyntaxException {
        log.debug("REST request to save AppMenu : {}", appMenuDTO);
        if (appMenuDTO.getId() != null) {
            throw new BadRequestAlertException("A new appMenu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppMenuDTO result = appMenuService.save(appMenuDTO);
        return ResponseEntity.created(new URI("/api/app-menus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /app-menus : Updates an existing appMenu.
     *
     * @param appMenuDTO the appMenuDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated appMenuDTO,
     * or with status 400 (Bad Request) if the appMenuDTO is not valid,
     * or with status 500 (Internal Server Error) if the appMenuDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/app-menus")
    @Timed
    public ResponseEntity<AppMenuDTO> updateAppMenu(@RequestBody AppMenuDTO appMenuDTO) throws URISyntaxException {
        log.debug("REST request to update AppMenu : {}", appMenuDTO);
        if (appMenuDTO.getId() == null) {
            return createAppMenu(appMenuDTO);
        }
        AppMenuDTO result = appMenuService.save(appMenuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, appMenuDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /app-menus : get all the appMenus.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of appMenus in body
     */
    @GetMapping("/app-menus")
    @Timed
    public ResponseEntity<List<AppMenuDTO>> getAllAppMenus(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of AppMenus");
        Page<AppMenuDTO> page = appMenuService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/app-menus");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /app-menus/:id : get the "id" appMenu.
     *
     * @param id the id of the appMenuDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the appMenuDTO, or with status 404 (Not Found)
     */
    @GetMapping("/app-menus/{id}")
    @Timed
    public ResponseEntity<AppMenuDTO> getAppMenu(@PathVariable Long id) {
        log.debug("REST request to get AppMenu : {}", id);
        AppMenuDTO appMenuDTO = appMenuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(appMenuDTO));
    }

    /**
     * DELETE  /app-menus/:id : delete the "id" appMenu.
     *
     * @param id the id of the appMenuDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/app-menus/{id}")
    @Timed
    public ResponseEntity<Void> deleteAppMenu(@PathVariable Long id) {
        log.debug("REST request to delete AppMenu : {}", id);
        appMenuService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/app-menus?query=:query : search for the appMenu corresponding
     * to the query.
     *
     * @param query the query of the appMenu search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/app-menus")
    @Timed
    public ResponseEntity<List<AppMenuDTO>> searchAppMenus(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of AppMenus for query {}", query);
        Page<AppMenuDTO> page = appMenuService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/app-menus");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
