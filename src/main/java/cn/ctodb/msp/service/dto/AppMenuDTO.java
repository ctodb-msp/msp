package cn.ctodb.msp.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import cn.ctodb.msp.domain.enumeration.ReqMode;
import cn.ctodb.msp.domain.enumeration.AppLevel;

/**
 * A DTO for the AppMenu entity.
 */
public class AppMenuDTO implements Serializable {

    private Long id;

    private String code;

    private String name;

    private ReqMode reqMode;

    private String reqAddr;

    private AppLevel appLevel;

    private Integer sort;

    private String icon1;

    private String status;

    private Long appId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReqMode getReqMode() {
        return reqMode;
    }

    public void setReqMode(ReqMode reqMode) {
        this.reqMode = reqMode;
    }

    public String getReqAddr() {
        return reqAddr;
    }

    public void setReqAddr(String reqAddr) {
        this.reqAddr = reqAddr;
    }

    public AppLevel getAppLevel() {
        return appLevel;
    }

    public void setAppLevel(AppLevel appLevel) {
        this.appLevel = appLevel;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon1() {
        return icon1;
    }

    public void setIcon1(String icon1) {
        this.icon1 = icon1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appAppsId) {
        this.appId = appAppsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AppMenuDTO appMenuDTO = (AppMenuDTO) o;
        if(appMenuDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appMenuDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppMenuDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", reqMode='" + getReqMode() + "'" +
            ", reqAddr='" + getReqAddr() + "'" +
            ", appLevel='" + getAppLevel() + "'" +
            ", sort='" + getSort() + "'" +
            ", icon1='" + getIcon1() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
