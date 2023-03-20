package edu.jcourse.student_order.domain;

import edu.jcourse.student_order.domain.document.MarriageCertificate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentOrder {

    private long studentOrderId;
    private StudentOrderStatus studentOrderStatus;
    private LocalDateTime studentOrderDate;
    private Adult husband;
    private Adult wife;
    private List<Child> children;
    private MarriageCertificate marriageCertificate;

    public StudentOrder() {
        studentOrderId = 0;
        studentOrderStatus = StudentOrderStatus.NO_STATUS;
        studentOrderDate = LocalDateTime.of(-1, 1, 1, 1, 1);
        husband = new Adult();
        wife = new Adult();
        children = new ArrayList<>();
        marriageCertificate = new MarriageCertificate();
    }

    public StudentOrder(long studentOrderId, StudentOrderStatus studentOrderStatus, LocalDateTime studentOrderDate,
                        Adult husband, Adult wife, List<Child> children, MarriageCertificate marriageCertificate) {
        this.studentOrderId = studentOrderId;
        this.studentOrderStatus = studentOrderStatus;
        this.studentOrderDate = studentOrderDate;
        this.husband = husband;
        this.wife = wife;
        this.children = children;
        this.marriageCertificate = marriageCertificate;
    }

    public long getStudentOrderId() {
        return studentOrderId;
    }

    public void setStudentOrderId(long studentOrderId) {
        this.studentOrderId = studentOrderId;
    }

    public StudentOrderStatus getStudentOrderStatus() {
        return studentOrderStatus;
    }

    public void setStudentOrderStatus(StudentOrderStatus studentOrderStatus) {
        this.studentOrderStatus = studentOrderStatus;
    }

    public LocalDateTime getStudentOrderDate() {
        return studentOrderDate;
    }

    public void setStudentOrderDate(LocalDateTime studentOrderDate) {
        this.studentOrderDate = studentOrderDate;
    }

    public Adult getHusband() {
        return husband;
    }

    public void setHusband(Adult husband) {
        this.husband = husband;
    }

    public Adult getWife() {
        return wife;
    }

    public void setWife(Adult wife) {
        this.wife = wife;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void addChild(Child child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(child);
    }

    public MarriageCertificate getMarriageCertificate() {
        return marriageCertificate;
    }

    public void setMarriageCertificate(MarriageCertificate marriageCertificate) {
        this.marriageCertificate = marriageCertificate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentOrder that = (StudentOrder) o;

        if (studentOrderId != that.studentOrderId) return false;
        if (studentOrderStatus != that.studentOrderStatus) return false;
        if (!Objects.equals(studentOrderDate, that.studentOrderDate))
            return false;
        if (!Objects.equals(husband, that.husband)) return false;
        if (!Objects.equals(wife, that.wife)) return false;
        if (!Objects.equals(children, that.children)) return false;
        return Objects.equals(marriageCertificate, that.marriageCertificate);
    }

    @Override
    public int hashCode() {
        int result = (int) (studentOrderId ^ (studentOrderId >>> 32));
        result = 31 * result + (studentOrderStatus != null ? studentOrderStatus.hashCode() : 0);
        result = 31 * result + (studentOrderDate != null ? studentOrderDate.hashCode() : 0);
        result = 31 * result + (husband != null ? husband.hashCode() : 0);
        result = 31 * result + (wife != null ? wife.hashCode() : 0);
        result = 31 * result + (children != null ? children.hashCode() : 0);
        result = 31 * result + (marriageCertificate != null ? marriageCertificate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("{studentOrderId=").append(studentOrderId);
        sb.append(", studentOrderStatus=").append(studentOrderStatus);
        sb.append(", studentOrderDate=").append(studentOrderDate);
        sb.append(", husband=").append(husband);
        sb.append(", wife=").append(wife);
        sb.append(", children=").append(children);
        sb.append(", marriageCertificate=").append(marriageCertificate);
        sb.append('}');
        return sb.toString();
    }
}
