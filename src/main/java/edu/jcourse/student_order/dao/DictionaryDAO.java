package edu.jcourse.student_order.dao;

import edu.jcourse.student_order.domain.CountryArea;
import edu.jcourse.student_order.domain.office.PassportOffice;
import edu.jcourse.student_order.domain.office.RegisterOffice;
import edu.jcourse.student_order.domain.Street;
import edu.jcourse.student_order.exception.DAOException;

import java.util.List;

public interface DictionaryDAO {

    List<Street> findStreets(String pattern) throws DAOException;

    List<PassportOffice> findPassportOffices(String areaID) throws DAOException;

    List<RegisterOffice> findRegisterOffices(String areaID) throws DAOException;

    List<CountryArea> findAreas(String areaID) throws DAOException;



}
