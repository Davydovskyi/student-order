package edu.jcourse.student_order.domain;

import java.util.Objects;

public class CountryArea {

    private String areaId;
    private String areaName;

    public CountryArea() {
        areaId = "";
        areaName = "";
    }

    public CountryArea(String areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryArea that = (CountryArea) o;

        if (!Objects.equals(areaId, that.areaId)) return false;
        return Objects.equals(areaName, that.areaName);
    }

    @Override
    public int hashCode() {
        int result = areaId != null ? areaId.hashCode() : 0;
        result = 31 * result + (areaName != null ? areaName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{areaId='").append(areaId).append('\'');
        sb.append(", areaName='").append(areaName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
