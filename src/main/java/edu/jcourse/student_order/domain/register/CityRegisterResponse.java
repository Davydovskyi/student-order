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
}
