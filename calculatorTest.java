package calculator;

import org.junit.jupiter.api.Test;

public class calculatorTest {

    // ---- Tests for ln() ----
    @Test
    void testLnOfOneIsZero() {
        assertEquals(0.0, MathFunctions.ln(1.0), 1e-12);
    }

    @Test
    void testLnOfEIsOne() {
        assertEquals(1.0, MathFunctions.ln(Math.E), 1e-12);
    }

    @Test
    void testLnPositiveNumber() {
        double val = 10.0;
        assertEquals(Math.log(val), MathFunctions.ln(val), 1e-12);
    }

    @Test
    void testLnOfZeroThrows() {
        assertThrows(IllegalArgumentException.class, () -> MathFunctions.ln(0.0));
    }

    @Test
    void testLnOfNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> MathFunctions.ln(-5.0));
    }

    // ---- Tests for exp() ----
    @Test
    void testExpOfZeroIsOne() {
        assertEquals(1.0, MathFunctions.exp(0.0), 1e-12);
    }

    @Test
    void testExpMatchesMathExp() {
        double val = 3.5;
        assertEquals(Math.exp(val), MathFunctions.exp(val), 1e-12);
    }

    @Test
    void testExpNegativeValue() {
        double val = -2.0;
        assertEquals(Math.exp(val), MathFunctions.exp(val), 1e-12);
    }

    // ---- Tests for parseInput() in ExponentialCalculator ----
    @Test
    void testParseInputValidInteger() throws Exception {
        ExponentialCalculator calc = new ExponentialCalculator();
        double result = invokeParse(calc, "42", "testVar");
        assertEquals(42.0, result);
    }

    @Test
    void testParseInputValidDecimal() throws Exception {
        ExponentialCalculator calc = new ExponentialCalculator();
        double result = invokeParse(calc, "3.1415", "pi");
        assertEquals(3.1415, result);
    }

    @Test
    void testParseInputEmptyString() throws Exception {
        ExponentialCalculator calc = new ExponentialCalculator();
        double result = invokeParse(calc, "", "multiplier");
        assertTrue(Double.isNaN(result));
    }

    @Test
    void testParseInputNullString() throws Exception {
        ExponentialCalculator calc = new ExponentialCalculator();
        double result = invokeParse(calc, null, "multiplier");
        assertTrue(Double.isNaN(result));
    }

    @Test
    void testParseInputInvalidFormat() throws Exception {
        ExponentialCalculator calc = new ExponentialCalculator();
        double result = invokeParse(calc, "abc", "multiplier");
        assertTrue(Double.isNaN(result));
    }

    // Helper to call private parseInput() via reflection
    private double invokeParse(ExponentialCalculator calc, String input, String name) throws Exception {
        var method = ExponentialCalculator.class.getDeclaredMethod("parseInput", String.class, String.class);
        method.setAccessible(true);
        return (double) method.invoke(calc, input, name);
    }
}

//}
