package cn.ctodb.msp.repository.search;

import cn.ctodb.msp.domain.AppGroup;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AppGroup entity.
 */
public interface AppGroupSearchRepository extends ElasticsearchRepository<AppGroup, Long> {
}
