package edu.jcourse.student_order.domain.register;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class CityRegisterResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -8184831828919799482L;

    private boolean existing;
    private Boolean temporal;

    public CityRegisterResponse() {
    }

    public CityRegisterResponse(boolean existing, Boolean temporal) {
        this.existing = existing;
        this.temporal = temporal;
    }

    public boolean isExisting() {
        return existing;
    }

    public void setExisting(boolean existing) {
        this.existing = existing;
    }

    public Boolean getTemporal() {
        return temporal;
    }

    public void setTemporal(Boolean temporal) {
        this.temporal = temporal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityRegisterResponse that = (CityRegisterResponse) o;

        if (existing != that.existing) return false;
        return Objects.equals(temporal, that.temporal);
    }

    @Override
    public int hashCode() {
        int result = (existing ? 1 : 0);
        result = 31 * result + (temporal != null ? temporal.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{existing=").append(existing);
        sb.append(", temporal=").append(temporal);
        sb.append('}');
        return sb.toString();
    }
}
