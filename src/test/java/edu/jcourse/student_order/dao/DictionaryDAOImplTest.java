package edu.jcourse.student_order.dao;

import edu.jcourse.student_order.domain.CountryArea;
import edu.jcourse.student_order.domain.Street;
import edu.jcourse.student_order.domain.office.PassportOffice;
import edu.jcourse.student_order.domain.office.RegisterOffice;
import edu.jcourse.student_order.exception.DAOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class DictionaryDAOImplTest {

    @BeforeAll
    public static void startUp() throws Exception {
        DBInit.startUp();
    }

    @Test
    void testStreet() throws DAOException {
        DictionaryDAO dao = new DictionaryDAOImpl();

        List<Street> streets1 = dao.findStreets("сад");
        Assertions.assertEquals(1, streets1.size());

        List<Street> streets2 = dao.findStreets("as");
        Assertions.assertEquals(0, streets2.size());

        List<Street> streets3 = dao.findStreets(null);
        Assertions.assertEquals(0, streets3.size());

        List<Street> streets4 = dao.findStreets(" ");
        Assertions.assertEquals(5, streets4.size());
    }

    @Test
    void testPassportOffice() throws DAOException {
        DictionaryDAO dao = new DictionaryDAOImpl();

        List<PassportOffice> passportOffices1 = dao.findPassportOffices("010020000000");
        Assertions.assertEquals(2, passportOffices1.size());

        List<PassportOffice> passportOffices2 = dao.findPassportOffices("0100");
        Assertions.assertEquals(0, passportOffices2.size());

        List<PassportOffice> passportOffices3 = dao.findPassportOffices(" ");
        Assertions.assertEquals(0, passportOffices3.size());

        List<PassportOffice> passportOffices4 = dao.findPassportOffices(null);
        Assertions.assertEquals(0, passportOffices4.size());
    }

    @Test
    void testRegisterOffice() throws DAOException {
        DictionaryDAO dao = new DictionaryDAOImpl();

        List<RegisterOffice> registerOffices1 = dao.findRegisterOffices("010010000000");
        Assertions.assertEquals(2, registerOffices1.size());

        List<RegisterOffice> registerOffices2 = dao.findRegisterOffices("0100");
        Assertions.assertEquals(0, registerOffices2.size());

        List<RegisterOffice> registerOffices3 = dao.findRegisterOffices(" ");
        Assertions.assertEquals(0, registerOffices3.size());

        List<RegisterOffice> registerOffices4 = dao.findRegisterOffices(null);
        Assertions.assertEquals(0, registerOffices4.size());
    }

    @Test
    void testArea() throws DAOException {
        DictionaryDAO dao = new DictionaryDAOImpl();

        List<CountryArea> areas1 = dao.findAreas("");
        Assertions.assertEquals(2, areas1.size());

        List<CountryArea> areas2 = dao.findAreas("020000000000");
        Assertions.assertEquals(2, areas2.size());

        List<CountryArea> areas3 = dao.findAreas("020010000000");
        Assertions.assertEquals(2, areas3.size());

        List<CountryArea> areas4 = dao.findAreas("020010010000");
        Assertions.assertEquals(2, areas4.size());

        List<CountryArea> areas5 = dao.findAreas(null);
        Assertions.assertEquals(2, areas5.size());
    }

    @Test
    void testAreaError() {
        DictionaryDAO dao = new DictionaryDAOImpl();
        Assertions.assertThrows(DAOException.class, () -> dao.findAreas("0200"));
    }
}