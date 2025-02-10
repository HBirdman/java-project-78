package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    protected String string;
    protected Integer minLength;
    protected String contains;

    public boolean isValid(String data) {
        string = data;
        if (required && (string == null || string.isEmpty())) {
            return false;
        }
        if (minLength != null && minLength > 0 && minLength > string.length()) {
            return false;
        }
        return contains == null || string.contains(contains);
    }

    public StringSchema required() {
        required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }

    public StringSchema contains(String letters) {
        contains = letters;
        return this;
    }
}
