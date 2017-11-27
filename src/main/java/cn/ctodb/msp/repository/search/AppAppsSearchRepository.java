package cn.ctodb.msp.repository.search;

import cn.ctodb.msp.domain.AppApps;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AppApps entity.
 */
public interface AppAppsSearchRepository extends ElasticsearchRepository<AppApps, Long> {
}
