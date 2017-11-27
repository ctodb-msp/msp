package cn.ctodb.msp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import cn.ctodb.msp.domain.enumeration.AppType;

import cn.ctodb.msp.domain.enumeration.ReqMode;

import cn.ctodb.msp.domain.enumeration.AppLevel;

import cn.ctodb.msp.domain.enumeration.AppPlatForm;

/**
 * A AppApps.
 */
@Entity
@Table(name = "app_apps")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "appapps")
public class AppApps implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "version")
    private Integer version;

    @Column(name = "app_desc")
    private String appDesc;

    @Enumerated(EnumType.STRING)
    @Column(name = "app_type")
    private AppType appType;

    @Enumerated(EnumType.STRING)
    @Column(name = "req_mode")
    private ReqMode reqMode;

    @Column(name = "req_addr")
    private String reqAddr;

    @Enumerated(EnumType.STRING)
    @Column(name = "app_level")
    private AppLevel appLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform")
    private AppPlatForm platform;

    @Column(name = "jhi_sort")
    private Integer sort;

    @Column(name = "icon_1")
    private String icon1;

    @Column(name = "icon_2")
    private String icon2;

    @Column(name = "icon_3")
    private String icon3;

    @Column(name = "def_1")
    private String def1;

    @Column(name = "def_2")
    private String def2;

    @Column(name = "def_3")
    private String def3;

    @Column(name = "def_4")
    private String def4;

    @Column(name = "def_5")
    private String def5;

    @Column(name = "def_6")
    private String def6;

    @Column(name = "def_7")
    private String def7;

    @Column(name = "def_8")
    private String def8;

    @Column(name = "def_9")
    private String def9;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(unique = true)
    private AppMenu menu;

    @ManyToOne
    private AppGroup group;

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

    public AppApps code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public AppApps name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public AppApps version(Integer version) {
        this.version = version;
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public AppApps appDesc(String appDesc) {
        this.appDesc = appDesc;
        return this;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public AppType getAppType() {
        return appType;
    }

    public AppApps appType(AppType appType) {
        this.appType = appType;
        return this;
    }

    public void setAppType(AppType appType) {
        this.appType = appType;
    }

    public ReqMode getReqMode() {
        return reqMode;
    }

    public AppApps reqMode(ReqMode reqMode) {
        this.reqMode = reqMode;
        return this;
    }

    public void setReqMode(ReqMode reqMode) {
        this.reqMode = reqMode;
    }

    public String getReqAddr() {
        return reqAddr;
    }

    public AppApps reqAddr(String reqAddr) {
        this.reqAddr = reqAddr;
        return this;
    }

    public void setReqAddr(String reqAddr) {
        this.reqAddr = reqAddr;
    }

    public AppLevel getAppLevel() {
        return appLevel;
    }

    public AppApps appLevel(AppLevel appLevel) {
        this.appLevel = appLevel;
        return this;
    }

    public void setAppLevel(AppLevel appLevel) {
        this.appLevel = appLevel;
    }

    public AppPlatForm getPlatform() {
        return platform;
    }

    public AppApps platform(AppPlatForm platform) {
        this.platform = platform;
        return this;
    }

    public void setPlatform(AppPlatForm platform) {
        this.platform = platform;
    }

    public Integer getSort() {
        return sort;
    }

    public AppApps sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon1() {
        return icon1;
    }

    public AppApps icon1(String icon1) {
        this.icon1 = icon1;
        return this;
    }

    public void setIcon1(String icon1) {
        this.icon1 = icon1;
    }

    public String getIcon2() {
        return icon2;
    }

    public AppApps icon2(String icon2) {
        this.icon2 = icon2;
        return this;
    }

    public void setIcon2(String icon2) {
        this.icon2 = icon2;
    }

    public String getIcon3() {
        return icon3;
    }

    public AppApps icon3(String icon3) {
        this.icon3 = icon3;
        return this;
    }

    public void setIcon3(String icon3) {
        this.icon3 = icon3;
    }

    public String getDef1() {
        return def1;
    }

    public AppApps def1(String def1) {
        this.def1 = def1;
        return this;
    }

    public void setDef1(String def1) {
        this.def1 = def1;
    }

    public String getDef2() {
        return def2;
    }

    public AppApps def2(String def2) {
        this.def2 = def2;
        return this;
    }

    public void setDef2(String def2) {
        this.def2 = def2;
    }

    public String getDef3() {
        return def3;
    }

    public AppApps def3(String def3) {
        this.def3 = def3;
        return this;
    }

    public void setDef3(String def3) {
        this.def3 = def3;
    }

    public String getDef4() {
        return def4;
    }

    public AppApps def4(String def4) {
        this.def4 = def4;
        return this;
    }

    public void setDef4(String def4) {
        this.def4 = def4;
    }

    public String getDef5() {
        return def5;
    }

    public AppApps def5(String def5) {
        this.def5 = def5;
        return this;
    }

    public void setDef5(String def5) {
        this.def5 = def5;
    }

    public String getDef6() {
        return def6;
    }

    public AppApps def6(String def6) {
        this.def6 = def6;
        return this;
    }

    public void setDef6(String def6) {
        this.def6 = def6;
    }

    public String getDef7() {
        return def7;
    }

    public AppApps def7(String def7) {
        this.def7 = def7;
        return this;
    }

    public void setDef7(String def7) {
        this.def7 = def7;
    }

    public String getDef8() {
        return def8;
    }

    public AppApps def8(String def8) {
        this.def8 = def8;
        return this;
    }

    public void setDef8(String def8) {
        this.def8 = def8;
    }

    public String getDef9() {
        return def9;
    }

    public AppApps def9(String def9) {
        this.def9 = def9;
        return this;
    }

    public void setDef9(String def9) {
        this.def9 = def9;
    }

    public String getStatus() {
        return status;
    }

    public AppApps status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AppMenu getMenu() {
        return menu;
    }

    public AppApps menu(AppMenu appMenu) {
        this.menu = appMenu;
        return this;
    }

    public void setMenu(AppMenu appMenu) {
        this.menu = appMenu;
    }

    public AppGroup getGroup() {
        return group;
    }

    public AppApps group(AppGroup appGroup) {
        this.group = appGroup;
        return this;
    }

    public void setGroup(AppGroup appGroup) {
        this.group = appGroup;
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
        AppApps appApps = (AppApps) o;
        if (appApps.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appApps.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppApps{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", version='" + getVersion() + "'" +
            ", appDesc='" + getAppDesc() + "'" +
            ", appType='" + getAppType() + "'" +
            ", reqMode='" + getReqMode() + "'" +
            ", reqAddr='" + getReqAddr() + "'" +
            ", appLevel='" + getAppLevel() + "'" +
            ", platform='" + getPlatform() + "'" +
            ", sort='" + getSort() + "'" +
            ", icon1='" + getIcon1() + "'" +
            ", icon2='" + getIcon2() + "'" +
            ", icon3='" + getIcon3() + "'" +
            ", def1='" + getDef1() + "'" +
            ", def2='" + getDef2() + "'" +
            ", def3='" + getDef3() + "'" +
            ", def4='" + getDef4() + "'" +
            ", def5='" + getDef5() + "'" +
            ", def6='" + getDef6() + "'" +
            ", def7='" + getDef7() + "'" +
            ", def8='" + getDef8() + "'" +
            ", def9='" + getDef9() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
