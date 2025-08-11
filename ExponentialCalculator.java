package calculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * ExponentialCalculator
 * Version: 1.0.0
 * Computes a × (b^x) with error handling, accessibility, and GUI best practices.
 */
public class ExponentialCalculator extends Application {

  public static final String VERSION = "1.0.0";

  private final TextField afield = new TextField();
  private final TextField bfield = new TextField();
  private final TextField xfield = new TextField();
  private final Label resultLabel = new Label("Result: ");
  private final Label errorLabel = new Label();

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("a × (b^x) Calculator v" + VERSION);

    GridPane grid = new GridPane();
    grid.setPadding(new Insets(16));
    grid.setHgap(10);
    grid.setVgap(10);

    grid.add(new Label("Multiplier (a):"), 0, 0);
    afield.setPromptText("e.g., 2");
    afield.setAccessibleText("Input for multiplier a");
    grid.add(afield, 1, 0);

    grid.add(new Label("Base (b > 0):"), 0, 1);
    bfield.setPromptText("e.g., 3");
    bfield.setAccessibleText("Input for base b, must be > 0");
    grid.add(bfield, 1, 1);

    grid.add(new Label("Exponent (x):"), 0, 2);
    xfield.setPromptText("e.g., 4");
    xfield.setAccessibleText("Input for exponent x");
    grid.add(xfield, 1, 2);

    Button calcButton = new Button("Compute a × (b^x)");
    calcButton.setAccessibleText("Click or press Enter to calculate a times b raised to x");
    grid.add(calcButton, 1, 3);
    calcButton.setDefaultButton(true);

    resultLabel.setStyle("-fx-font-weight: bold;");
    grid.add(resultLabel, 1, 4);

    errorLabel.setStyle("-fx-text-fill: red;");
    grid.add(errorLabel, 1, 5);

    calcButton.setOnAction(e -> computeAndShow());

    Scene scene = new Scene(grid, 420, 280);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void computeAndShow() {
    errorLabel.setText("");
    try {
      double a = parseInput(afield.getText(), "a");
      double b = parseInput(bfield.getText(), "b");
      double x = parseInput(xfield.getText(), "x");
      if (b <= 0) {
        throw new IllegalArgumentException("Base (b) must be > 0.");
      }
      double result = a * Math.pow(b, x);
      resultLabel.setText("Result: " + result);
    } catch (Exception ex) {
      resultLabel.setText("Result: ");
      errorLabel.setText("Error: " + ex.getMessage());
    }
  }

  private double parseInput(String input, String varName) {
    try {
      return Double.parseDouble(input.trim());
    } catch (NumberFormatException nfe) {
      throw new IllegalArgumentException("Invalid input for " + varName + ".");
    }
  }
  
  /**
   * The entry point for the JavaFX application.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    launch(args);
  }
}


