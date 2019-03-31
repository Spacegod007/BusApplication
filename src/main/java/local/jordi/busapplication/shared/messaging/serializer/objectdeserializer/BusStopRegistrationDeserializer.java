package local.jordi.busapplication.shared.messaging.serializer.objectdeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import local.jordi.busapplication.shared.messaging.model.brokerregistration.BusStopRegistration;

import java.io.IOException;

public class BusStopRegistrationDeserializer extends JsonDeserializer<BusStopRegistration> {
    @Override
    public BusStopRegistration deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String stopName = node.get(BusStopRegistration.getStopNameString()).asText();
        String buReachedStopListeningQueue = node.get(BusStopRegistration.busReachedStopListeningQueueString).asText();

        return new BusStopRegistration(stopName, buReachedStopListeningQueue);
    }
}
