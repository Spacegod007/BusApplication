package local.jordi.busapplication.shared.messaging.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericListSerializer<T> implements ISerializer<List<T>> {

    private Class<List<T>> entityclass;
    private ObjectMapper objectMapper;

    public GenericListSerializer() {
        objectMapper = new ObjectMapper();

        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        entityclass = (Class<List<T>>) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public String serialize(List<T> tList) throws JsonProcessingException {
        return objectMapper.writeValueAsString(tList);
    }

    @Override
    public List<T> deserialize(String serialized) throws IOException {
        return objectMapper.readValue(serialized, objectMapper.getTypeFactory().constructCollectionType(List.class, entityclass));
    }
}
