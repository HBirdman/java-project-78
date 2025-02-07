package hexlet.code.schemas;

import hexlet.code.Validator;

public class StringSchema extends Validator {
    protected String data;
    protected boolean required = false;
    protected int minLength;
    protected String contains;
    protected boolean valid = true;

    public boolean isValid(String data) {
        this.data = data;
        if (this.required) {
            this.valid = this.data != null && !this.data.isEmpty();
        }
        if (this.minLength > 0) {
            assert this.data != null;
            this.valid = this.minLength <= this.data.length();
        }
        if (this.contains != null) {
            assert this.data != null;
            this.valid = this.data.contains(this.contains);
        }
        return this.valid;
    }

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int minLength) {
        this.minLength = minLength;
        return this;
    }

    public StringSchema contains(String contains) {
        this.contains = contains;
        return this;
    }
}
