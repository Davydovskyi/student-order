package edu.jcourse.student_order.dao;

import edu.jcourse.student_order.dao.impl.StudentOrderDAOImpl;
import edu.jcourse.student_order.domain.*;
import edu.jcourse.student_order.domain.document.BirthCertificate;
import edu.jcourse.student_order.domain.document.MarriageCertificate;
import edu.jcourse.student_order.domain.document.Passport;
import edu.jcourse.student_order.domain.office.PassportOffice;
import edu.jcourse.student_order.domain.office.RegisterOffice;
import edu.jcourse.student_order.exception.DAOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

// TODO покрыть тестами как можно больше.(как протестировать запись в базу при плохих данных(null) - saveStudentOrderError.
class StudentOrderDAOImplTest {

    @BeforeAll
    public static void startUp() throws Exception {
        DBInit.startUp();
    }

    public StudentOrder buildStudentOrder(long id) {
        StudentOrder studentOrder = new StudentOrder();
        studentOrder.setStudentOrderId(id);

        MarriageCertificate marriageCertificate = new MarriageCertificate("" + (123456000 + id),
                new RegisterOffice(1L, "", ""), LocalDate.of(2014, 12, 3));
        studentOrder.setMarriageCertificate(marriageCertificate);

        Street street = new Street(1L, "Сампмсоньевский проспект");
        Address address = new Address("195000", street, "10", "2", "121");

        Adult husband = new Adult("Васильев", "Павел", "Николаевич", LocalDate.of(1995, 3, 18));
        husband.setUniversity(new University(2L, ""));
        husband.setStudentId("" + (10000 + id));
        husband.setAddress(address);
        Passport husbandPassport = new Passport("" + (1000 + id), "" + (100000 + id), LocalDate.of(2000, 4, 11),
                new PassportOffice(2L, "", ""));
        husband.setPassport(husbandPassport);

        Adult wife = new Adult("Васильева", "Ирина", "Петровна", LocalDate.of(1997, 8, 21));
        wife.setUniversity(new University(1L, ""));
        wife.setStudentId("" + (200000 + id));
        wife.setAddress(address);
        Passport wifePassport = new Passport("" + (2000 + id), "" + (200000 + id), LocalDate.of(2004, 4, 1),
                new PassportOffice(3L, "", ""));
        wife.setPassport(wifePassport);

        Child child1 = new Child("Васильева", "Евгения", "Павловна", LocalDate.of(2016, 1, 11));
        BirthCertificate birthCertificate1 = new BirthCertificate("" + (300000 + id),
                new RegisterOffice(4L, "", ""), LocalDate.of(2018, 3, 23));
        child1.setBirthCertificate(birthCertificate1);
        child1.setAddress(address);

        Child child2 = new Child("Васильев", "Александр", "Павлович", LocalDate.of(2018, 10, 24));
        BirthCertificate birthCertificate2 = new BirthCertificate("" + (400000 + id),
                new RegisterOffice(5L, "", ""), LocalDate.of(2020, 5, 30));
        child2.setBirthCertificate(birthCertificate2);
        child2.setAddress(address);

        studentOrder.setHusband(husband);
        studentOrder.setWife(wife);
        studentOrder.addChild(child1);
        studentOrder.addChild(child2);
        return studentOrder;
    }

    @Order(1)
    @Test
    void saveStudentOrder() throws DAOException {
        StudentOrderDAO dao = DAOProvider.getInstance().getStudentOrderDAO();

        StudentOrder studentOrder = buildStudentOrder(10);
        Long id = dao.saveStudentOrder(studentOrder);
        Assertions.assertEquals(1, id);
    }

    @Order(2)
    @Test
    void getStudentOrders() throws DAOException {
        List<StudentOrder> studentOrders = new StudentOrderDAOImpl().getStudentOrders();
        Assertions.assertEquals(1, studentOrders.size());
    }

    @Order(3)
    @Test
    void saveStudentOrderError() {
        StudentOrderDAO dao = DAOProvider.getInstance().getStudentOrderDAO();

        StudentOrder so = buildStudentOrder(10);
        so.getHusband().setSurName(null);
        Assertions.assertThrows(DAOException.class, () -> dao.saveStudentOrder(so));
    }
}