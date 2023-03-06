package edu.jcourse.student_order.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class University implements Serializable {

    @Serial
    private static final long serialVersionUID = 7673975928368226633L;

    private Long universityID;
    private String universityName;

    public University() {
        universityID = 0L;
        universityName = "";
    }

    public University(Long universityID, String universityName) {
        this.universityID = universityID;
        this.universityName = universityName;
    }

    public Long getUniversityID() {
        return universityID;
    }

    public void setUniversityID(Long universityID) {
        this.universityID = universityID;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        University that = (University) o;

        if (!Objects.equals(universityID, that.universityID)) return false;
        return Objects.equals(universityName, that.universityName);
    }

    @Override
    public int hashCode() {
        int result = universityID != null ? universityID.hashCode() : 0;
        result = 31 * result + (universityName != null ? universityName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{universityID=").append(universityID);
        sb.append(", universityName='").append(universityName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
