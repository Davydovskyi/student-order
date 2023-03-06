package edu.jcourse.student_order.domain.document;

import edu.jcourse.student_order.domain.office.RegisterOffice;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class MarriageCertificate implements Serializable {

    @Serial
    private static final long serialVersionUID = 9007294100779690398L;

    private String marriageCertificateId;
    private RegisterOffice marriageOffice;
    private LocalDate marriageDate;

    public MarriageCertificate() {
        marriageCertificateId = "";
        marriageOffice = new RegisterOffice();
        marriageDate = LocalDate.of(-1, 1, 1);
    }

    public MarriageCertificate(String marriageCertificateId, RegisterOffice marriageOffice, LocalDate marriageDate) {
        this.marriageCertificateId = marriageCertificateId;
        this.marriageOffice = marriageOffice;
        this.marriageDate = marriageDate;
    }

    public String getMarriageCertificateId() {
        return marriageCertificateId;
    }

    public void setMarriageCertificateId(String marriageCertificateId) {
        this.marriageCertificateId = marriageCertificateId;
    }

    public RegisterOffice getMarriageOffice() {
        return marriageOffice;
    }

    public void setMarriageOffice(RegisterOffice marriageOffice) {
        this.marriageOffice = marriageOffice;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarriageCertificate that = (MarriageCertificate) o;

        if (!Objects.equals(marriageCertificateId, that.marriageCertificateId))
            return false;
        if (!Objects.equals(marriageOffice, that.marriageOffice))
            return false;
        return Objects.equals(marriageDate, that.marriageDate);
    }

    @Override
    public int hashCode() {
        int result = marriageCertificateId != null ? marriageCertificateId.hashCode() : 0;
        result = 31 * result + (marriageOffice != null ? marriageOffice.hashCode() : 0);
        result = 31 * result + (marriageDate != null ? marriageDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{marriageCertificateId='").append(marriageCertificateId).append('\'');
        sb.append(", marriageOffice=").append(marriageOffice);
        sb.append(", marriageDate=").append(marriageDate);
        sb.append('}');
        return sb.toString();
    }
}
