package edu.jcourse.student_order.mail;

import edu.jcourse.student_order.domain.StudentOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailSender {

    private static final Logger logger = LoggerFactory.getLogger(MailSender.class);

    public void sendMail(StudentOrder studentOrder) {
        logger.info("Mail is sent");
    }
}
