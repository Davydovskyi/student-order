package edu.jcourse.student_order.validator.register;

import edu.jcourse.student_order.domain.Person;
import edu.jcourse.student_order.domain.register.CityRegisterRequest;
import edu.jcourse.student_order.domain.register.CityRegisterResponse;
import edu.jcourse.student_order.exception.CityRegisterException;
import edu.jcourse.student_order.exception.TransportException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;

public class RealCityRegisterChecker implements CityRegisterChecker {

    @Override
    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException, TransportException {
        CityRegisterRequest request = new CityRegisterRequest(person);
        CityRegisterResponse response;

        try (Client client = ClientBuilder.newClient()) {
            response = client.target("http://localhost:8080/city-register-1.0/rest/check")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(request, MediaType.APPLICATION_JSON))
                    .readEntity(CityRegisterResponse.class);
        }
        return response;
    }
}
