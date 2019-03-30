package local.jordi.busapplication.shared.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import local.jordi.busapplication.shared.messaging.serializer.objectdeserializer.BusScheduleItemDeserializer;

import java.io.Serializable;
import java.util.Date;

@JsonDeserialize(using = BusScheduleItemDeserializer.class)
public class BusScheduleItem implements Serializable {
    public static final String busStopString = "busStop";
    public static final String expectedArrivalString = "expectedArrival";

    @JsonProperty(busStopString)
    private String busStop;

    @JsonProperty(expectedArrivalString)
    private Date expectedArrival;

    public BusScheduleItem(String busStop, Date expectedArrival) {
        this.busStop = busStop;
        this.expectedArrival = expectedArrival;
    }

    public BusScheduleItem() {
    }

    public String getBusStop() {
        return busStop;
    }

    public void setBusStop(String busStop) {
        this.busStop = busStop;
    }

    public Date getExpectedArrival() {
        return expectedArrival;
    }

    public void setExpectedArrival(Date expectedArrival) {
        this.expectedArrival = expectedArrival;
    }

    @Override
    public String toString() {
        return busStop + " - " + expectedArrival.getHours() + ":" + expectedArrival.getMinutes();
    }
}
