package local.jordi.busapplication.shared.messaging.serializer.objectdeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import local.jordi.busapplication.shared.messaging.model.stopreached.BusReachedStop;
import local.jordi.busapplication.shared.model.BusynessLevel;

import java.io.IOException;

public class BusReachedStopDeserializer extends JsonDeserializer<BusReachedStop> {
    @Override
    public BusReachedStop deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        int busNumber = node.get(BusReachedStop.busNumberString).asInt();
        String company = node.get(BusReachedStop.companyString).asText();
        String nextStop = node.get(BusReachedStop.nextStopString).asText();
        String reachedStop = node.get(BusReachedStop.reachedStopString).asText();
        BusynessLevel busynessLevel = BusynessLevel.valueOf(node.get(BusReachedStop.busynessLevelString).asText());

        return new BusReachedStop(busNumber, company, reachedStop, nextStop, busynessLevel);
    }
}
