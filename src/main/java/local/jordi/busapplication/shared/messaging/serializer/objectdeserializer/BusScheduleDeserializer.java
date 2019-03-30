package local.jordi.busapplication.shared.messaging.serializer.objectdeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import local.jordi.busapplication.shared.messaging.serializer.listobject.BusScheduleItemsListSerializer;
import local.jordi.busapplication.shared.model.BusSchedule;
import local.jordi.busapplication.shared.model.BusScheduleItem;
import local.jordi.busapplication.shared.messaging.serializer.GenericListSerializer;

import java.io.IOException;
import java.util.List;

public class BusScheduleDeserializer extends StdDeserializer<BusSchedule> {

    public BusScheduleDeserializer()
    {
        this(null);
    }

    protected BusScheduleDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public BusSchedule deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        BusScheduleItemsListSerializer busScheduleItemsListSerializer = new BusScheduleItemsListSerializer();

        int busNumber = node.get(BusSchedule.busNumberString).asInt();
        String company = node.get(BusSchedule.companyString).asText();

        List<BusScheduleItem> busScheduleItems = busScheduleItemsListSerializer.deserialize(node.get(BusSchedule.scheduleItemsString).toString());

        return new BusSchedule(busNumber, company, busScheduleItems);
    }
}
