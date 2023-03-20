package edu.jcourse.student_order.domain;

import edu.jcourse.student_order.domain.document.Passport;

import java.time.LocalDate;
import java.util.Objects;

public class Adult extends Person {

    private Passport passport;
    private University university;
    private String studentId;

    public Adult() {
        passport = new Passport();
        university = new University();
        studentId = "";
    }

    public Adult(String surName, String givenName, String patronymic, LocalDate dateOfBirth) {
        super(surName, givenName, patronymic, dateOfBirth);
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Adult adult = (Adult) o;

        if (!Objects.equals(passport, adult.passport)) return false;
        if (!Objects.equals(university, adult.university)) return false;
        return Objects.equals(studentId, adult.studentId);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        result = 31 * result + (university != null ? university.hashCode() : 0);
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.deleteCharAt(sb.length() - 1);
        sb.append(", passport=").append(passport);
        sb.append(", university='").append(university).append('\'');
        sb.append(", studentId='").append(studentId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
