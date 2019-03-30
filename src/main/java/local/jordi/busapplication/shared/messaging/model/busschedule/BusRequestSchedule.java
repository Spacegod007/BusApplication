package local.jordi.busapplication.shared.messaging.model.busschedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import local.jordi.busapplication.shared.messaging.serializer.objectdeserializer.BusRequestScheduleDeserializer;

import java.io.Serializable;

@JsonDeserialize(using = BusRequestScheduleDeserializer.class)
public class BusRequestSchedule implements Serializable
{
    public static final String busNumberString = "busNumber";
    public static final String companyString = "company";

    @JsonProperty(busNumberString)
    private int busNumber;

    @JsonProperty(companyString)
    private String company;

    public BusRequestSchedule(int busNumber, String company) {
        setBusNumber(busNumber);
        setCompany(company);
    }

    public BusRequestSchedule() {
    }

    public int getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(int busNumber) {
        this.busNumber = busNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return company + " " + busNumber;
    }
}
