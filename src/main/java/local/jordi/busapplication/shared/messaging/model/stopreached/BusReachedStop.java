package local.jordi.busapplication.shared.messaging.model.stopreached;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import local.jordi.busapplication.shared.messaging.serializer.objectdeserializer.BusReachedStopDeserializer;
import local.jordi.busapplication.shared.model.BusynessLevel;

@JsonDeserialize(using = BusReachedStopDeserializer.class)
public class BusReachedStop {

    public static final String busNumberString = "busNumber";
    public static final String companyString = "company";
    public static final String reachedStopString = "reachedStop";
    public static final String nextStopString = "nextStop";
    public static final String busynessLevelString = "busynessLevelString";

    @JsonProperty(busNumberString)
    private int busNumber;

    @JsonProperty(companyString)
    private String company;

    @JsonProperty(reachedStopString)
    private String reachedStop;

    @JsonProperty(nextStopString)
    private String nextStop;

    @JsonIgnore
    private BusynessLevel busynessLevel;

    public BusReachedStop(int busNumber, String company, String reachedStop, String nextStop, BusynessLevel busynessLevel) {
        setBusNumber(busNumber);
        setCompany(company);
        setReachedStop(reachedStop);
        setNextStop(nextStop);
        setBusynessLevel(busynessLevel);
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
}
