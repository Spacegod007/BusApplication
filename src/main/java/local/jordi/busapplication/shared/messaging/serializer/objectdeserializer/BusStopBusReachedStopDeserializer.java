package local.jordi.busapplication.shared.messaging.serializer.objectdeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import local.jordi.busapplication.shared.messaging.model.stopreached.BusStopBusReachedStop;
import local.jordi.busapplication.shared.model.BusynessLevel;

import java.io.IOException;

public class BusStopBusReachedStopDeserializer extends JsonDeserializer<BusStopBusReachedStop> {
    @Override
    public BusStopBusReachedStop deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        int busNumber = node.get(BusStopBusReachedStop.busNumberString).asInt();
        String company = node.get(BusStopBusReachedStop.companyString).asText();
        BusynessLevel busynessLevel = BusynessLevel.valueOf(node.get(BusStopBusReachedStop.busynessLevelString).asText());

        return new BusStopBusReachedStop(busNumber, company, busynessLevel);
    }
}
