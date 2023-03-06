package edu.jcourse.student_order.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Street implements Serializable {

    @Serial
    private static final long serialVersionUID = -2994749762255759818L;

    private Long streetCode;
    private String streetName;

    public Street() {
        streetCode = 0L;
        streetName = "";
    }

    public Street(Long streetCode, String streetName) {
        this.streetCode = streetCode;
        this.streetName = streetName;
    }

    public Long getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Street street = (Street) o;

        if (!Objects.equals(streetCode, street.streetCode)) return false;
        return Objects.equals(streetName, street.streetName);
    }

    @Override
    public int hashCode() {
        int result = streetCode != null ? streetCode.hashCode() : 0;
        result = 31 * result + (streetName != null ? streetName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{streetCode=").append(streetCode);
        sb.append(", streetName='").append(streetName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
