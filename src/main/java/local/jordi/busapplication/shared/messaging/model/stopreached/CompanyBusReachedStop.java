package local.jordi.busapplication.shared.messaging.model.stopreached;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import local.jordi.busapplication.shared.messaging.serializer.objectdeserializer.CompanyBusReachedStopDeserializer;

@JsonDeserialize(using = CompanyBusReachedStopDeserializer.class)
public class CompanyBusReachedStop {
    public static final String busNumberString = "busNumber";
    public static final String reachedStopString = "reachedStop";
    public static final String nextStopString = "nextStop";

    @JsonProperty(busNumberString)
    private int busNumber;

    @JsonProperty(reachedStopString)
    private String reachedStop;

    @JsonProperty(nextStopString)
    private String nextStop;

    public CompanyBusReachedStop(int busNumber, String reachedStop, String nextStop) {
        setBusNumber(busNumber);
        setReachedStop(reachedStop);
        setNextStop(nextStop);
    }

    public CompanyBusReachedStop() {
    }

    public int getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(int busNumber) {
        this.busNumber = busNumber;
    }

    public String getReachedStop() {
        return reachedStop;
    }

    public void setReachedStop(String reachedStop) {
        this.reachedStop = reachedStop;
    }

    public String getNextStop() {
        return nextStop;
    }

    public void setNextStop(String nextStop) {
        this.nextStop = nextStop;
    }

    @Override
    public String toString()
    {
        return busNumber + ", reached: " + reachedStop + ", next: " + nextStop;
    }
}
