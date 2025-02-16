package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public boolean isValid(String data) {
        return super.isValid(data);
    }

    @Override
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
