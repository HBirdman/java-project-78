package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    protected Integer number;
    protected boolean positive = false;
    protected Integer rangeMin;
    protected Integer rangeMax;

    public boolean isValid(Integer data) {
        number = data;
        if (required && number == null) {
            return false;
        }
        if (!required && number == null) {
            return true;
        }
        if (positive && number < 0) {
            return false;
        }
        if (rangeMin != null && number < rangeMin) {
            return false;
        }
        return rangeMax == null || number <= rangeMax;
    }

    public NumberSchema required() {
        required = true;
        return this;
    }

    public NumberSchema positive() {
        positive = true;
        return this;
    }

    public NumberSchema range(int range1, int range2) {
        this.rangeMin = range1;
        this.rangeMax = range2;
        return this;
    }

    public NumberSchema range(int range1) {
        rangeMin = range1;
        return this;
    }
}
