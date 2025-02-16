package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    @Override
    public boolean isValid(Integer data) {
        return super.isValid(data);
    }

    @Override
    public NumberSchema required() {
        return (NumberSchema) super.required();
    }

    public NumberSchema positive() {
        schema.put("positive", true);
        return this;
    }

    public NumberSchema range(int rangeMin, int rangeMax) {
        schema.put("rangeMin", rangeMin);
        schema.put("rangeMax", rangeMax);
        return this;
    }

    public NumberSchema range(int rangeMin) {
        schema.put("rangeMin", rangeMin);
        return this;
    }
}
