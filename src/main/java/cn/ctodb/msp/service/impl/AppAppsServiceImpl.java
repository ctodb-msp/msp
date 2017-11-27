package cn.ctodb.msp.service.impl;

import cn.ctodb.msp.service.AppAppsService;
import cn.ctodb.msp.domain.AppApps;
import cn.ctodb.msp.repository.AppAppsRepository;
import cn.ctodb.msp.repository.search.AppAppsSearchRepository;
import cn.ctodb.msp.service.dto.AppAppsDTO;
import cn.ctodb.msp.service.mapper.AppAppsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AppApps.
 */
@Service
@Transactional
public class AppAppsServiceImpl implements AppAppsService{

    private final Logger log = LoggerFactory.getLogger(AppAppsServiceImpl.class);

    private final AppAppsRepository appAppsRepository;

    private final AppAppsMapper appAppsMapper;

    private final AppAppsSearchRepository appAppsSearchRepository;

    public AppAppsServiceImpl(AppAppsRepository appAppsRepository, AppAppsMapper appAppsMapper, AppAppsSearchRepository appAppsSearchRepository) {
        this.appAppsRepository = appAppsRepository;
        this.appAppsMapper = appAppsMapper;
        this.appAppsSearchRepository = appAppsSearchRepository;
    }

    /**
     * Save a appApps.
     *
     * @param appAppsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AppAppsDTO save(AppAppsDTO appAppsDTO) {
        log.debug("Request to save AppApps : {}", appAppsDTO);
        AppApps appApps = appAppsMapper.toEntity(appAppsDTO);
        appApps = appAppsRepository.save(appApps);
        AppAppsDTO result = appAppsMapper.toDto(appApps);
        appAppsSearchRepository.save(appApps);
        return result;
    }

    /**
     *  Get all the appApps.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AppAppsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AppApps");
        return appAppsRepository.findAll(pageable)
            .map(appAppsMapper::toDto);
    }

    /**
     *  Get one appApps by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AppAppsDTO findOne(Long id) {
        log.debug("Request to get AppApps : {}", id);
        AppApps appApps = appAppsRepository.findOne(id);
        return appAppsMapper.toDto(appApps);
    }

    /**
     *  Delete the  appApps by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AppApps : {}", id);
        appAppsRepository.delete(id);
        appAppsSearchRepository.delete(id);
    }

    /**
     * Search for the appApps corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AppAppsDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AppApps for query {}", query);
        Page<AppApps> result = appAppsSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(appAppsMapper::toDto);
    }
}
