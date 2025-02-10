package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {

    @Test
    public void testStringIsValidTrue() {
        boolean expected = true;
        var actualSchema = new Validator().string();
        var result = actualSchema.contains("ps").minLength(3).required().isValid("psgaming");
        assertEquals(expected, result);
    }

    @Test
    public void testStringIsValidFalse() {
        boolean expected = false;
        var actualSchema = new Validator().string();
        var result = actualSchema.contains("ps").minLength(10).required().isValid("sgaming");
        assertEquals(expected, result);
    }

    @Test
    public void testStringIsValidNull() {
        boolean expected = true;
        var actualSchema = new Validator().string();
        var result = actualSchema.isValid(null);
        assertEquals(expected, result);
    }

    @Test
    public void testNumberIsValidTrue() {
        boolean expected = true;
        var actualSchema = new Validator().number();
        var result = actualSchema.required().positive().range(5, 10).isValid(5);
        assertEquals(expected, result);
    }

    @Test
    public void testNumberIsValidFalse() {
        boolean expected = false;
        var actualSchema = new Validator().number();
        var result = actualSchema.required().positive().range(5).isValid(0);
        assertEquals(expected, result);
    }

    @Test
    public void testNumberIsValidNull() {
        boolean expected = true;
        var actualSchema = new Validator().number();
        var result = actualSchema.positive().isValid(null);
        assertEquals(expected, result);
    }

    @Test
    public void testNumberIsValidFalse2() {
        boolean expected = false;
        var actualSchema = new Validator().number();
        var result = actualSchema.positive().isValid(-2);
        assertEquals(expected, result);
    }

    @Test
    public void testMapIsValidTrue() {
        boolean expected = true;
        var actualSchema = new Validator().map();
        var result = actualSchema.required().sizeof(2).isValid(Map.of("1", "1", "2", "2"));
        assertEquals(expected, result);
    }

    @Test
    public void testMapIsValidFalse() {
        boolean expected = false;
        var actualSchema = new Validator().map();
        var result = actualSchema.required().sizeof(2).isValid(Map.of("1", "1"));
        assertEquals(expected, result);
    }

    @Test
    public void testMapIsValidNullTrue() {
        boolean expected = true;
        var actualSchema = new Validator().map();
        var result = actualSchema.sizeof(2).isValid(null);
        assertEquals(expected, result);
    }

    @Test
    public void testMapIsValidNullFalse() {
        boolean expected = false;
        var actualSchema = new Validator().map();
        var result = actualSchema.required().sizeof(2).isValid(null);
        assertEquals(expected, result);
    }

    @Test
    public void testMapIsValidShapeTrue() {
        boolean expected = true;
        var v = new Validator();
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
        var v = new Validator();
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
        var v = new Validator();
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
