package hexlet.code.schemas;

import java.util.Map;

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
        schema.put("sizeof", quantity);
        return this;
    }

    public void shape(Map<String, BaseSchema<String>> schemas) {
        schema.put("shape", true);
        nestedSchemas = schemas.entrySet();
    }
}
