package assignment.fans;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Optional;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FanManagerFxAppTest {

    private DataSource dataSource;

    @BeforeEach
    void setUp() throws Exception {
        dataSource = createDataSource();
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS fans");
            statement.execute("""
                CREATE TABLE fans (
                    ID INT PRIMARY KEY,
                    firstname VARCHAR(25),
                    lastname VARCHAR(25),
                    favoriteteam VARCHAR(25)
                )
                """);
            statement.execute("""
                INSERT INTO fans (ID, firstname, lastname, favoriteteam)
                VALUES (1, 'Sam', 'Taylor', 'Lions'),
                       (2, 'Rae', 'Hill', 'Bears')
                """);
        }
    }

    @Test
    void findById_returnsFanWhenPresent() throws Exception {
        Optional<FanManagerFxApp.Fan> fanResult = FanManagerFxApp.findById(dataSource, 1);

        assertTrue(fanResult.isPresent());
        assertEquals("Sam", fanResult.get().firstName());
        assertEquals("Taylor", fanResult.get().lastName());
        assertEquals("Lions", fanResult.get().favoriteTeam());
    }

    @Test
    void findById_returnsEmptyWhenMissing() throws Exception {
        Optional<FanManagerFxApp.Fan> fanResult = FanManagerFxApp.findById(dataSource, 99);
        assertTrue(fanResult.isEmpty());
    }

    @Test
    void updateById_updatesExistingRow() throws Exception {
        boolean updated = FanManagerFxApp.updateById(
                dataSource,
                new FanManagerFxApp.Fan(2, "Rey", "Hill", "Eagles")
        );

        assertTrue(updated);
        Optional<FanManagerFxApp.Fan> fanResult = FanManagerFxApp.findById(dataSource, 2);
        assertTrue(fanResult.isPresent());
        assertEquals("Rey", fanResult.get().firstName());
        assertEquals("Eagles", fanResult.get().favoriteTeam());
    }

    @Test
    void updateById_returnsFalseForMissingRow() throws Exception {
        boolean updated = FanManagerFxApp.updateById(
                dataSource,
                new FanManagerFxApp.Fan(77, "No", "Body", "None")
        );

        assertFalse(updated);
    }

    @Test
    void buildFanFromFields_throwsForBadInput() {
        assertThrows(IllegalArgumentException.class,
                () -> FanManagerFxApp.buildFanFromFields("abc", "A", "B", "C"));

        assertThrows(IllegalArgumentException.class,
                () -> FanManagerFxApp.buildFanFromFields("1", "", "B", "C"));
    }

    private DataSource createDataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:fansDb;MODE=MySQL;DB_CLOSE_DELAY=-1");
        ds.setUser("sa");
        ds.setPassword("");
        return ds;
    }
}
