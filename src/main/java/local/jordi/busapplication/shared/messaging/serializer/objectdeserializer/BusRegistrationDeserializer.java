package local.jordi.busapplication.shared.messaging.serializer.objectdeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import local.jordi.busapplication.shared.messaging.model.brokerregistration.BusRegistration;

import java.io.IOException;

public class BusRegistrationDeserializer extends JsonDeserializer<BusRegistration> {
    @Override
    public BusRegistration deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        int busNumber = node.get(BusRegistration.busNumberString).asInt();
        String company = node.get(BusRegistration.companyString).asText();
        String busScheduleReplyListeningQueue = node.get(BusRegistration.busScheduleReplyListeningQueueString).asText();

        return new BusRegistration(busNumber, company, busScheduleReplyListeningQueue);
    }
}
