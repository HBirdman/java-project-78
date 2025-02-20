package hexlet.code.schemas;

import java.util.Map;
import java.util.Set;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    @Override
    public boolean isValid(Map<String, String> data) {
        return super.isValid(data);
    }

    @Override
    public MapSchema required() {
        return (MapSchema) super.required();
    }

    public MapSchema sizeof(Integer quantity) {
        schemas.put("sizeof", (d) -> d.size() != quantity);
        return this;
    }

    public void shape(Map<String, BaseSchema<String>> mapOfNestedSchemas) {
        Set<Map.Entry<String, BaseSchema<String>>> entrySet = mapOfNestedSchemas.entrySet();
        for (Map.Entry<String, BaseSchema<String>> entry : entrySet) {
            String key = entry.getKey();
            schemas.put(key, (d) -> !entry.getValue().isValid(d.get(key)));
        }
    }
}
