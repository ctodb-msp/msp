package cn.ctodb.msp.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the AppGroup entity.
 */
public class AppGroupDTO implements Serializable {

    private Long id;

    private String name;

    private Integer sort;

    private String icon1;

    private String icon2;

    private String icon3;

    private String def1;

    private String def2;

    private String def3;

    private String def4;

    private String def5;

    private String def6;

    private String def7;

    private String def8;

    private String def9;

    private Long parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIcon2() {
        return icon2;
    }

    public void setIcon2(String icon2) {
        this.icon2 = icon2;
    }

    public String getIcon3() {
        return icon3;
    }

    public void setIcon3(String icon3) {
        this.icon3 = icon3;
    }

    public String getDef1() {
        return def1;
    }

    public void setDef1(String def1) {
        this.def1 = def1;
    }

    public String getDef2() {
        return def2;
    }

    public void setDef2(String def2) {
        this.def2 = def2;
    }

    public String getDef3() {
        return def3;
    }

    public void setDef3(String def3) {
        this.def3 = def3;
    }

    public String getDef4() {
        return def4;
    }

    public void setDef4(String def4) {
        this.def4 = def4;
    }

    public String getDef5() {
        return def5;
    }

    public void setDef5(String def5) {
        this.def5 = def5;
    }

    public String getDef6() {
        return def6;
    }

    public void setDef6(String def6) {
        this.def6 = def6;
    }

    public String getDef7() {
        return def7;
    }

    public void setDef7(String def7) {
        this.def7 = def7;
    }

    public String getDef8() {
        return def8;
    }

    public void setDef8(String def8) {
        this.def8 = def8;
    }

    public String getDef9() {
        return def9;
    }

    public void setDef9(String def9) {
        this.def9 = def9;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long appGroupId) {
        this.parentId = appGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AppGroupDTO appGroupDTO = (AppGroupDTO) o;
        if(appGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppGroupDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
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
            "}";
    }
}
