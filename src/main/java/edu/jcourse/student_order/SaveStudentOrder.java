package edu.jcourse.student_order;

import edu.jcourse.student_order.domain.StudentOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveStudentOrder {

    private static final Logger logger = LoggerFactory.getLogger(SaveStudentOrder.class);

    public static long saveStudentOrder(StudentOrder studentOrder) {
        long answer = 199;
        logger.info("Saving Student Order");
        return answer;
    }
}
