package edu.jcourse.student_order.dao;

import edu.jcourse.student_order.dao.impl.DictionaryDAOImpl;
import edu.jcourse.student_order.dao.impl.StudentOrderDAOImpl;

public class DAOProvider {

    private static volatile DAOProvider instance;

    private final DictionaryDAO dictionaryDAO;
    private final StudentOrderDAO studentOrderDAO;

    private DAOProvider() {
        dictionaryDAO = new DictionaryDAOImpl();
        studentOrderDAO = new StudentOrderDAOImpl();
    }

    public static DAOProvider getInstance() {
        if (instance != null) {
            return instance;
        }

        synchronized (DAOProvider.class) {
            if (instance == null) {
                instance = new DAOProvider();
            }
            return instance;
        }
    }

    public DictionaryDAO getDictionaryDAO() {
        return dictionaryDAO;
    }

    public StudentOrderDAO getStudentOrderDAO() {
        return studentOrderDAO;
    }
}
