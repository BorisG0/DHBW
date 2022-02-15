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

    @org.junit.jupiter.api.Test
    void add() {
        assertEquals(4, cut.add(2, 2));
    }

    @org.junit.jupiter.api.Test
    void subtract() {
        assertEquals(0, cut.subtract(2, 2));
    }
}