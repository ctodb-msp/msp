package cn.ctodb.msp.web.rest;

import com.codahale.metrics.annotation.Timed;
import cn.ctodb.msp.service.AppGroupService;
import cn.ctodb.msp.web.rest.errors.BadRequestAlertException;
import cn.ctodb.msp.web.rest.util.HeaderUtil;
import cn.ctodb.msp.web.rest.util.PaginationUtil;
import cn.ctodb.msp.service.dto.AppGroupDTO;
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
 * REST controller for managing AppGroup.
 */
@RestController
@RequestMapping("/api")
public class AppGroupResource {

    private final Logger log = LoggerFactory.getLogger(AppGroupResource.class);

    private static final String ENTITY_NAME = "appGroup";

    private final AppGroupService appGroupService;

    public AppGroupResource(AppGroupService appGroupService) {
        this.appGroupService = appGroupService;
    }

    /**
     * POST  /app-groups : Create a new appGroup.
     *
     * @param appGroupDTO the appGroupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new appGroupDTO, or with status 400 (Bad Request) if the appGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/app-groups")
    @Timed
    public ResponseEntity<AppGroupDTO> createAppGroup(@RequestBody AppGroupDTO appGroupDTO) throws URISyntaxException {
        log.debug("REST request to save AppGroup : {}", appGroupDTO);
        if (appGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new appGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppGroupDTO result = appGroupService.save(appGroupDTO);
        return ResponseEntity.created(new URI("/api/app-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /app-groups : Updates an existing appGroup.
     *
     * @param appGroupDTO the appGroupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated appGroupDTO,
     * or with status 400 (Bad Request) if the appGroupDTO is not valid,
     * or with status 500 (Internal Server Error) if the appGroupDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/app-groups")
    @Timed
    public ResponseEntity<AppGroupDTO> updateAppGroup(@RequestBody AppGroupDTO appGroupDTO) throws URISyntaxException {
        log.debug("REST request to update AppGroup : {}", appGroupDTO);
        if (appGroupDTO.getId() == null) {
            return createAppGroup(appGroupDTO);
        }
        AppGroupDTO result = appGroupService.save(appGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, appGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /app-groups : get all the appGroups.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of appGroups in body
     */
    @GetMapping("/app-groups")
    @Timed
    public ResponseEntity<List<AppGroupDTO>> getAllAppGroups(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of AppGroups");
        Page<AppGroupDTO> page = appGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/app-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /app-groups/:id : get the "id" appGroup.
     *
     * @param id the id of the appGroupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the appGroupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/app-groups/{id}")
    @Timed
    public ResponseEntity<AppGroupDTO> getAppGroup(@PathVariable Long id) {
        log.debug("REST request to get AppGroup : {}", id);
        AppGroupDTO appGroupDTO = appGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(appGroupDTO));
    }

    /**
     * DELETE  /app-groups/:id : delete the "id" appGroup.
     *
     * @param id the id of the appGroupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/app-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteAppGroup(@PathVariable Long id) {
        log.debug("REST request to delete AppGroup : {}", id);
        appGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/app-groups?query=:query : search for the appGroup corresponding
     * to the query.
     *
     * @param query the query of the appGroup search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/app-groups")
    @Timed
    public ResponseEntity<List<AppGroupDTO>> searchAppGroups(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of AppGroups for query {}", query);
        Page<AppGroupDTO> page = appGroupService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/app-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
