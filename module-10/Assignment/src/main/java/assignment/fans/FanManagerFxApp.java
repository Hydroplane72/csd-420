/**
 * Matthew Rozendaal
 * 05/11/2026
 * Module 10.2
 */
package assignment.fans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FanManagerFxApp {

    private static final String FIND_SQL
            = "SELECT ID, firstname, lastname, favoriteteam FROM fans WHERE ID = ?";
    private static final String UPDATE_SQL
            = "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE ID = ?";

    public static void main(String[] args) {
        Application.launch(FxUiApplication.class, args);
    }

    public static class FxUiApplication extends Application {

        private final TextField idField = new TextField();
        private final TextField firstNameField = new TextField();
        private final TextField lastNameField = new TextField();
        private final TextField favoriteTeamField = new TextField();
        private final Label statusLabel = new Label(" ");

        private DataSource dataSource;

        @Override
        public void start(Stage stage) {
            dataSource = createMySqlDataSource();

            Button displayButton = new Button("Display");
            Button updateButton = new Button("Update");

            displayButton.setOnAction(event -> displayFan());
            updateButton.setOnAction(event -> updateFan());

            GridPane root = new GridPane();
            root.setPadding(new Insets(12));
            root.setHgap(10);
            root.setVgap(10);

            root.add(new Label("ID:"), 0, 0);
            root.add(idField, 1, 0);

            root.add(new Label("First Name:"), 0, 1);
            root.add(firstNameField, 1, 1);

            root.add(new Label("Last Name:"), 0, 2);
            root.add(lastNameField, 1, 2);

            root.add(new Label("Favorite Team:"), 0, 3);
            root.add(favoriteTeamField, 1, 3);

            root.add(displayButton, 0, 4);
            root.add(updateButton, 1, 4);
            root.add(statusLabel, 0, 5, 2, 1);

            stage.setTitle("Fan Information Manager");
            stage.setScene(new Scene(root, 420, 250));
            stage.show();
        }

        private void displayFan() {
            try {
                int id = parseId(idField.getText());
                Optional<Fan> fanResult = findById(dataSource, id);

                if (fanResult.isEmpty()) {
                    firstNameField.clear();
                    lastNameField.clear();
                    favoriteTeamField.clear();
                    statusLabel.setText("No fan found for that ID.");
                    return;
                }

                Fan fan = fanResult.get();
                firstNameField.setText(fan.firstName());
                lastNameField.setText(fan.lastName());
                favoriteTeamField.setText(fan.favoriteTeam());
                statusLabel.setText("Fan record loaded.");
            } catch (IllegalArgumentException ex) {
                statusLabel.setText(ex.getMessage());
            } catch (SQLException ex) {
                statusLabel.setText("Database error: " + ex.getMessage());
            }
        }

        private void updateFan() {
            try {
                Fan fan = buildFanFromFields(
                        idField.getText(),
                        firstNameField.getText(),
                        lastNameField.getText(),
                        favoriteTeamField.getText()
                );

                boolean updated = updateById(dataSource, fan);
                statusLabel.setText(updated ? "Fan record updated." : "No fan found to update.");
            } catch (IllegalArgumentException ex) {
                statusLabel.setText(ex.getMessage());
            } catch (SQLException ex) {
                statusLabel.setText("Database error: " + ex.getMessage());
            }
        }
    }

    static DataSource createMySqlDataSource() {
        String url = System.getenv().getOrDefault("FAN_DB_URL", "jdbc:mysql://localhost:3306/databasedb");
        String user = System.getenv().getOrDefault("FAN_DB_USER", "student1");
        String pass = System.getenv().getOrDefault("FAN_DB_PASSWORD", "pass");

        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL(url);
        ds.setUser(user);
        ds.setPassword(pass);
        return ds;
    }

    static Optional<Fan> findById(DataSource dataSource, int id) throws SQLException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(FIND_SQL)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                return Optional.of(new Fan(
                        rs.getInt("ID"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("favoriteteam")
                ));
            }
        }
    }

    static boolean updateById(DataSource dataSource, Fan fan) throws SQLException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, fan.firstName());
            statement.setString(2, fan.lastName());
            statement.setString(3, fan.favoriteTeam());
            statement.setInt(4, fan.id());
            return statement.executeUpdate() == 1;
        }
    }

    static Fan buildFanFromFields(String idText, String firstName, String lastName, String favoriteTeam) {
        int id = parseId(idText);
        String first = requireText(firstName, "First name");
        String last = requireText(lastName, "Last name");
        String team = requireText(favoriteTeam, "Favorite team");
        return new Fan(id, first, last, team);
    }

    static int parseId(String idText) {
        try {
            int id = Integer.parseInt(idText.trim());
            if (id < 0) {
                throw new IllegalArgumentException("ID must be a positive integer.");
            }
            return id;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("ID must be a valid integer.", ex);
        }
    }

    static String requireText(String value, String name) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(name + " is required.");
        }
        return value.trim();
    }

    record Fan(int id, String firstName, String lastName, String favoriteTeam) {

    }
}
