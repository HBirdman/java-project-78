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
        schemas.put("positive", (d) -> d < 1);
        return this;
    }

    public NumberSchema range(int rangeMin, int rangeMax) {
        schemas.put("rangeMin", (d) -> d < rangeMin);
        schemas.put("rangeMax", (d) -> d > rangeMax);
        return this;
    }

    public NumberSchema range(int rangeMin) {
        schemas.put("rangeMin", (d) -> d < rangeMin);
        return this;
    }
}
