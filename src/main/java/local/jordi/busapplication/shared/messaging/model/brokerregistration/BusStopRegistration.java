package local.jordi.busapplication.shared.messaging.model.brokerregistration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusStopRegistration {
    public static final String stopNameString = "stopName";

    @JsonProperty(stopNameString)
    private String stopName;

    public BusStopRegistration(String stopName) {
        this.stopName = stopName;
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

    @Override
    public String toString()
    {
        return stopName;
    }
}
