package local.jordi.busapplication.shared.messaging.serializer.objectdeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import local.jordi.busapplication.shared.messaging.model.busschedule.CompanyReplySchedule;
import local.jordi.busapplication.shared.messaging.serializer.object.BusScheduleSerializer;
import local.jordi.busapplication.shared.model.BusSchedule;

import java.io.IOException;

public class CompanyReplyScheduleDeserializer extends JsonDeserializer<CompanyReplySchedule> {
    @Override
    public CompanyReplySchedule deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        int busNumber = node.get(CompanyReplySchedule.busNumberString).asInt();
        String company = node.get(CompanyReplySchedule.companyString).asText();
        BusSchedule busSchedule = new BusScheduleSerializer().deserialize(node.get(CompanyReplySchedule.busScheduleString).toString());

        return new CompanyReplySchedule(company, busNumber, busSchedule);
    }
}
