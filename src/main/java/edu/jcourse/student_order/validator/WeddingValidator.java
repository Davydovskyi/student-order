package edu.jcourse.student_order.validator;

import edu.jcourse.student_order.domain.wedding.AnswerWedding;
import edu.jcourse.student_order.domain.StudentOrder;
import edu.jcourse.student_order.mail.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeddingValidator {

    private static final Logger logger = LoggerFactory.getLogger(WeddingValidator.class);

    private String hostName;
    private String login;
    private String password;

    public AnswerWedding checkWedding(StudentOrder studentOrder) {
        logger.info("Wedding check is running");
        return new AnswerWedding();
    }
}
