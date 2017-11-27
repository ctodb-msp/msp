package cn.ctodb.msp.repository;

import cn.ctodb.msp.domain.AppGroup;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AppGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppGroupRepository extends JpaRepository<AppGroup, Long> {

}
