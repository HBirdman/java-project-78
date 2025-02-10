package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, String>> {
    protected Map<String, String> map;
    protected Integer sizeof;

    public boolean isValid(Map<String, String> data) {
        map = data;
        if (required && map == null) {
            return false;
        }
        if (!required && map == null) {
            return true;
        }
        return sizeof == null || map.size() == sizeof;
    }

    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeof(Integer quantity) {
        sizeof = quantity;
        return this;
    }
}
