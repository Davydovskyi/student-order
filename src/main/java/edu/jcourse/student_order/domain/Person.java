package edu.jcourse.student_order.domain;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Person {

    protected String surName;
    protected String givenName;
    protected String patronymic;
    protected LocalDate dateOfBirth;
    protected Address address;

    public Person() {
        surName = "";
        givenName = "";
        patronymic = "";
        dateOfBirth = LocalDate.of(-1, 1, 1);
        address = new Address();
    }

    public Person(String surName, String givenName, String patronymic, LocalDate dateOfBirth) {
        this.surName = surName;
        this.givenName = givenName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!Objects.equals(surName, person.surName)) return false;
        if (!Objects.equals(givenName, person.givenName)) return false;
        if (!Objects.equals(patronymic, person.patronymic)) return false;
        if (!Objects.equals(dateOfBirth, person.dateOfBirth)) return false;
        return Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        int result = surName != null ? surName.hashCode() : 0;
        result = 31 * result + (givenName != null ? givenName.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{surName='").append(surName).append('\'');
        sb.append(", givenName='").append(givenName).append('\'');
        sb.append(", patronymic='").append(patronymic).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}
