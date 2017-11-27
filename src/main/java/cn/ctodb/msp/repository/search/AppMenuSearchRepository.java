package cn.ctodb.msp.repository.search;

import cn.ctodb.msp.domain.AppMenu;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AppMenu entity.
 */
public interface AppMenuSearchRepository extends ElasticsearchRepository<AppMenu, Long> {
}
