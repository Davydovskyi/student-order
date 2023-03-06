package edu.jcourse.student_order.validator;

import edu.jcourse.student_order.domain.Child;
import edu.jcourse.student_order.domain.Person;
import edu.jcourse.student_order.domain.StudentOrder;
import edu.jcourse.student_order.domain.register.AnswerCityRegister;
import edu.jcourse.student_order.domain.register.AnswerCityRegisterItem;
import edu.jcourse.student_order.domain.register.CityRegisterResponse;
import edu.jcourse.student_order.exception.CityRegisterException;
import edu.jcourse.student_order.exception.TransportException;
import edu.jcourse.student_order.validator.register.CityRegisterChecker;
import edu.jcourse.student_order.validator.register.FakeCityRegisterChecker;

public class CityRegisterValidator {

    private static final String IN_CODE = "NO_GRM";

    private CityRegisterChecker personChecker;

    public CityRegisterValidator() {
        personChecker = new FakeCityRegisterChecker();
    }

    public AnswerCityRegister checkCityRegister(StudentOrder studentOrder) {
        AnswerCityRegister answer = new AnswerCityRegister();

        answer.addItem(checkPerson(studentOrder.getHusband()));
        answer.addItem(checkPerson(studentOrder.getWife()));
        for (Child child : studentOrder.getChildren()) {
            answer.addItem(checkPerson(child));
        }

        return answer;
    }

    private AnswerCityRegisterItem checkPerson(Person person) {
        AnswerCityRegisterItem.CityStatus status;
        AnswerCityRegisterItem.CityError error = null;
        try {
            CityRegisterResponse response = personChecker.checkPerson(person);
            status = response.isExisting() ?
                    AnswerCityRegisterItem.CityStatus.YES : AnswerCityRegisterItem.CityStatus.NO;
        } catch (CityRegisterException | TransportException e) {
            e.printStackTrace();
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            if (e instanceof CityRegisterException exception) {
                error = new AnswerCityRegisterItem.CityError(exception.getCode(), e.getMessage());
            } else {
                error = new AnswerCityRegisterItem.CityError(IN_CODE, e.getMessage());
            }
        }

        return new AnswerCityRegisterItem(person, status, error);
    }
}
