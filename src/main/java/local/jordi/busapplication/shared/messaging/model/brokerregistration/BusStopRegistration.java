package local.jordi.busapplication.shared.messaging.model.brokerregistration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import local.jordi.busapplication.shared.StaticStrings;
import local.jordi.busapplication.shared.messaging.serializer.objectdeserializer.BusStopRegistrationDeserializer;

@JsonDeserialize(using = BusStopRegistrationDeserializer.class)
public class BusStopRegistration {
    public static final String stopNameString = "stopName";
    public static final String busReachedStopListeningQueueString = "busReachedStopListeningQueue";

    @JsonProperty(stopNameString)
    private String stopName;

    @JsonProperty(busReachedStopListeningQueueString)
    private String busReachedStopListeningQueue;

    public BusStopRegistration(String stopName, String busReachedStopListeningQueue) {
        setStopName(stopName);
        setBusReachedStopListeningQueue(busReachedStopListeningQueue);
    }

    public static String getStopNameString() {
        return stopNameString;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getBusReachedStopListeningQueue() {
        return busReachedStopListeningQueue;
    }

    public void setBusReachedStopListeningQueue(String busReachedStopListeningQueue) {
        this.busReachedStopListeningQueue = busReachedStopListeningQueue;
    }

    @Override
    public String toString()
    {
        return stopName;
    }
}
