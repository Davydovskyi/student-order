package edu.jcourse.student_order.domain.register;

public class CityRegisterResponse {

    private boolean registered;
    private boolean temporal;

    public CityRegisterResponse() {
    }

    public CityRegisterResponse(boolean existing, Boolean temporal) {
        this.registered = existing;
        this.temporal = temporal;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean isTemporal() {
        return temporal;
    }

    public void setTemporal(boolean temporal) {
        this.temporal = temporal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityRegisterResponse response = (CityRegisterResponse) o;

        if (registered != response.registered) return false;
        return temporal == response.temporal;
    }

    @Override
    public int hashCode() {
        int result = (registered ? 1 : 0);
        result = 31 * result + (temporal ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{registered=").append(registered);
        sb.append(", temporal=").append(temporal);
        sb.append('}');
        return sb.toString();
    }
}
