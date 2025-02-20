package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class NumberSchemaTest {
    private static Validator v;

    @BeforeAll
    static void setup() {
        v = new Validator();
    }

    @ParameterizedTest
    @CsvSource({
        "true, 5, 10, 10",
        "false, 5, 10, -2",
        "false, 3, 6, 1"
    })
    void testNumberIsValid(boolean expected, int range1, int range2, int number) {
        NumberSchema actualSchema = v.number();

        boolean actual = actualSchema.required().positive().range(range1, range2).isValid(number);

        assertEquals(expected, actual);
    }

    @Test
    public void testNumberIsValidNull() {
        boolean expected = true;
        NumberSchema actualSchema = v.number();

        boolean result = actualSchema.positive().isValid(null);

        assertEquals(expected, result);
    }
}
