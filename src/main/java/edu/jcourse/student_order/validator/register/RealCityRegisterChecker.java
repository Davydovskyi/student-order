package edu.jcourse.student_order.validator.register;

import edu.jcourse.student_order.domain.Person;
import edu.jcourse.student_order.domain.register.CityRegisterResponse;
import edu.jcourse.student_order.exception.CityRegisterException;
import edu.jcourse.student_order.exception.TransportException;

public class RealCityRegisterChecker implements CityRegisterChecker {

    @Override
    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException, TransportException {
        return null;
    }
}
