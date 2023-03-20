package edu.jcourse.student_order.domain.document;

import edu.jcourse.student_order.domain.office.RegisterOffice;

import java.time.LocalDate;
import java.util.Objects;

public class BirthCertificate {

    private String certificateNumber;
    private RegisterOffice issueDepartment;
    private LocalDate issueDate;

    public BirthCertificate() {
        certificateNumber = "";
        issueDepartment = new RegisterOffice();
        issueDate = LocalDate.of(-1, 1, 1);
    }

    public BirthCertificate(String certificateNumber, RegisterOffice issueDepartment, LocalDate issueDate) {
        this.certificateNumber = certificateNumber;
        this.issueDepartment = issueDepartment;
        this.issueDate = issueDate;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public RegisterOffice getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(RegisterOffice issueDepartment) {
        this.issueDepartment = issueDepartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BirthCertificate that = (BirthCertificate) o;

        if (!Objects.equals(certificateNumber, that.certificateNumber))
            return false;
        if (!Objects.equals(issueDepartment, that.issueDepartment))
            return false;
        return Objects.equals(issueDate, that.issueDate);
    }

    @Override
    public int hashCode() {
        int result = certificateNumber != null ? certificateNumber.hashCode() : 0;
        result = 31 * result + (issueDepartment != null ? issueDepartment.hashCode() : 0);
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{certificateNumber='").append(certificateNumber).append('\'');
        sb.append(", issueDepartment=").append(issueDepartment);
        sb.append(", issueDate=").append(issueDate);
        sb.append('}');
        return sb.toString();
    }
}
