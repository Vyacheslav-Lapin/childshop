package dao.h2;

import dao.PampersDao;
import domain.Color;
import domain.Gender;
import domain.Pampers;
import domain.Size;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2PampersDao implements PampersDao {

    static {
        try {
            Class.forName("org.h2.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "");
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(
                        "CREATE TABLE Pampers (" +
                                "id INT PRIMARY KEY AUTO_INCREMENT," +
                                " size VARCHAR(255) NOT NULL," +
                                " color VARCHAR(255)," +
                                " gender BOOLEAN DEFAULT FALSE);" +
                                "INSERT INTO Pampers (size, color, gender)" +
                                "VALUES ('SMALL', 'WHITE', TRUE);");

            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @SneakyThrows
    public Pampers get(long id) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Pampers WHERE id=?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? new Pampers(
                        resultSet.getLong("id"),
                        Size.valueOf(resultSet.getString("size")),
                        Color.valueOf(resultSet.getString("color")),
                        resultSet.getBoolean("gender") ? Gender.MALE : Gender.FEMALE) : null;
            }
        }
    }

    @Override
    @SneakyThrows
    public List<Pampers> getAll() {

        List<Pampers> pampers = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Pampers")) {
            while (resultSet.next())
                pampers.add(new Pampers(
                        resultSet.getLong("id"),
                        Size.valueOf(resultSet.getString("size")),
                        Color.valueOf(resultSet.getString("color")),
                        resultSet.getBoolean("gender") ? Gender.MALE : Gender.FEMALE));
        }
        return pampers;
    }

    @Override
    @SneakyThrows
    public void add(Pampers pampers) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "");
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Pampers (color, size, gender) VALUES (?, ?, ?)")) {
            statement.setString(1, pampers.getColor().toString());
            statement.setString(2, pampers.getSize().toString());
            statement.setBoolean(3, pampers.getGender() == Gender.MALE);
            statement.executeUpdate();
        }
    }

    @Override
    public void save(Pampers pampers) {

    }

    @Override
    @SneakyThrows
    public void delete(Pampers pampers) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM Dress WHERE id =" + pampers.getId());
        }
    }
}
