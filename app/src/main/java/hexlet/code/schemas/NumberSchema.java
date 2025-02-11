package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {
    private boolean positive = false;
    private Integer rangeMin;
    private Integer rangeMax;

    public boolean isValid(Integer data) {
        if (required && data == null) {
            return false;
        }
        if (!required && data == null) {
            return true;
        }
        if (positive && data < 1) {
            return false;
        }
        if (rangeMin != null && data < rangeMin) {
            return false;
        }
        return rangeMax == null || data <= rangeMax;
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
