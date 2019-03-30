package local.jordi.busapplication.shared.messaging.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface ISerializer <T> {

    String serialize(T t) throws JsonProcessingException;
    T deserialize(String serialized) throws IOException;

}
