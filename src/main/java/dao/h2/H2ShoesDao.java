package dao.h2;

import dao.ShoesDao;
import domain.Color;
import domain.Gender;
import domain.Shoes;
import domain.Size;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;

public class H2ShoesDao implements ShoesDao {

    static {
        try {
            Class.forName("org.h2.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "");
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(
                        "CREATE TABLE Shoes (" +
                                "id INT PRIMARY KEY AUTO_INCREMENT," +
                                " size VARCHAR(255) NOT NULL," +
                                " color VARCHAR(255)," +
                                " gender BOOLEAN DEFAULT FALSE);" +
                                "INSERT INTO Shoes (size, color, gender)" +
                                "VALUES ('LARGE', 'BLACK', TRUE);");

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SneakyThrows
    public Shoes get(long id) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "")) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Shoes where id=?"); {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next() ? new Shoes(
                            resultSet.getLong("id"),
                            Size.valueOf(resultSet.getString("size")),
                            Color.valueOf(resultSet.getString("color")),
                            resultSet.getBoolean("gender") ? Gender.MALE : Gender.FEMALE) : null;
                }
            }
        }
    }

    @Override
    public List<Shoes> getAll() {
        return null;
    }

    @Override
    public void add(Shoes shoes) {

    }

    @Override
    public void save(Shoes shoes) {

    }

    @Override
    @SneakyThrows
    public void delete(Shoes shoes) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM Dress WHERE id =" + shoes.getId());
        }
    }
}
