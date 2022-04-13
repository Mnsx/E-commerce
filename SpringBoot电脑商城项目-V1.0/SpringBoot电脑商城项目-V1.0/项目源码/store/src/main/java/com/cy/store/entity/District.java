package com.cy.store.entity;

import java.io.Serializable;

/** 省/市/区数据的实体类 */
public class District implements Serializable {
    private Integer id;
    private String parent;
    private String code;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof District)) return false;

        District district = (District) o;

        if (getId() != null ? !getId().equals(district.getId()) : district.getId() != null) return false;
        if (getParent() != null ? !getParent().equals(district.getParent()) : district.getParent() != null)
            return false;
        if (getCode() != null ? !getCode().equals(district.getCode()) : district.getCode() != null) return false;
        return getName() != null ? getName().equals(district.getName()) : district.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getParent() != null ? getParent().hashCode() : 0);
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", parent='" + parent + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
