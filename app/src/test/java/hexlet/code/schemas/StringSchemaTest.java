package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class StringSchemaTest {
    private static Validator v;

    @BeforeAll
    static void setup() {
        v = new Validator();
    }

    @ParameterizedTest
    @CsvSource({
        "true, pc, 3, pcGaming",
        "false, pc, 10, cGaming",
        "false, pc, 10, pcGaming"
    })
    void testStringIsValid(boolean expected, String contains, int length, String text) {
        StringSchema actualSchema = v.string();

        boolean actual = actualSchema.contains(contains).minLength(length).required().isValid(text);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testStringIsValidNullAndEmpty(String text) {
        boolean expected = true;
        StringSchema actualSchema = v.string();

        boolean actual = actualSchema.isValid(text);

        assertEquals(expected, actual);
    }
}
