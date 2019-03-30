package local.jordi.busapplication.shared.messaging.serializer.objectdeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import local.jordi.busapplication.shared.messaging.model.busschedule.BusRequestSchedule;

import java.io.IOException;

public class BusRequestScheduleDeserializer extends JsonDeserializer<BusRequestSchedule> {
    @Override
    public BusRequestSchedule deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        int busNumber = node.get(BusRequestSchedule.busNumberString).asInt();
        String company = node.get(BusRequestSchedule.companyString).asText();

        return new BusRequestSchedule(busNumber, company);
    }
}
