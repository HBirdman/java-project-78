package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {

    @Test
    public void testIsValidFalse() {
        boolean expected = false;
        var actualSchema = new Validator().string();
        var result = actualSchema.contains("ps").minLength(10).required().isValid("sgaming");
        assertEquals(expected, result);
    }

    @Test
    public void testIsValidTrue() {
        boolean expected = true;
        var actualSchema = new Validator().string();
        var result = actualSchema.contains("ps").minLength(3).required().isValid("psgaming");
        assertEquals(expected, result);
    }

    @Test
    public void testIsValidNull() {
        boolean expected = true;
        var actualSchema = new Validator().string();
        var result = actualSchema.isValid(null);
        assertEquals(expected, result);
    }
}
