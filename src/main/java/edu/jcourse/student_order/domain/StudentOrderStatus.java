package edu.jcourse.student_order.domain;

public enum StudentOrderStatus {
    START, CHECKED, NO_STATUS;

    public static StudentOrderStatus fromValue(int value) {
        for (StudentOrderStatus sos : StudentOrderStatus.values()) {
            if (sos.ordinal() == value) {
                return sos;
            }
        }
        throw new IllegalArgumentException("Unknown value:" + value);
    }
}
