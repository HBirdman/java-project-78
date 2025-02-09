package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean required = false;

    public abstract boolean isValid(T data);
}
