package cn.ctodb.msp.service.impl;

import cn.ctodb.msp.service.AppGroupService;
import cn.ctodb.msp.domain.AppGroup;
import cn.ctodb.msp.repository.AppGroupRepository;
import cn.ctodb.msp.repository.search.AppGroupSearchRepository;
import cn.ctodb.msp.service.dto.AppGroupDTO;
import cn.ctodb.msp.service.mapper.AppGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AppGroup.
 */
@Service
@Transactional
public class AppGroupServiceImpl implements AppGroupService{

    private final Logger log = LoggerFactory.getLogger(AppGroupServiceImpl.class);

    private final AppGroupRepository appGroupRepository;

    private final AppGroupMapper appGroupMapper;

    private final AppGroupSearchRepository appGroupSearchRepository;

    public AppGroupServiceImpl(AppGroupRepository appGroupRepository, AppGroupMapper appGroupMapper, AppGroupSearchRepository appGroupSearchRepository) {
        this.appGroupRepository = appGroupRepository;
        this.appGroupMapper = appGroupMapper;
        this.appGroupSearchRepository = appGroupSearchRepository;
    }

    /**
     * Save a appGroup.
     *
     * @param appGroupDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AppGroupDTO save(AppGroupDTO appGroupDTO) {
        log.debug("Request to save AppGroup : {}", appGroupDTO);
        AppGroup appGroup = appGroupMapper.toEntity(appGroupDTO);
        appGroup = appGroupRepository.save(appGroup);
        AppGroupDTO result = appGroupMapper.toDto(appGroup);
        appGroupSearchRepository.save(appGroup);
        return result;
    }

    /**
     *  Get all the appGroups.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AppGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AppGroups");
        return appGroupRepository.findAll(pageable)
            .map(appGroupMapper::toDto);
    }

    /**
     *  Get one appGroup by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AppGroupDTO findOne(Long id) {
        log.debug("Request to get AppGroup : {}", id);
        AppGroup appGroup = appGroupRepository.findOne(id);
        return appGroupMapper.toDto(appGroup);
    }

    /**
     *  Delete the  appGroup by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AppGroup : {}", id);
        appGroupRepository.delete(id);
        appGroupSearchRepository.delete(id);
    }

    /**
     * Search for the appGroup corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AppGroupDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AppGroups for query {}", query);
        Page<AppGroup> result = appGroupSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(appGroupMapper::toDto);
    }
}
