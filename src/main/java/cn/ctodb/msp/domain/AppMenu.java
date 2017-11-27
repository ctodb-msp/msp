package cn.ctodb.msp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import cn.ctodb.msp.domain.enumeration.ReqMode;

import cn.ctodb.msp.domain.enumeration.AppLevel;

/**
 * A AppMenu.
 */
@Entity
@Table(name = "app_menu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "appmenu")
public class AppMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "req_mode")
    private ReqMode reqMode;

    @Column(name = "req_addr")
    private String reqAddr;

    @Enumerated(EnumType.STRING)
    @Column(name = "app_level")
    private AppLevel appLevel;

    @Column(name = "jhi_sort")
    private Integer sort;

    @Column(name = "icon_1")
    private String icon1;

    @Column(name = "status")
    private String status;

    @ManyToOne
    private AppApps app;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public AppMenu code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public AppMenu name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReqMode getReqMode() {
        return reqMode;
    }

    public AppMenu reqMode(ReqMode reqMode) {
        this.reqMode = reqMode;
        return this;
    }

    public void setReqMode(ReqMode reqMode) {
        this.reqMode = reqMode;
    }

    public String getReqAddr() {
        return reqAddr;
    }

    public AppMenu reqAddr(String reqAddr) {
        this.reqAddr = reqAddr;
        return this;
    }

    public void setReqAddr(String reqAddr) {
        this.reqAddr = reqAddr;
    }

    public AppLevel getAppLevel() {
        return appLevel;
    }

    public AppMenu appLevel(AppLevel appLevel) {
        this.appLevel = appLevel;
        return this;
    }

    public void setAppLevel(AppLevel appLevel) {
        this.appLevel = appLevel;
    }

    public Integer getSort() {
        return sort;
    }

    public AppMenu sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon1() {
        return icon1;
    }

    public AppMenu icon1(String icon1) {
        this.icon1 = icon1;
        return this;
    }

    public void setIcon1(String icon1) {
        this.icon1 = icon1;
    }

    public String getStatus() {
        return status;
    }

    public AppMenu status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AppApps getApp() {
        return app;
    }

    public AppMenu app(AppApps appApps) {
        this.app = appApps;
        return this;
    }

    public void setApp(AppApps appApps) {
        this.app = appApps;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppMenu appMenu = (AppMenu) o;
        if (appMenu.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appMenu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppMenu{" +
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
