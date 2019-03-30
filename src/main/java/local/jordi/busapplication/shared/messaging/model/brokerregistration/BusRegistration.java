package local.jordi.busapplication.shared.messaging.model.brokerregistration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import local.jordi.busapplication.shared.messaging.serializer.objectdeserializer.BusRegistrationDeserializer;

@JsonDeserialize(using = BusRegistrationDeserializer.class)
public class BusRegistration {
    public static final String busNumberString = "busNumber";
    public static final String companyString = "company";
    public static final String busScheduleReplyListeningQueueString = "busScheduleReplyListeningQueue";

    @JsonProperty(busNumberString)
    private int busNumber;

    @JsonProperty(companyString)
    private String company;

    @JsonProperty(busScheduleReplyListeningQueueString)
    private String busScheduleReplyListeningQueue;

    public BusRegistration(int busNumber, String company, String busScheduleReplyListeningQueue) {
        setBusNumber(busNumber);
        setCompany(company);
        setBusScheduleReplyListeningQueue(busScheduleReplyListeningQueue);
    }

    public BusRegistration() {
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

    public String getBusScheduleReplyListeningQueue() {
        return busScheduleReplyListeningQueue;
    }

    public void setBusScheduleReplyListeningQueue(String busScheduleReplyListeningQueue) {
        this.busScheduleReplyListeningQueue = busScheduleReplyListeningQueue;
    }
}
