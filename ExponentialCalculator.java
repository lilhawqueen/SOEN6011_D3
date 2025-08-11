package calculator;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ExponentialCalculator extends Application {
    private TextField aField = new TextField();
    private TextField bField = new TextField();
    private TextField xField = new TextField();
    private Label resultLabel = new Label("Result: ");
    private Label errorLabel = new Label("");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("a × (b^x) Calculator");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(12));
        grid.setHgap(8);
        grid.setVgap(8);

        grid.add(new Label("a:"), 0, 0);
        grid.add(aField, 1, 0);
        grid.add(new Label("b (>0):"), 0, 1);
        grid.add(bField, 1, 1);
        grid.add(new Label("x:"), 0, 2);
        grid.add(xField, 1, 2);

        Button calcButton = new Button("Compute a × (b^x)");
        grid.add(calcButton, 1, 3);

        grid.add(resultLabel, 1, 4);
        grid.add(errorLabel, 1, 5);

        calcButton.setOnAction(e -> computeAndShow());

        Scene scene = new Scene(grid, 350, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void computeAndShow() {
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setText("");
        try {
            double a = parseInput(aField.getText(), "a");
            double b = parseInput(bField.getText(), "b");
            double x = parseInput(xField.getText(), "x");
            if (b <= 0) throw new IllegalArgumentException("b must be > 0.");

            double logb = ln(b);
            double exponent = x * logb;
            double pow = exp(exponent);
            double result = a * pow;
            int intResult = (int)result;

            resultLabel.setText("Result: " + result);
        } catch (Exception ex) {
            resultLabel.setText("Result: ");
            errorLabel.setText("Error: " + ex.getMessage());
        }
    }

    private double parseInput(String input, String varName) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Invalid input for " + varName + ".");
        }
    }

    // Natural logarithm using Mercator series
    private double ln(double z) {
        if (z <= 0) throw new IllegalArgumentException("Cannot compute ln for b ≤ 0.");
        double x = (z - 1) / (z + 1);
        double x2 = x * x;
        double sum = 0.0;
        double denom = 1.0;
        double term = x;
        for (int n = 0; n < 70; n++) {
            sum += term / denom;
            term *= x2;
            denom += 2.0;
        }
        return 2 * sum;
    }

    // Exponential function using Taylor series
    private double exp(double y) {
        double sum = 1.0;
        double term = 1.0;
        for (int n = 1; n < 30; n++) {
            term *= y / n;
            sum += term;
            if (Math.abs(term) < 1e-15) break;
        }
        return sum;
    }

    public static void main(String[] args) {
        launch(args);
    }
}


