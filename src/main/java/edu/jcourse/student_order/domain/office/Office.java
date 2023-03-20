package edu.jcourse.student_order.domain.office;

import java.util.Objects;

public abstract class Office {

    private Long officeID;
    private String officeAreaId;
    private String officeName;

    public Office() {
        officeID = 0L;
        officeAreaId = "";
        officeName = "";
    }

    public Office(Long officeID, String officeAreaId, String officeName) {
        this.officeID = officeID;
        this.officeAreaId = officeAreaId;
        this.officeName = officeName;
    }

    public Long getOfficeID() {
        return officeID;
    }

    public void setOfficeID(Long officeID) {
        this.officeID = officeID;
    }

    public String getOfficeAreaId() {
        return officeAreaId;
    }

    public void setOfficeAreaId(String officeAreaId) {
        this.officeAreaId = officeAreaId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Office office = (Office) o;

        if (!Objects.equals(officeID, office.officeID)) return false;
        if (!Objects.equals(officeAreaId, office.officeAreaId))
            return false;
        return Objects.equals(officeName, office.officeName);
    }

    @Override
    public int hashCode() {
        int result = officeID != null ? officeID.hashCode() : 0;
        result = 31 * result + (officeAreaId != null ? officeAreaId.hashCode() : 0);
        result = 31 * result + (officeName != null ? officeName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{officeID=").append(officeID);
        sb.append(", officeAreaId='").append(officeAreaId).append('\'');
        sb.append(", officeName='").append(officeName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
