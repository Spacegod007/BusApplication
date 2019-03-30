package local.jordi.busapplication.shared.messaging.model.busschedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import local.jordi.busapplication.shared.messaging.serializer.objectdeserializer.CompanyRequestScheduleDeserializer;

import java.io.Serializable;

@JsonDeserialize(using = CompanyRequestScheduleDeserializer.class)
public class CompanyRequestSchedule implements Serializable
{
    public static final String busNumberSting = "busNumber";

    @JsonProperty(busNumberSting)
    private int busNumber;

    public CompanyRequestSchedule(int busNumber) {
        setBusNumber(busNumber);
    }

    public CompanyRequestSchedule() {
    }

    public int getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(int busNumber) {
        this.busNumber = busNumber;
    }
}
