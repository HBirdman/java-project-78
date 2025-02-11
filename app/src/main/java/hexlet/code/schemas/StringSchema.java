package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private Integer minLength;
    private String contains;

    public boolean isValid(String data) {
        if (required && (data == null || data.isEmpty())) {
            return false;
        }
        if (minLength != null && minLength > 0 && minLength > data.length()) {
            return false;
        }
        return contains == null || data.contains(contains);
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
