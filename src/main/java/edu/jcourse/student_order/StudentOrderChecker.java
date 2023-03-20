package edu.jcourse.student_order;

import edu.jcourse.student_order.dao.DAOProvider;
import edu.jcourse.student_order.domain.StudentOrder;
import edu.jcourse.student_order.domain.children.AnswerChildren;
import edu.jcourse.student_order.domain.register.AnswerCityRegister;
import edu.jcourse.student_order.domain.student.AnswerStudent;
import edu.jcourse.student_order.domain.wedding.AnswerWedding;
import edu.jcourse.student_order.exception.DAOException;
import edu.jcourse.student_order.mail.MailSender;
import edu.jcourse.student_order.validator.ChildrenValidator;
import edu.jcourse.student_order.validator.CityRegisterValidator;
import edu.jcourse.student_order.validator.StudentValidator;
import edu.jcourse.student_order.validator.WeddingValidator;

import java.util.List;

public class StudentOrderChecker {

    private CityRegisterValidator cityRegisterValidator;
    private StudentValidator studentValidator;
    private WeddingValidator weddingValidator;
    private ChildrenValidator childrenValidator;
    private MailSender mailSender;

    public StudentOrderChecker() {
        cityRegisterValidator = new CityRegisterValidator();
        studentValidator = new StudentValidator();
        weddingValidator = new WeddingValidator();
        childrenValidator = new ChildrenValidator();
        mailSender = new MailSender();
    }

    public static void main(String[] args) {
        StudentOrderChecker checker = new StudentOrderChecker();
        checker.checkAll();
    }

    public List<StudentOrder> readStudentOrders() throws DAOException {
        return DAOProvider.getInstance().getStudentOrderDAO().getStudentOrders();
    }

    public void checkAll() {
        try {
            List<StudentOrder> studentOrder = readStudentOrders();
            studentOrder.forEach(this::checkOneOrder);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void checkOneOrder(StudentOrder so) {
        AnswerCityRegister cityAnswer = checkCityRegister(so);
//        AnswerWedding weddingAnswer = checkWedding(so);
//        AnswerChildren childrenAnswer = checkChildren(so);
//        AnswerStudent studentAnswer = checkStudent(so);
//
//        sendMail(so);
    }

    public AnswerCityRegister checkCityRegister(StudentOrder studentOrder) {
        return cityRegisterValidator.checkCityRegister(studentOrder);
    }

    public AnswerStudent checkStudent(StudentOrder studentOrder) {
        return studentValidator.checkStudent(studentOrder);
    }

    public AnswerWedding checkWedding(StudentOrder studentOrder) {
        return weddingValidator.checkWedding(studentOrder);
    }

    public AnswerChildren checkChildren(StudentOrder studentOrder) {
        return childrenValidator.checkChildren(studentOrder);
    }

    public void sendMail(StudentOrder studentOrder) {
        mailSender.sendMail(studentOrder);
    }
}