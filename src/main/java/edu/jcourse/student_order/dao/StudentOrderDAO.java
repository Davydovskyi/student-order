package edu.jcourse.student_order.dao;

import edu.jcourse.student_order.domain.StudentOrder;
import edu.jcourse.student_order.exception.DAOException;

import java.util.List;

public interface StudentOrderDAO {

    Long saveStudentOrder(StudentOrder so) throws DAOException;

    List<StudentOrder> getStudentOrders() throws DAOException;
}
