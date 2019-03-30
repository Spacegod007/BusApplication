package local.jordi.busapplication.shared.messaging.serializer.objectdeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import local.jordi.busapplication.shared.messaging.model.stopreached.CompanyBusReachedStop;

import java.io.IOException;

public class CompanyBusReachedStopDeserializer extends JsonDeserializer<CompanyBusReachedStop> {
    @Override
    public CompanyBusReachedStop deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        int busNumber = node.get(CompanyBusReachedStop.busNumberString).asInt();
        String reachedStop = node.get(CompanyBusReachedStop.reachedStopString).asText();
        String nextStop = node.get(CompanyBusReachedStop.nextStopString).asText();

        return new CompanyBusReachedStop(busNumber, reachedStop, nextStop);
    }
}
