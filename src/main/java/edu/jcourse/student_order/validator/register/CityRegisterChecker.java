package edu.jcourse.student_order.validator.register;

import edu.jcourse.student_order.domain.Person;
import edu.jcourse.student_order.domain.register.CityRegisterResponse;
import edu.jcourse.student_order.exception.CityRegisterException;

public interface CityRegisterChecker {

    CityRegisterResponse checkPerson(Person person) throws CityRegisterException;
}
