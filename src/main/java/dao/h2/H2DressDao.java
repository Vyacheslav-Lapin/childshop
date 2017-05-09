package dao.h2;

import dao.DressDao;
import domain.Color;
import domain.Dress;
import domain.Size;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;

public class H2DressDao implements DressDao {



    static {
        try {
            Class.forName("org.h2.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "");
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(
                        "CREATE TABLE Dress (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT," +
                        " size VARCHAR(255) NOT NULL," +
                        " color VARCHAR(255)," +
                        "INSERT INTO Dress (size, color)" +
                        "VALUES ('MEDIUM', 'RED');");

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SneakyThrows
    public Dress get(long id) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Dress where id=?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? new Dress(
                        resultSet.getLong("id"),
                        Size.valueOf(resultSet.getString("size")),
                        Color.valueOf(resultSet.getString("color"))) : null;
            }
        }
    }

    @Override
    public List<Dress> getAll() {
        return null;
    }

    @Override
    public void add(Dress dress) {

    }

    @Override
    public void save(Dress dress) {

    }

    @Override
    @SneakyThrows
    public void delete(Dress dress) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM Dress WHERE id =" + dress.getId());
        }
    }
}
