package local.jordi.busapplication.shared.messaging.model.brokerregistration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import local.jordi.busapplication.shared.messaging.serializer.objectdeserializer.CompanyRegistrationDeserializer;

@JsonDeserialize(using = CompanyRegistrationDeserializer.class)
public class CompanyRegistration {
    public static final String companyNameString = "companyName";
    public static final String companyScheduleRequestListeningQueueString = "companyScheduleRequestListeningQueue";
    public static final String companyBusReachedStopListeningQueueString = "companyBusReachedStopListeningQueue";

    @JsonProperty(companyNameString)
    private String companyName;

    @JsonProperty(companyScheduleRequestListeningQueueString)
    private String companyScheduleRequestListeningQueue;

    @JsonProperty(companyBusReachedStopListeningQueueString)
    private String companyBusReachedStopListeningQueue;

    public CompanyRegistration(String companyName, String companyScheduleRequestListeningQueue, String companyBusReachedStopListeningQueue) {
        setCompanyName(companyName);
        setCompanyScheduleRequestListeningQueue(companyScheduleRequestListeningQueue);
        setCompanyBusReachedStopListeningQueue(companyBusReachedStopListeningQueue);
    }

    public CompanyRegistration() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyScheduleRequestListeningQueue() {
        return companyScheduleRequestListeningQueue;
    }

    public void setCompanyScheduleRequestListeningQueue(String companyScheduleRequestListeningQueue) {
        this.companyScheduleRequestListeningQueue = companyScheduleRequestListeningQueue;
    }

    public String getCompanyBusReachedStopListeningQueue() {
        return companyBusReachedStopListeningQueue;
    }

    public void setCompanyBusReachedStopListeningQueue(String companyBusReachedStopListeningQueue) {
        this.companyBusReachedStopListeningQueue = companyBusReachedStopListeningQueue;
    }
}
