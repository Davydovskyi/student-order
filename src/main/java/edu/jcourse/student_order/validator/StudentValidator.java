package edu.jcourse.student_order.validator;

import edu.jcourse.student_order.domain.StudentOrder;
import edu.jcourse.student_order.domain.student.AnswerStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentValidator {

    private static final Logger logger = LoggerFactory.getLogger(StudentValidator.class);

    private String hostName;
    private String login;
    private String password;

    public AnswerStudent checkStudent(StudentOrder studentOrder) {
        logger.info("Student check is running");
        return new AnswerStudent();
    }
}
