package cn.ctodb.msp.service.mapper;

import cn.ctodb.msp.domain.*;
import cn.ctodb.msp.service.dto.AppGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AppGroup and its DTO AppGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AppGroupMapper extends EntityMapper<AppGroupDTO, AppGroup> {

    @Mapping(source = "parent.id", target = "parentId")
    AppGroupDTO toDto(AppGroup appGroup); 

    @Mapping(source = "parentId", target = "parent")
    @Mapping(target = "children", ignore = true)
    AppGroup toEntity(AppGroupDTO appGroupDTO);

    default AppGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        AppGroup appGroup = new AppGroup();
        appGroup.setId(id);
        return appGroup;
    }
}
