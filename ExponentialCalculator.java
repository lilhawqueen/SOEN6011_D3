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
 * ExponentialCalculator.
 * <p>
 * Computes multiplier × (base^exponent) with validation, accessibility,
 * and JavaFX GUI best practices. Designed to pass PMD (zero violations) and
 * Checkstyle checks (no style errors).
 * </p>
 */
public class ExponentialCalculator extends Application {

    /** Application version. */
    public static final String VERSION = "1.0.0";
    /** Label prefix for result. */
    private static final String RESULT_PREFIX = "Result: ";
    /** Label prefix for error messages. */
    private static final String ERROR_PREFIX = "Error: ";

    /** Input field for multiplier. */
    private final TextField multiplierField = new TextField();
    /** Input field for base value. */
    private final TextField baseField = new TextField();
    /** Input field for exponent. */
    private final TextField exponentField = new TextField();
    /** Label to show calculation results. */
    private final Label resultLabel = new Label(RESULT_PREFIX);
    /** Label to show error messages. */
    private final Label errorLabel = new Label();

    /**
     * JavaFX entry point — sets up the user interface.
     *
     * @param primaryStage the main application stage
     */
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Multiplier × (Base^Exponent) Calculator v" + VERSION);

        final GridPane grid = new GridPane();
        grid.setPadding(new Insets(16));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Multiplier:"), 0, 0);
        multiplierField.setPromptText("e.g., 2");
        multiplierField.setAccessibleText("Input for multiplier");
        grid.add(multiplierField, 1, 0);

        grid.add(new Label("Base (> 0):"), 0, 1);
        baseField.setPromptText("e.g., 3");
        baseField.setAccessibleText("Input for base, must be greater than zero");
        grid.add(baseField, 1, 1);

        grid.add(new Label("Exponent:"), 0, 2);
        exponentField.setPromptText("e.g., 4");
        exponentField.setAccessibleText("Input for exponent");
        grid.add(exponentField, 1, 2);

        final Button calcButton = new Button("Compute");
        calcButton.setAccessibleText(
            "Click or press Enter to calculate multiplier × base^exponent");
        grid.add(calcButton, 1, 3);
        calcButton.setDefaultButton(true);

        resultLabel.setStyle("-fx-font-weight: bold;");
        grid.add(resultLabel, 1, 4);

        errorLabel.setStyle("-fx-text-fill: red;");
        grid.add(errorLabel, 1, 5);

        calcButton.setOnAction(event -> computeAndShow());

        final Scene scene = new Scene(grid, 420, 280);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Performs the computation of multiplier × (base^exponent) and updates the UI.
     */
    private void computeAndShow() {
        errorLabel.setText("");
        resultLabel.setText(RESULT_PREFIX);
        validateAndCompute();
    }

    /**
     * Parses and validates input, computes result if valid.
     * Returns true if computation succeeded, false if validation failed.
     *
     * @return true if valid and computed, else false
     */
    private boolean validateAndCompute() {
        final boolean success;

        final double multiplier = parseInput(multiplierField.getText(), "multiplier");
        final double baseValue = parseInput(baseField.getText(), "base");
        final double exponent = parseInput(exponentField.getText(), "exponent");

        if (Double.isNaN(multiplier) || Double.isNaN(baseValue) || Double.isNaN(exponent)) {
            success = false;
        } else if (baseValue <= 0) {
            errorLabel.setText(ERROR_PREFIX + "Base must be greater than zero.");
            success = false;
        } else {
            final double result = multiplier * Math.pow(baseValue, exponent);
            resultLabel.setText(RESULT_PREFIX + result);
            success = true;
        }
        return success;
    }

    /**
     * Parses numeric input. Returns NaN if invalid.
     * Avoids exceptions as flow control.
     *
     * @param input   the user-provided string
     * @param varName the variable description for error messages
     * @return the parsed double or NaN if invalid
     */
    private double parseInput(final String input, final String varName) {
        final double parsedValue;
        if (input == null || input.isEmpty()) {
            errorLabel.setText(ERROR_PREFIX + varName + " cannot be empty.");
            parsedValue = Double.NaN;
        } else if (!input.matches("-?\\d+(\\.\\d+)?")) {
            errorLabel.setText(ERROR_PREFIX + "Invalid numeric input for " + varName + ".");
            parsedValue = Double.NaN;
        } else {
            parsedValue = Double.parseDouble(input.trim());
        }
        return parsedValue;
    }

    /**
     * The entry point for the JavaFX application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(final String[] args) {
        launch(args);
    }
}



