package local.jordi.busapplication.shared.messaging.serializer.objectdeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import local.jordi.busapplication.shared.messaging.model.brokerregistration.CompanyRegistration;

import java.io.IOException;

public class CompanyRegistrationDeserializer extends JsonDeserializer<CompanyRegistration> {

    @Override
    public CompanyRegistration deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String companyName = node.get(CompanyRegistration.companyNameString).asText();
        String companyScheduleRequestListeningQueue = node.get(CompanyRegistration.companyScheduleRequestListeningQueueString).asText();
        String companyBusReachedStopListeningQueue = node.get(CompanyRegistration.companyBusReachedStopListeningQueueString).asText();

        return new CompanyRegistration(companyName, companyScheduleRequestListeningQueue, companyBusReachedStopListeningQueue);
    }
}
