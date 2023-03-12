package edu.jcourse.student_order.dao.impl;

import edu.jcourse.student_order.dao.ConnectionBuilder;
import edu.jcourse.student_order.dao.DictionaryDAO;
import edu.jcourse.student_order.domain.CountryArea;
import edu.jcourse.student_order.domain.Street;
import edu.jcourse.student_order.domain.office.PassportOffice;
import edu.jcourse.student_order.domain.office.RegisterOffice;
import edu.jcourse.student_order.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDAOImpl implements DictionaryDAO {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryDAOImpl.class);

    private static final String GET_STREET = "SELECT * FROM jc_street " +
            "WHERE UPPER(street_name) LIKE UPPER(?)";
    private static final String GET_REGISTER = "SELECT * FROM jc_register_office " +
            "WHERE r_office_area_id = ?";
    private static final String GET_PASSPORT = "SELECT * FROM jc_passport_office " +
            "WHERE p_office_area_id = ?";
    private static final String GET_AREA = "SELECT * FROM jc_country_struct " +
            "WHERE area_id LIKE ? AND area_id != ?";

    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    @Override
    public List<Street> findStreets(String pattern) throws DAOException {
        List<Street> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_STREET)) {

            preparedStatement.setString(1, "%" + pattern + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new Street(
                        resultSet.getLong("street_code"),
                        resultSet.getString("street_name")));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public List<PassportOffice> findPassportOffices(String areaID) throws DAOException {
        List<PassportOffice> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PASSPORT)) {

            preparedStatement.setString(1, areaID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new PassportOffice(
                        resultSet.getLong("p_office_id"),
                        resultSet.getString("p_office_area_id"),
                        resultSet.getString("p_office_name")));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public List<RegisterOffice> findRegisterOffices(String areaID) throws DAOException {
        List<RegisterOffice> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_REGISTER)) {

            preparedStatement.setString(1, areaID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new RegisterOffice(
                        resultSet.getLong("r_office_id"),
                        resultSet.getString("r_office_area_id"),
                        resultSet.getString("r_office_name")));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public List<CountryArea> findAreas(String areaID) throws DAOException {
        List<CountryArea> result = new LinkedList<>();

        logger.debug("AreaID:{}", areaID);

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_AREA)) {

            String param1 = buildParam(areaID);
            String param2 = areaID;
            if (areaID == null) {
                param2 = "";
            }

            preparedStatement.setString(1, param1);
            preparedStatement.setString(2, param2);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new CountryArea(
                        resultSet.getString("area_id"),
                        resultSet.getString("area_name")));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    private String buildParam(String areaID) throws SQLException {
        if (areaID == null || areaID.isBlank()) {
            return "__0000000000";
        } else if (areaID.endsWith("0000000000")) {
            return areaID.substring(0, 2) + "___0000000";
        } else if (areaID.endsWith("0000000")) {
            return areaID.substring(0, 5) + "___0000";
        } else if (areaID.endsWith("0000")) {
            return areaID.substring(0, 8) + "____";
        }
        throw new SQLException("Invalid parameter 'areaID':" + areaID);
    }
}
