package edu.jcourse.student_order.validator.register;

import edu.jcourse.student_order.domain.Adult;
import edu.jcourse.student_order.domain.Person;
import edu.jcourse.student_order.domain.register.CityRegisterResponse;
import edu.jcourse.student_order.exception.CityRegisterException;

public class FakeCityRegisterChecker implements CityRegisterChecker {

    private static final String HUSBAND_SERIES = "40";
    private static final String WIFE_SERIES = "50";
    private static final String ERROR_MESSAGE = "GRN Error";
    private static final String ERROR_T_MESSAGE = "Fake_Transport Error";

    @Override
    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException {
        CityRegisterResponse response = null;
        if (person instanceof Adult adult) {
            String passportSeries = adult.getPassport().getPassportSeries();
            if (passportSeries.startsWith(HUSBAND_SERIES) || passportSeries.startsWith(WIFE_SERIES)) {
                response = new CityRegisterResponse(true, false);
            } else {
                throw new CityRegisterException("1", ERROR_MESSAGE);
            }
        }
        return response;
    }
}
