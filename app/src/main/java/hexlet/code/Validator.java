package hexlet.code;

import hexlet.code.schemas.StringSchema;

public class Validator {
    private StringSchema stringSchema;

    public StringSchema string() {
        return stringSchema = new StringSchema();
    }
}
