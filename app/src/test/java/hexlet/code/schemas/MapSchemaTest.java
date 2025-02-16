package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class MapSchemaTest {
    private static Validator v;

    @BeforeAll
    static void setup() {
        v = new Validator();
    }

    @ParameterizedTest
    @CsvSource({
        "true, 2, key1, value1, key2, value2",
        "false, 3, key1, value1, key2, value2"
    })
    void testMapIsValid(boolean expected, int sizeof, String key1, String value1, String key2, String value2) {
        var actualSchema = v.map();

        var actual = actualSchema.required().sizeof(sizeof).isValid(Map.of(key1, value1, key2, value2));

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "true, firstName, lastName, John, Smith",
        "false, firstName, lastName, Anna, B"
    })
    void testMapIsValidShape(boolean expected, String key1, String key2, String value1, String value2) {
        var actualSchema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put(key1, v.string().required());
        schemas.put(key2, v.string().required().minLength(2));
        actualSchema.shape(schemas);

        var result = actualSchema.isValid(Map.of(key1, value1, key2, value2));

        assertEquals(expected, result);
    }

    @Test
    public void testMapIsValidShapeNull() {
        boolean expected = false;
        var actualSchema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        actualSchema.shape(schemas);
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", null);

        var result = actualSchema.isValid(human1);

        assertEquals(expected, result);
    }


    @Test
    public void testMapIsValidNullTrue() {
        boolean expected = true;
        var actualSchema = v.map();

        var result = actualSchema.sizeof(2).isValid(null);

        assertEquals(expected, result);
    }

    @Test
    public void testMapIsValidNullFalse() {
        boolean expected = false;
        var actualSchema = v.map();

        var result = actualSchema.required().sizeof(2).isValid(null);

        assertEquals(expected, result);
    }
}
