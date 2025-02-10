package hexlet.code;

import org.junit.jupiter.api.Test;

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
}
