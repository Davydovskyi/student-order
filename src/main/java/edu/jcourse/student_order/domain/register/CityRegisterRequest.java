package edu.jcourse.student_order.domain.register;

import edu.jcourse.student_order.domain.Address;
import edu.jcourse.student_order.domain.Person;
import edu.jcourse.student_order.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.Objects;

public class CityRegisterRequest {
    private String surName;
    private String givenName;
    private String patronymic;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateOfBirth;
    private Long streetCode;
    private String building;
    private String extension;
    private String apartment;

    public CityRegisterRequest() {
    }

    public CityRegisterRequest(Person person) {
        surName = person.getSurName();
        givenName = person.getGivenName();
        patronymic = person.getPatronymic();
        dateOfBirth = person.getDateOfBirth();
        Address address = person.getAddress();
        streetCode = address.getStreet().getStreetCode();
        building = address.getBuilding();
        extension = address.getExtension();
        apartment = address.getApartment();
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

    public Long getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityRegisterRequest that = (CityRegisterRequest) o;

        if (!Objects.equals(surName, that.surName)) return false;
        if (!Objects.equals(givenName, that.givenName)) return false;
        if (!Objects.equals(patronymic, that.patronymic)) return false;
        if (!Objects.equals(dateOfBirth, that.dateOfBirth)) return false;
        if (!Objects.equals(streetCode, that.streetCode)) return false;
        if (!Objects.equals(building, that.building)) return false;
        if (!Objects.equals(extension, that.extension)) return false;
        return Objects.equals(apartment, that.apartment);
    }

    @Override
    public int hashCode() {
        int result = surName != null ? surName.hashCode() : 0;
        result = 31 * result + (givenName != null ? givenName.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (streetCode != null ? streetCode.hashCode() : 0);
        result = 31 * result + (building != null ? building.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        @SuppressWarnings("DuplicatedCode") final StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{surName='").append(surName).append('\'');
        sb.append(", givenName='").append(givenName).append('\'');
        sb.append(", patronymic='").append(patronymic).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", streetCode=").append(streetCode);
        sb.append(", building='").append(building).append('\'');
        sb.append(", extension='").append(extension).append('\'');
        sb.append(", apartment='").append(apartment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
