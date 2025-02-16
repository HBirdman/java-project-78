package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BaseSchema<T> {
    protected Map<String, Object> schema = new HashMap<>();
    protected Set<Map.Entry<String, BaseSchema<String>>> nestedSchemas;

    /**
     * This method checks whether the provided data matches the selected data conditions.
     * @param data - can be String, Integer, int or Map type
     * @return boolean
     */
    public boolean isValid(T data) {
        if (schema.containsKey("required") && (data == null || data.equals(""))) {
            return false;
        }
        if (!schema.containsKey("required") && data == null) {
            return true;
        }
        if (data instanceof Integer) {
            if (schema.containsKey("positive") && (int) data < 1) {
                return false;
            }
            if (schema.containsKey("rangeMin") && (int) data < (int) schema.get("rangeMin")) {
                return false;
            }
            return !schema.containsKey("rangeMax") || (int) data <= (int) schema.get("rangeMax");
        }
        if (data instanceof String) {
            if (schema.containsKey("minLength") && (int) schema.get("minLength") > ((String) data).length()) {
                return false;
            }
            return schema.get("contains") == null || ((String) data).contains((String) schema.get("contains"));
        }
        if (data instanceof Map<?, ?>) {
            if (schema.containsKey("shape")) {
                for (var nestedSchema : nestedSchemas) {
                    String mapKey = nestedSchema.getKey();
                    String mapValue = (String) ((Map<?, ?>) data).get(mapKey);
                    if (!((Map<?, ?>) data).containsKey(mapKey)
                            && nestedSchema.getValue().schema.containsKey("required")) {
                        return false;
                    }
                    if (!nestedSchema.getValue().isValid(mapValue)) {
                        return false;
                    }
                }
            }
            return !schema.containsKey("sizeof") || ((Map<?, ?>) data).size() == (int) schema.get("sizeof");
        }
        return true;
    }

    /**
     * This method adds check for not Null condition for data.
     * @return BaseSchema<T></T>
     */
    public BaseSchema<T> required() {
        schema.put("required", true);
        return this;
    }
}
