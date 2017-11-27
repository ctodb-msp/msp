package cn.ctodb.msp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A AppGroup.
 */
@Entity
@Table(name = "app_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "appgroup")
public class AppGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

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

    @ManyToOne
    private AppGroup parent;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AppGroup> children = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public AppGroup name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public AppGroup sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon1() {
        return icon1;
    }

    public AppGroup icon1(String icon1) {
        this.icon1 = icon1;
        return this;
    }

    public void setIcon1(String icon1) {
        this.icon1 = icon1;
    }

    public String getIcon2() {
        return icon2;
    }

    public AppGroup icon2(String icon2) {
        this.icon2 = icon2;
        return this;
    }

    public void setIcon2(String icon2) {
        this.icon2 = icon2;
    }

    public String getIcon3() {
        return icon3;
    }

    public AppGroup icon3(String icon3) {
        this.icon3 = icon3;
        return this;
    }

    public void setIcon3(String icon3) {
        this.icon3 = icon3;
    }

    public String getDef1() {
        return def1;
    }

    public AppGroup def1(String def1) {
        this.def1 = def1;
        return this;
    }

    public void setDef1(String def1) {
        this.def1 = def1;
    }

    public String getDef2() {
        return def2;
    }

    public AppGroup def2(String def2) {
        this.def2 = def2;
        return this;
    }

    public void setDef2(String def2) {
        this.def2 = def2;
    }

    public String getDef3() {
        return def3;
    }

    public AppGroup def3(String def3) {
        this.def3 = def3;
        return this;
    }

    public void setDef3(String def3) {
        this.def3 = def3;
    }

    public String getDef4() {
        return def4;
    }

    public AppGroup def4(String def4) {
        this.def4 = def4;
        return this;
    }

    public void setDef4(String def4) {
        this.def4 = def4;
    }

    public String getDef5() {
        return def5;
    }

    public AppGroup def5(String def5) {
        this.def5 = def5;
        return this;
    }

    public void setDef5(String def5) {
        this.def5 = def5;
    }

    public String getDef6() {
        return def6;
    }

    public AppGroup def6(String def6) {
        this.def6 = def6;
        return this;
    }

    public void setDef6(String def6) {
        this.def6 = def6;
    }

    public String getDef7() {
        return def7;
    }

    public AppGroup def7(String def7) {
        this.def7 = def7;
        return this;
    }

    public void setDef7(String def7) {
        this.def7 = def7;
    }

    public String getDef8() {
        return def8;
    }

    public AppGroup def8(String def8) {
        this.def8 = def8;
        return this;
    }

    public void setDef8(String def8) {
        this.def8 = def8;
    }

    public String getDef9() {
        return def9;
    }

    public AppGroup def9(String def9) {
        this.def9 = def9;
        return this;
    }

    public void setDef9(String def9) {
        this.def9 = def9;
    }

    public AppGroup getParent() {
        return parent;
    }

    public AppGroup parent(AppGroup appGroup) {
        this.parent = appGroup;
        return this;
    }

    public void setParent(AppGroup appGroup) {
        this.parent = appGroup;
    }

    public Set<AppGroup> getChildren() {
        return children;
    }

    public AppGroup children(Set<AppGroup> appGroups) {
        this.children = appGroups;
        return this;
    }

    public AppGroup addChild(AppGroup appGroup) {
        this.children.add(appGroup);
        appGroup.setParent(this);
        return this;
    }

    public AppGroup removeChild(AppGroup appGroup) {
        this.children.remove(appGroup);
        appGroup.setParent(null);
        return this;
    }

    public void setChildren(Set<AppGroup> appGroups) {
        this.children = appGroups;
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
        AppGroup appGroup = (AppGroup) o;
        if (appGroup.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appGroup.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppGroup{" +
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
