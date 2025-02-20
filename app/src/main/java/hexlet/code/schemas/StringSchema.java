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
        schemas.put("minLength", (d) -> d.length() < length);
        return this;
    }

    public StringSchema contains(String letters) {
        schemas.put("contains", (d) -> !d.contains(letters));
        return this;
    }
}
