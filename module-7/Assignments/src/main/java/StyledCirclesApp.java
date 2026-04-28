import java.util.List;
import java.util.Objects;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class StyledCirclesApp extends Application {

    Pane createContent() {
        Pane root = new Pane();
        root.setPrefSize(300, 300);
        root.getStyleClass().add("border");

        Circle circle1 = new Circle(75, 75, 45);
        Circle circle2 = new Circle(225, 75, 45);
        Circle circle3 = new Circle(75, 225, 45);
        Circle circle4 = new Circle(225, 225, 45);

        List<Circle> circles = List.of(circle1, circle2, circle3, circle4);
        circles.forEach(circle -> circle.getStyleClass().add("plaincircle"));

        circle3.setId("redcircle");
        circle4.setId("greencircle");

        root.getChildren().addAll(circles);
        return root;
    }

    String getStylesheetUrl() {
        return Objects.requireNonNull(getClass().getResource("mystyle.css"),
                "Unable to find mystyle.css on the classpath")
                .toExternalForm();
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent());
        scene.getStylesheets().add(getStylesheetUrl());

        primaryStage.setTitle("Styled Circles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
