package cn.ctodb.msp.service.mapper;

import cn.ctodb.msp.domain.*;
import cn.ctodb.msp.service.dto.AppAppsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AppApps and its DTO AppAppsDTO.
 */
@Mapper(componentModel = "spring", uses = {AppMenuMapper.class, AppGroupMapper.class})
public interface AppAppsMapper extends EntityMapper<AppAppsDTO, AppApps> {

    @Mapping(source = "menu.id", target = "menuId")
    @Mapping(source = "group.id", target = "groupId")
    AppAppsDTO toDto(AppApps appApps); 

    @Mapping(source = "menuId", target = "menu")
    @Mapping(source = "groupId", target = "group")
    AppApps toEntity(AppAppsDTO appAppsDTO);

    default AppApps fromId(Long id) {
        if (id == null) {
            return null;
        }
        AppApps appApps = new AppApps();
        appApps.setId(id);
        return appApps;
    }
}
