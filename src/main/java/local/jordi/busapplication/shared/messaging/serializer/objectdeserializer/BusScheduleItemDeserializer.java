package local.jordi.busapplication.shared.messaging.serializer.objectdeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import local.jordi.busapplication.shared.model.BusScheduleItem;

import java.io.IOException;
import java.util.Date;

public class BusScheduleItemDeserializer extends JsonDeserializer {

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String busStop = node.get(BusScheduleItem.busStopString).asText();

        //todo test this
        //Date expectedArrival = new Date(node.get(BusScheduleItem.expectedArrivalString).asLong());

        Long time = Long.parseLong(node.get(BusScheduleItem.expectedArrivalString).asText());
        Date expectedArrival = new Date(time);

        return new BusScheduleItem(busStop, expectedArrival);
    }
}
