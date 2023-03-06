package edu.jcourse.student_order.domain.register;

import edu.jcourse.student_order.domain.Person;

public class AnswerCityRegisterItem {

    private Person person;
    private CityStatus status;
    private CityError error;

    public AnswerCityRegisterItem(Person person, CityStatus status) {
        this.person = person;
        this.status = status;
    }

    public AnswerCityRegisterItem(Person person, CityStatus status, CityError error) {
        this.person = person;
        this.status = status;
        this.error = error;
    }

    public Person getPerson() {
        return person;
    }

    public CityStatus getStatus() {
        return status;
    }

    public CityError getError() {
        return error;
    }

    public enum CityStatus {
        YES, NO, ERROR
    }

    public static class CityError {
        String code;
        String text;

        public CityError(String code, String text) {
            this.code = code;
            this.text = text;
        }

        public String getCode() {
            return code;
        }

        public String getText() {
            return text;
        }
    }
}
