package edu.jcourse.student_order.dao;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class DBInit {

    public static void startUp() throws Exception {
        URL structureURL = DictionaryDAOImplTest.class.getClassLoader()
                .getResource("student_project.sql");
        URL dataURL = DictionaryDAOImplTest.class.getClassLoader()
                .getResource("student_data.sql");

        List<String> structure = Files.readAllLines(Path.of(structureURL.toURI()));
        String structureSQL = structure.stream().collect(Collectors.joining("\n"));

        List<String> data = Files.readAllLines(Path.of(dataURL.toURI()));
        String dataSQL = data.stream().collect(Collectors.joining("\n"));

        try (Connection connection = ConnectionBuilder.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(structureSQL);
            statement.executeUpdate(dataSQL);
        }
    }
}
