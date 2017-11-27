package cn.ctodb.msp.service.mapper;

import cn.ctodb.msp.domain.*;
import cn.ctodb.msp.service.dto.AppMenuDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AppMenu and its DTO AppMenuDTO.
 */
@Mapper(componentModel = "spring", uses = {AppAppsMapper.class})
public interface AppMenuMapper extends EntityMapper<AppMenuDTO, AppMenu> {

    @Mapping(source = "app.id", target = "appId")
    AppMenuDTO toDto(AppMenu appMenu); 

    @Mapping(source = "appId", target = "app")
    AppMenu toEntity(AppMenuDTO appMenuDTO);

    default AppMenu fromId(Long id) {
        if (id == null) {
            return null;
        }
        AppMenu appMenu = new AppMenu();
        appMenu.setId(id);
        return appMenu;
    }
}
