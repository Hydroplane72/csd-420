
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StyledCirclesAppTest {

    @Test
    void testCreateContentBuildsFourCirclesWithExpectedStylesAndIds() {
        StyledCirclesApp app = new StyledCirclesApp();
        Pane content = app.createContent();

        List<Circle> circles = content.getChildren()
                .stream()
                .filter(Circle.class::isInstance)
                .map(Circle.class::cast)
                .toList();

        assertEquals(4, circles.size(), "The app must render exactly four circles.");
        circles.forEach(circle -> assertTrue(circle.getStyleClass().contains("plaincircle"),
                "Each circle must use the plaincircle style class."));

        long redCount = circles.stream().filter(circle -> "redcircle".equals(circle.getId())).count();
        long greenCount = circles.stream().filter(circle -> "greencircle".equals(circle.getId())).count();

        assertEquals(1, redCount, "Exactly one circle should have the redcircle ID.");
        assertEquals(1, greenCount, "Exactly one circle should have the greencircle ID.");
    }

    @Test
    void testCreateContentAppliesBorderStyleToRootPane() {
        StyledCirclesApp app = new StyledCirclesApp();
        Pane content = app.createContent();

        assertTrue(content.getStyleClass().contains("border"),
                "The root pane should use the border style class.");
    }

    @Test
    void testStylesheetCanBeResolved() {
        StyledCirclesApp app = new StyledCirclesApp();

        String stylesheetUrl = app.getStylesheetUrl();
        assertNotNull(stylesheetUrl, "Stylesheet URL should not be null.");
        assertTrue(stylesheetUrl.endsWith("mystyle.css"),
                "Stylesheet URL should point to mystyle.css.");
    }
}
