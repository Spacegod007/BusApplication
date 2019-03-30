package local.jordi.busapplication.shared.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import local.jordi.busapplication.shared.messaging.serializer.objectdeserializer.BusScheduleDeserializer;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@JsonDeserialize(using = BusScheduleDeserializer.class)
public class BusSchedule implements Serializable
{
    public static final String busNumberString = "busName";
    public static final String companyString = "company";
    public static final String scheduleItemsString = "scheduleItems";

    @JsonProperty(busNumberString)
    private int busNumber;

    @JsonProperty(companyString)
    private String company;

    @JsonProperty(scheduleItemsString)
    private List<BusScheduleItem> scheduleItems;

    public BusSchedule(int busNumber, String company, BusScheduleItem... scheduleItems)
    {
        this(busNumber, company, Arrays.asList(scheduleItems));
    }

    public BusSchedule(int busNumber, String company, List<BusScheduleItem> scheduleItems) {
        this.busNumber = busNumber;
        this.company = company;
        this.scheduleItems = scheduleItems;
    }

    public int getBusNumber() {
        return busNumber;
    }

    public String getCompany() {
        return company;
    }

    public List<BusScheduleItem> getScheduleItems() {
        return scheduleItems;
    }

    public BusScheduleItem getItemAfterDestination(String lastDestination)
    {
        for (int i = 0; i < scheduleItems.size(); i++) {
            BusScheduleItem scheduleItem = scheduleItems.get(i);
            if (scheduleItem.getBusStop().equals(lastDestination)) {
                return (i + 1) < scheduleItems.size()
                        ? scheduleItems.get(i + 1)
                        : null;
            }
        }

        return null;
    }

    @Override
    public String toString()
    {
        return "BusSchedule with " + scheduleItems.size() + " items";
    }
}
