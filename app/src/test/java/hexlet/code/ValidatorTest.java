package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class ValidatorTest {
    private static Validator v;

    @BeforeAll
    static void setup() {
        v = new Validator();
    }


    @ParameterizedTest
    @CsvSource({
        "true, pc, 3, pcGaming",
        "false, pc, 10, cGaming"
    })
    void testStringIsValid(boolean expected, String contains, int length, String text) {
        var actualSchema = v.string();
        var actual = actualSchema.contains(contains).minLength(length).required().isValid(text);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testStringIsValidNullAndEmpty(String text) {
        boolean expected = true;
        var actualSchema = v.string();
        var actual = actualSchema.isValid(text);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "true, 5, 10, 10",
        "false, 5, 0, -2"
    })
    void testNumberIsValid(boolean expected, int range1, int range2, int number) {
        var actualSchema = v.number();
        var actual = actualSchema.required().positive().range(range1, range2).isValid(number);
        assertEquals(expected, actual);
    }

    @Test
    public void testNumberIsValidNull() {
        boolean expected = true;
        var actualSchema = v.number();
        var result = actualSchema.positive().isValid(null);
        assertEquals(expected, result);
    }

    @Test
    public void testMapIsValidTrue() {
        boolean expected = true;
        var actualSchema = v.map();
        var result = actualSchema.required().sizeof(2).isValid(Map.of("1", "1", "2", "2"));
        assertEquals(expected, result);
    }

    @Test
    public void testMapIsValidFalse() {
        boolean expected = false;
        var actualSchema = v.map();
        var result = actualSchema.required().sizeof(2).isValid(Map.of("1", "1"));
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

    @Test
    public void testMapIsValidShapeTrue() {
        boolean expected = true;
        var actualSchema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        actualSchema.shape(schemas);
        var result = actualSchema.isValid(Map.of("firstName", "John", "lastName", "Smith"));
        assertEquals(expected, result);
    }

    @Test
    public void testMapIsValidShapeFalse() {
        boolean expected = false;
        var actualSchema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        actualSchema.shape(schemas);
        var result = actualSchema.isValid(Map.of("firstName", "Anna", "lastName", "B"));
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
}
