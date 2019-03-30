package local.jordi.busapplication.shared.messaging.model.busschedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import local.jordi.busapplication.shared.messaging.serializer.objectdeserializer.CompanyReplyScheduleDeserializer;
import local.jordi.busapplication.shared.model.BusSchedule;

import java.io.Serializable;

@JsonDeserialize(using = CompanyReplyScheduleDeserializer.class)
public class CompanyReplySchedule implements Serializable {
    public static final String companyString = "company";
    public static final String busNumberString = "busNumber";
    public static final String busScheduleString = "busSchedule";

    @JsonProperty(companyString)
    private String company;

    @JsonProperty(busNumberString)
    private int busNumber;

    @JsonProperty(busScheduleString)
    private BusSchedule busSchedule;

    public CompanyReplySchedule(String company, int busNumber, BusSchedule busSchedule) {
        setCompany(company);
        setBusNumber(busNumber);
        setBusSchedule(busSchedule);
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(int busNumber) {
        this.busNumber = busNumber;
    }

    public BusSchedule getBusSchedule() {
        return busSchedule;
    }

    public void setBusSchedule(BusSchedule busSchedule) {
        this.busSchedule = busSchedule;
    }

    @Override
    public String toString()
    {
        return String.format("%s %d %s", company, busNumber, busSchedule);
    }
}
