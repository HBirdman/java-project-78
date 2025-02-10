package hexlet.code.schemas;

import java.util.Map;
import java.util.Set;

public class MapSchema extends BaseSchema<Map<String, String>> {
    protected Map<String, String> map;
    protected Integer sizeof;
    protected boolean shape;
    protected Set<Map.Entry<String, BaseSchema<String>>> schemasSet;

    public boolean isValid(Map<String, String> data) {
        map = data;
        if (required && map == null) {
            return false;
        }
        if (!required && map == null) {
            return true;
        }
        if (shape) {
            String mapKey;
            String mapValue;
            for (var schema : schemasSet) {
                if (!map.containsKey(schema.getKey()) && schema.getValue().required) {
                    return false;
                }
                mapKey = schema.getKey();
                mapValue = map.get(mapKey);
                if (!schema.getValue().isValid(mapValue)) {
                    return false;
                }
            }
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

    public void shape(Map<String, BaseSchema<String>> schemas) {
        shape = true;
        schemasSet = schemas.entrySet();
    }
}
