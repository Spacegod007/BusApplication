package local.jordi.busapplication.shared.messaging.serializer.objectdeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import local.jordi.busapplication.shared.messaging.model.busschedule.CompanyRequestSchedule;

import java.io.IOException;

public class CompanyRequestScheduleDeserializer extends JsonDeserializer<CompanyRequestSchedule> {
    @Override
    public CompanyRequestSchedule deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        int busNumber = node.get(CompanyRequestSchedule.busNumberSting).asInt();

        return new CompanyRequestSchedule(busNumber);
    }
}
