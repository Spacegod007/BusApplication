package local.jordi.busapplication.shared.messaging.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

public class GenericSerializer <T> implements ISerializer<T> {

    private Class<T> entityclass;
    private ObjectMapper objectMapper;

    public GenericSerializer()
    {
        objectMapper = new ObjectMapper();

        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        entityclass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public String serialize(T t) throws JsonProcessingException {
        return objectMapper.writeValueAsString(t);
    }

    @Override
    public T deserialize(String serialized) throws IOException {
        return objectMapper.readValue(serialized, entityclass);
    }
}
