package edu.jcourse.student_order.domain.document;

import edu.jcourse.student_order.domain.office.PassportOffice;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Passport implements Serializable {

    @Serial
    private static final long serialVersionUID = -4582901849117846350L;

    private String passportSeries;
    private String passportNumber;
    private LocalDate issueDate;
    private PassportOffice issueDepartment;

    public Passport() {
        passportSeries = "";
        passportNumber = "";
        issueDate = LocalDate.of(-1, 1, 1);
        issueDepartment = new PassportOffice();
    }

    public Passport(String passportSeries, String passportNumber, LocalDate issueDate, PassportOffice issueDepartment) {
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.issueDate = issueDate;
        this.issueDepartment = issueDepartment;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public PassportOffice getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(PassportOffice issueDepartment) {
        this.issueDepartment = issueDepartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passport passport = (Passport) o;

        if (!Objects.equals(passportSeries, passport.passportSeries))
            return false;
        if (!Objects.equals(passportNumber, passport.passportNumber))
            return false;
        if (!Objects.equals(issueDate, passport.issueDate)) return false;
        return Objects.equals(issueDepartment, passport.issueDepartment);
    }

    @Override
    public int hashCode() {
        int result = passportSeries != null ? passportSeries.hashCode() : 0;
        result = 31 * result + (passportNumber != null ? passportNumber.hashCode() : 0);
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        result = 31 * result + (issueDepartment != null ? issueDepartment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{passportSeries='").append(passportSeries).append('\'');
        sb.append(", passportNumber='").append(passportNumber).append('\'');
        sb.append(", issueDate=").append(issueDate);
        sb.append(", issueDepartment=").append(issueDepartment);
        sb.append('}');
        return sb.toString();
    }
}
