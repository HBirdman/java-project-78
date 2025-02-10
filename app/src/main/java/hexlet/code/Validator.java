package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {
    private StringSchema stringSchema;
    private NumberSchema numberSchema;
    private MapSchema mapSchema;

    public StringSchema string() {
        stringSchema = new StringSchema();
        return stringSchema;
    }

    public NumberSchema number() {
        numberSchema = new NumberSchema();
        return numberSchema;
    }

    public MapSchema map() {
        mapSchema = new MapSchema();
        return mapSchema;
    }
}
