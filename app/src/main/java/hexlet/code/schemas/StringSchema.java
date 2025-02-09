package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    protected String data;
    protected Integer minLength;
    protected String contains;

    public boolean isValid(String string) {
        data = string;
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

    public StringSchema contains(String string) {
        this.contains = string;
        return this;
    }
}
