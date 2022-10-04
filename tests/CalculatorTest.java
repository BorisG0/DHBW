import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import other.Calculator;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator cut;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        cut = new Calculator();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void addAndSubtract(){
        assertEquals(5, cut.add(3, 2));
        assertEquals(3, cut.subtract(5, 2));
    }

    @org.junit.jupiter.api.Test
    void add() {
        assertEquals(4, cut.add(2, 2));
    }

    @org.junit.jupiter.api.Test
    void subtract() {
        assertEquals(0, cut.subtract(2, 2));
    }

    void multiply(){
        assertEquals(16, cut.multiply(4, 4));
    }

    @ParameterizedTest(name = "{0} plus {1} equals {2}")
    @CsvSource({
            "-14124, 200, -13924",
            "10, 15, 25"
    })
    void addParameterized(double a, double b, double exp){
        double act = cut.add(a, b);
        assertEquals(act, exp);
    }
}