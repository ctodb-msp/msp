package cn.ctodb.msp.web.rest;

import com.codahale.metrics.annotation.Timed;
import cn.ctodb.msp.service.AppAppsService;
import cn.ctodb.msp.web.rest.errors.BadRequestAlertException;
import cn.ctodb.msp.web.rest.util.HeaderUtil;
import cn.ctodb.msp.web.rest.util.PaginationUtil;
import cn.ctodb.msp.service.dto.AppAppsDTO;
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
 * REST controller for managing AppApps.
 */
@RestController
@RequestMapping("/api")
public class AppAppsResource {

    private final Logger log = LoggerFactory.getLogger(AppAppsResource.class);

    private static final String ENTITY_NAME = "appApps";

    private final AppAppsService appAppsService;

    public AppAppsResource(AppAppsService appAppsService) {
        this.appAppsService = appAppsService;
    }

    /**
     * POST  /app-apps : Create a new appApps.
     *
     * @param appAppsDTO the appAppsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new appAppsDTO, or with status 400 (Bad Request) if the appApps has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/app-apps")
    @Timed
    public ResponseEntity<AppAppsDTO> createAppApps(@RequestBody AppAppsDTO appAppsDTO) throws URISyntaxException {
        log.debug("REST request to save AppApps : {}", appAppsDTO);
        if (appAppsDTO.getId() != null) {
            throw new BadRequestAlertException("A new appApps cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppAppsDTO result = appAppsService.save(appAppsDTO);
        return ResponseEntity.created(new URI("/api/app-apps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /app-apps : Updates an existing appApps.
     *
     * @param appAppsDTO the appAppsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated appAppsDTO,
     * or with status 400 (Bad Request) if the appAppsDTO is not valid,
     * or with status 500 (Internal Server Error) if the appAppsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/app-apps")
    @Timed
    public ResponseEntity<AppAppsDTO> updateAppApps(@RequestBody AppAppsDTO appAppsDTO) throws URISyntaxException {
        log.debug("REST request to update AppApps : {}", appAppsDTO);
        if (appAppsDTO.getId() == null) {
            return createAppApps(appAppsDTO);
        }
        AppAppsDTO result = appAppsService.save(appAppsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, appAppsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /app-apps : get all the appApps.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of appApps in body
     */
    @GetMapping("/app-apps")
    @Timed
    public ResponseEntity<List<AppAppsDTO>> getAllAppApps(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of AppApps");
        Page<AppAppsDTO> page = appAppsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/app-apps");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /app-apps/:id : get the "id" appApps.
     *
     * @param id the id of the appAppsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the appAppsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/app-apps/{id}")
    @Timed
    public ResponseEntity<AppAppsDTO> getAppApps(@PathVariable Long id) {
        log.debug("REST request to get AppApps : {}", id);
        AppAppsDTO appAppsDTO = appAppsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(appAppsDTO));
    }

    /**
     * DELETE  /app-apps/:id : delete the "id" appApps.
     *
     * @param id the id of the appAppsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/app-apps/{id}")
    @Timed
    public ResponseEntity<Void> deleteAppApps(@PathVariable Long id) {
        log.debug("REST request to delete AppApps : {}", id);
        appAppsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/app-apps?query=:query : search for the appApps corresponding
     * to the query.
     *
     * @param query the query of the appApps search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/app-apps")
    @Timed
    public ResponseEntity<List<AppAppsDTO>> searchAppApps(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of AppApps for query {}", query);
        Page<AppAppsDTO> page = appAppsService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/app-apps");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
