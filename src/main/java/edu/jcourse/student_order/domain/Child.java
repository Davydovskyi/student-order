package edu.jcourse.student_order.domain;

import edu.jcourse.student_order.domain.document.BirthCertificate;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Objects;

public class Child extends Person {

    @Serial
    private static final long serialVersionUID = -6851963215245556650L;

    private BirthCertificate birthCertificate;

    public Child() {
        birthCertificate = new BirthCertificate();
    }

    public Child(String surName, String givenName, String patronymic, LocalDate dateOfBirth) {
        super(surName, givenName, patronymic, dateOfBirth);
    }

    public BirthCertificate getBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(BirthCertificate birthCertificate) {
        this.birthCertificate = birthCertificate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Child child = (Child) o;

        return Objects.equals(birthCertificate, child.birthCertificate);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (birthCertificate != null ? birthCertificate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.deleteCharAt(sb.length() - 1);
        sb.append(", birthCertificate=").append(birthCertificate);
        sb.append('}');
        return sb.toString();
    }
}
