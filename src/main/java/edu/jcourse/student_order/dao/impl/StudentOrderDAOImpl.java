package edu.jcourse.student_order.dao.impl;

import edu.jcourse.student_order.config.Config;
import edu.jcourse.student_order.dao.ConnectionBuilder;
import edu.jcourse.student_order.dao.StudentOrderDAO;
import edu.jcourse.student_order.domain.*;
import edu.jcourse.student_order.domain.document.BirthCertificate;
import edu.jcourse.student_order.domain.document.MarriageCertificate;
import edu.jcourse.student_order.domain.document.Passport;
import edu.jcourse.student_order.domain.office.Office;
import edu.jcourse.student_order.domain.office.PassportOffice;
import edu.jcourse.student_order.domain.office.RegisterOffice;
import edu.jcourse.student_order.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentOrderDAOImpl implements StudentOrderDAO {

    private static final Logger logger = LoggerFactory.getLogger(StudentOrderDAOImpl.class);

    private static final String INSERT_ORDER = "INSERT INTO jc_student_order (student_order_status, student_order_date, h_sur_name, h_given_name," +
            "                              h_patronymic, h_date_of_birth, h_passport_series, h_passport_number, h_passport_date," +
            "                              h_passport_office_id, h_post_index, h_street_code, h_building, h_extension, h_apartment, h_university_id," +
            "                              h_student_number,w_sur_name, w_given_name, w_patronymic, w_date_of_birth, w_passport_series," +
            "                              w_passport_number, w_passport_date, w_passport_office_id, w_post_index, w_street_code," +
            "                              w_building, w_extension, w_apartment,w_university_id, w_student_number, certificate, register_office_id, marriage_date)" +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String INSERT_CHILD = "INSERT INTO jc_student_child (student_order_id, c_sur_name, c_given_name, c_patronymic," +
            "                              c_date_of_birth, c_certificate_number, c_certificate_date, c_register_office_id," +
            "                              c_post_index, c_street_code, c_building, c_extension, c_apartment)" +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";

    private static final String SELECT_ORDERS = "SELECT so.*, " +
            "   ro.r_office_area_id, " +
            "   ro.r_office_name, " +
            "   po_h.p_office_area_id as h_p_office_area_id," +
            "   po_h.p_office_name    as h_p_office_name, " +
            "   po_w.p_office_area_id as w_p_office_area_id," +
            "   po_w.p_office_name    as w_p_office_name, " +
            "   str_h.street_name     as h_street_name, " +
            "   str_w.street_name     as w_street_name, " +
            "   u_h.university_name   as h_university_name, " +
            "   u_w.university_name   as w_university_name " +
            "FROM jc_student_order so " +
            "       INNER JOIN jc_register_office ro ON so.register_office_id = ro.r_office_id " +
            "       INNER JOIN jc_passport_office po_h ON po_h.p_office_id = so.h_passport_office_id " +
            "       INNER JOIN jc_passport_office po_w ON po_w.p_office_id = so.w_passport_office_id " +
            "       INNER JOIN jc_street str_h ON str_h.street_code = so.h_street_code " +
            "       INNER JOIN jc_street str_w ON str_w.street_code = so.w_street_code " +
            "       INNER JOIN jc_university u_h ON u_h.university_id = so.h_university_id " +
            "       INNER JOIN jc_university u_w ON u_w.university_id = so.w_university_id " +
            "WHERE so.student_order_status = ? ORDER BY so.student_order_date LIMIT ?";

    private static final String SELECT_CHILD = "SELECT sc.*, " +
            "       roc.r_office_area_id as c_r_office_area_id, " +
            "       roc.r_office_name    as c_r_office_name, " +
            "       str_c.street_name      as c_street_name " +
            "FROM jc_student_child sc " +
            "         INNER JOIN jc_register_office roc ON roc.r_office_id = sc.c_register_office_id " +
            "         INNER JOIN jc_street str_c ON sc.c_street_code = str_c.street_code " +
            "WHERE sc.student_order_id IN ";

    private static final String SELECT_ORDERS_FULL = "SELECT so.*, " +
            "   ro.r_office_area_id, " +
            "   ro.r_office_name, " +
            "   po_h.p_office_area_id as h_p_office_area_id," +
            "   po_h.p_office_name    as h_p_office_name, " +
            "   po_w.p_office_area_id as w_p_office_area_id," +
            "   po_w.p_office_name    as w_p_office_name, " +
            "   str_h.street_name     as h_street_name, " +
            "   str_w.street_name     as w_street_name, " +
            "   u_h.university_name   as h_university_name, " +
            "   u_w.university_name   as w_university_name, " +
            "   sc.*, " +
            "   roc.r_office_area_id  as c_r_office_area_id, " +
            "   roc.r_office_name     as c_r_office_name, " +
            "   str_c.street_name     as c_street_name  " +
            "FROM jc_student_order so " +
            "       INNER JOIN jc_register_office ro ON so.register_office_id = ro.r_office_id " +
            "       INNER JOIN jc_passport_office po_h ON po_h.p_office_id = so.h_passport_office_id " +
            "       INNER JOIN jc_passport_office po_w ON po_w.p_office_id = so.w_passport_office_id " +
            "       INNER JOIN jc_street str_h ON str_h.street_code = so.h_street_code " +
            "       INNER JOIN jc_street str_w ON str_w.street_code = so.w_street_code " +
            "       INNER JOIN jc_university u_h ON u_h.university_id = so.h_university_id " +
            "       INNER JOIN jc_university u_w ON u_w.university_id = so.w_university_id " +
            "       INNER JOIN jc_student_child sc ON sc.student_order_id = so.student_order_id " +
            "       INNER JOIN jc_register_office roc ON roc.r_office_id = sc.c_register_office_id " +
            "       INNER JOIN jc_street str_c ON sc.c_street_code = str_c.street_code " +
            "WHERE so.student_order_status = ? ORDER BY so.student_order_id LIMIT ?";

    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    @Override
    public Long saveStudentOrder(StudentOrder so) throws DAOException {
        Long studentOrderId = -1L;

        logger.info("StudentOrder:{}", so);

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER, new String[]{"student_order_id"})) {

            connection.setAutoCommit(false);
            try {
                int indexForHusband = 3;
                int indexForWife = 18;

                preparedStatement.setInt(1, StudentOrderStatus.START.ordinal());
                preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));

                setParamsForAdult(preparedStatement, indexForHusband, so.getHusband());
                setParamsForAdult(preparedStatement, indexForWife, so.getWife());

                MarriageCertificate certificate = so.getMarriageCertificate();
                preparedStatement.setString(33, certificate.getMarriageCertificateId());
                preparedStatement.setLong(34, certificate.getMarriageOffice().getOfficeID());
                preparedStatement.setDate(35, Date.valueOf(certificate.getMarriageDate()));

                preparedStatement.executeUpdate();
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    studentOrderId = generatedKeys.getLong(1);
                }

                generatedKeys.close();

                saveChildren(connection, so, studentOrderId);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return studentOrderId;
    }

    private void saveChildren(Connection connection, StudentOrder so, Long studentOrderId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CHILD)) {
            for (Child child : so.getChildren()) {
                preparedStatement.setLong(1, studentOrderId);
                setParamsForChild(preparedStatement, child);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    private void setParamsForAdult(PreparedStatement preparedStatement, int index, Adult adult) throws SQLException {
        index = setParamsForPerson(preparedStatement, index, adult);

        Passport passport = adult.getPassport();
        preparedStatement.setString(index++, passport.getPassportSeries());
        preparedStatement.setString(index++, passport.getPassportNumber());
        preparedStatement.setDate(index++, Date.valueOf(passport.getIssueDate()));
        preparedStatement.setLong(index++, passport.getIssueDepartment().getOfficeID());

        index = setParamsForAddress(preparedStatement, index, adult);
        preparedStatement.setLong(index++, adult.getUniversity().getUniversityID());
        preparedStatement.setString(index, adult.getStudentId());
    }

    private void setParamsForChild(PreparedStatement preparedStatement, Child child) throws SQLException {
        int indexForChild = 2;
        indexForChild = setParamsForPerson(preparedStatement, indexForChild, child);

        BirthCertificate certificate = child.getBirthCertificate();
        preparedStatement.setString(indexForChild++, certificate.getCertificateNumber());
        preparedStatement.setDate(indexForChild++, Date.valueOf(certificate.getIssueDate()));
        preparedStatement.setLong(indexForChild++, certificate.getIssueDepartment().getOfficeID());

        setParamsForAddress(preparedStatement, indexForChild, child);
    }

    private int setParamsForPerson(PreparedStatement preparedStatement, int index, Person person) throws SQLException {
        preparedStatement.setString(index++, person.getSurName());
        preparedStatement.setString(index++, person.getGivenName());
        preparedStatement.setString(index++, person.getPatronymic());
        preparedStatement.setDate(index++, Date.valueOf(person.getDateOfBirth()));
        return index;
    }

    private int setParamsForAddress(PreparedStatement preparedStatement, int index, Person person) throws SQLException {
        Address address = person.getAddress();
        preparedStatement.setString(index++, address.getPostCode());
        preparedStatement.setLong(index++, address.getStreet().getStreetCode());
        preparedStatement.setString(index++, address.getBuilding());
        preparedStatement.setString(index++, address.getExtension());
        preparedStatement.setString(index++, address.getApartment());
        return index;
    }

    @Override
    public List<StudentOrder> getStudentOrders() throws DAOException {
        return getStudentOrdersOneSelect();
//        return getStudentOrdersTwoSelect();
    }

    private List<StudentOrder> getStudentOrdersTwoSelect() throws DAOException {
        Map<Long, StudentOrder> studentOrders = new HashMap<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS)) {

            preparedStatement.setInt(1, StudentOrderStatus.START.ordinal());
            preparedStatement.setInt(2, Integer.parseInt(Config.getProperties(Config.DB_LIMIT)));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StudentOrder studentOrder = getFullStudentOrder(resultSet);
                studentOrders.put(studentOrder.getStudentOrderId(), studentOrder);
            }
            findChildren(connection, studentOrders);

            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return new ArrayList<>(studentOrders.values());
    }

    private List<StudentOrder> getStudentOrdersOneSelect() throws DAOException {
        Map<Long, StudentOrder> studentOrders = new HashMap<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_FULL)) {

            preparedStatement.setInt(1, StudentOrderStatus.START.ordinal());
            int limit = Integer.parseInt(Config.getProperties(Config.DB_LIMIT));
            preparedStatement.setInt(2, limit);


            ResultSet resultSet = preparedStatement.executeQuery();
            int counter = 0;
            while (resultSet.next()) {
                Long studentOrderId = resultSet.getLong("student_order_id");
                if (!studentOrders.containsKey(studentOrderId)) {
                    StudentOrder studentOrder = getFullStudentOrder(resultSet);
                    fillChild(resultSet, studentOrder);
                    studentOrders.put(studentOrderId, studentOrder);
                } else {
                    fillChild(resultSet, studentOrders.get(studentOrderId));
                }
                counter++;
            }
            if (counter >= limit) {
                studentOrders.remove(studentOrders.size() - 1L);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return new ArrayList<>(studentOrders.values());
    }

    private StudentOrder getFullStudentOrder(ResultSet resultSet) throws SQLException {
        StudentOrder studentOrder = new StudentOrder();
        fillStudentOrder(resultSet, studentOrder);
        fillMarriage(resultSet, studentOrder);
        fillAdult(resultSet, studentOrder, "h_");
        fillAdult(resultSet, studentOrder, "w_");
        return studentOrder;
    }

    private void findChildren(Connection connection, Map<Long, StudentOrder> studentOrders) throws SQLException {
        String ids = studentOrders.values().stream().map(so -> String.valueOf(so.getStudentOrderId()))
                .collect(Collectors.joining(",", "(", ")"));

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CHILD + ids)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                fillChild(resultSet, studentOrders.get(resultSet.getLong("student_order_id")));
            }
            resultSet.close();
        }
    }

    private void fillStudentOrder(ResultSet resultSet, StudentOrder studentOrder) throws SQLException {
        studentOrder.setStudentOrderId(resultSet.getLong("student_order_id"));
        studentOrder.setStudentOrderStatus(StudentOrderStatus.fromValue(resultSet.getInt("student_order_status")));
        studentOrder.setStudentOrderDate(resultSet.getTimestamp("student_order_date").toLocalDateTime());
    }

    private void fillMarriage(ResultSet resultSet, StudentOrder studentOrder) throws SQLException {
        MarriageCertificate certificate = new MarriageCertificate();
        certificate.setMarriageCertificateId(resultSet.getString("certificate"));
        certificate.setMarriageDate(resultSet.getDate("marriage_date").toLocalDate());

        RegisterOffice office = new RegisterOffice();
        office.setOfficeID(resultSet.getLong("register_office_id"));
        fillOffice(resultSet, office, "r_");

        certificate.setMarriageOffice(office);
        studentOrder.setMarriageCertificate(certificate);
    }

    private void fillAdult(ResultSet resultSet, StudentOrder studentOrder, String prefix) throws SQLException {
        Adult adult = new Adult();
        fillPerson(resultSet, adult, prefix);

        Passport passport = new Passport();
        passport.setPassportSeries(resultSet.getString(prefix + "passport_series"));
        passport.setPassportNumber(resultSet.getString(prefix + "passport_number"));
        passport.setIssueDate(resultSet.getDate(prefix + "passport_date").toLocalDate());

        PassportOffice office = new PassportOffice();
        office.setOfficeID(resultSet.getLong(prefix + "passport_office_id"));
        fillOffice(resultSet, office, prefix + "p_");

        passport.setIssueDepartment(office);
        adult.setPassport(passport);

        University university = new University();
        university.setUniversityID(resultSet.getLong(prefix + "university_id"));
        university.setUniversityName(resultSet.getString(prefix + "university_name"));

        adult.setUniversity(university);
        adult.setStudentId(resultSet.getString(prefix + "student_number"));

        if (prefix.equals("h_")) {
            studentOrder.setHusband(adult);
        } else if (prefix.equals("w_")) {
            studentOrder.setWife(adult);
        }
    }

    private void fillChild(ResultSet resultSet, StudentOrder studentOrder) throws SQLException {
        Child child = new Child();
        fillPerson(resultSet, child, "c_");

        BirthCertificate certificate = new BirthCertificate();
        certificate.setCertificateNumber(resultSet.getString("c_certificate_number"));
        certificate.setIssueDate(resultSet.getDate("c_certificate_date").toLocalDate());

        RegisterOffice office = new RegisterOffice();
        office.setOfficeID(resultSet.getLong("c_register_office_id"));
        fillOffice(resultSet, office, "c_r_");
        certificate.setIssueDepartment(office);

        child.setBirthCertificate(certificate);
        studentOrder.addChild(child);
    }

    private void fillPerson(ResultSet resultSet, Person person, String prefix) throws SQLException {
        person.setSurName(resultSet.getString(prefix + "sur_name"));
        person.setGivenName(resultSet.getString(prefix + "given_name"));
        person.setPatronymic(resultSet.getString(prefix + "patronymic"));
        person.setDateOfBirth(resultSet.getDate(prefix + "date_of_birth").toLocalDate());

        Address address = new Address();
        address.setPostCode(resultSet.getString(prefix + "post_index"));
        address.setBuilding(resultSet.getString(prefix + "building"));
        address.setExtension(resultSet.getString(prefix + "extension"));
        address.setApartment(resultSet.getString(prefix + "apartment"));

        Street street = new Street();
        street.setStreetCode(resultSet.getLong(prefix + "street_code"));
        street.setStreetName(resultSet.getString(prefix + "street_name"));

        address.setStreet(street);
        person.setAddress(address);
    }

    private void fillOffice(ResultSet resultSet, Office office, String prefix) throws SQLException {
        office.setOfficeAreaId(resultSet.getString(prefix + "office_area_id"));
        office.setOfficeName(resultSet.getString(prefix + "office_name"));
    }
}