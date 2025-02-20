package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class BaseSchema<T> {
    protected Map<String, Predicate<T>> schemas = new HashMap<>();

    /**
     * This method checks whether the provided data matches the selected data conditions.
     * @param data - can be String, Integer, int or Map type
     * @return boolean
     */
    public boolean isValid(T data) {
        if (schemas.containsKey("required") && data == null) {
            return false;
        }
        if (!schemas.containsKey("required") && data == null || data.equals("")) {
            return true;
        }
        Set<Map.Entry<String, Predicate<T>>> schemasSet = schemas.entrySet();
        for (Map.Entry<String, Predicate<T>> schema : schemasSet) {
            boolean testCondition = schema.getValue().test(data);
            if (testCondition) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method adds check for not Null condition for data.
     * @return BaseSchema<T>
     */
    public BaseSchema<T> required() {
        schemas.put("required", (d) -> d == null || d.equals(""));
        return this;
    }
}
