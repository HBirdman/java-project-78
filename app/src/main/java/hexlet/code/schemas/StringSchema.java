package hexlet.code.schemas;

import lombok.Getter;

@Getter
public final class StringSchema extends BaseSchema<String> {

    public boolean isValid(String data) {
        return super.isValid(data);
    }

    public StringSchema required() {
        return (StringSchema) super.required();
    }

    public StringSchema minLength(int length) {
        schema.put("minLength", length);
        return this;
    }

    public StringSchema contains(String letters) {
        schema.put("contains", letters);
        return this;
    }
}
