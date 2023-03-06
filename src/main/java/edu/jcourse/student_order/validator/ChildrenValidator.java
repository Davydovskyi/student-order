package edu.jcourse.student_order.validator;

import edu.jcourse.student_order.domain.StudentOrder;
import edu.jcourse.student_order.domain.children.AnswerChildren;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChildrenValidator {

    private static final Logger logger = LoggerFactory.getLogger(ChildrenValidator.class);

    private String hostName;
    private String login;
    private String password;

    public AnswerChildren checkChildren(StudentOrder studentOrder) {
        logger.info("Children check is running");
        return new AnswerChildren();
    }
}
