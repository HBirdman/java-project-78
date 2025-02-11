package hexlet.code.schemas;

import java.util.Map;
import java.util.Set;

public final class MapSchema extends BaseSchema<Map<String, String>> {
    private Integer sizeof;
    private boolean shape;
    private Set<Map.Entry<String, BaseSchema<String>>> schemasSet;

    public boolean isValid(Map<String, String> data) {
        if (required && data == null) {
            return false;
        }
        if (!required && data == null) {
            return true;
        }
        if (shape) {
            String mapKey;
            String mapValue;
            for (var schema : schemasSet) {
                if (!data.containsKey(schema.getKey()) && schema.getValue().required) {
                    return false;
                }
                mapKey = schema.getKey();
                mapValue = data.get(mapKey);
                if (!schema.getValue().isValid(mapValue)) {
                    return false;
                }
            }
        }
        return sizeof == null || data.size() == sizeof;
    }

    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeof(Integer quantity) {
        sizeof = quantity;
        return this;
    }

    public void shape(Map<String, BaseSchema<String>> schemas) {
        shape = true;
        schemasSet = schemas.entrySet();
    }
}
