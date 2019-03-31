package local.jordi.busapplication.shared.messaging.model.stopreached;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import local.jordi.busapplication.shared.messaging.serializer.objectdeserializer.BusStopBusReachedStopDeserializer;
import local.jordi.busapplication.shared.model.BusynessLevel;

@JsonDeserialize(using = BusStopBusReachedStopDeserializer.class)
public class BusStopBusReachedStop {
    public static final String busNumberString = "busNumber";
    public static final String companyString = "company";
    public static final String busynessLevelString = "busynessLevel";

    @JsonProperty(busNumberString)
    private int busNumber;

    @JsonProperty(companyString)
    private String company;

    @JsonIgnore
    private BusynessLevel busynessLevel;

    public BusStopBusReachedStop(int busNumber, String company, BusynessLevel busynessLevel) {
        setBusNumber(busNumber);
        setCompany(company);
        setBusynessLevel(busynessLevel);
    }

    public BusStopBusReachedStop() {
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

    public BusynessLevel getBusynessLevel() {
        return busynessLevel;
    }

    public void setBusynessLevel(BusynessLevel busynessLevel) {
        this.busynessLevel = busynessLevel;
    }

    @JsonProperty(busynessLevelString)
    public String getBusynessLevelString()
    {
        return busynessLevel.toString();
    }

    @Override
    public String toString() {
        return company + " " + busNumber + ", busyness: " + busynessLevel;
    }
}
