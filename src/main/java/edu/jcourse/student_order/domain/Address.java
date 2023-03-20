package edu.jcourse.student_order.domain;

import java.util.Objects;

public class Address {

    private String postCode;
    private Street street;
    private String building;
    private String extension;
    private String apartment;

    public Address() {
        postCode = "";
        street = new Street();
        building = "";
        extension = "";
        apartment = "";
    }

    public Address(String postCode, Street street, String building, String extension, String apartment) {
        this.postCode = postCode;
        this.street = street;
        this.building = building;
        this.extension = extension;
        this.apartment = apartment;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
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

        Address address = (Address) o;

        if (!Objects.equals(postCode, address.postCode)) return false;
        if (!Objects.equals(street, address.street)) return false;
        if (!Objects.equals(building, address.building)) return false;
        if (!Objects.equals(extension, address.extension)) return false;
        return Objects.equals(apartment, address.apartment);
    }

    @Override
    public int hashCode() {
        int result = postCode != null ? postCode.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (building != null ? building.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{postCode='").append(postCode).append('\'');
        sb.append(", street=").append(street.toString());
        sb.append(", building='").append(building).append('\'');
        sb.append(", extension='").append(extension).append('\'');
        sb.append(", apartment='").append(apartment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
