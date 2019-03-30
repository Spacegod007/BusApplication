package local.jordi.busapplication.company.logic.model;

import local.jordi.busapplication.shared.model.BusSchedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company {
    private String companyName;

    private Map<Integer, BusSchedule> busScheduleByBusNumber;

    public Company(String companyName) {
        setCompanyName(companyName);

        busScheduleByBusNumber = new HashMap<>();

        prepareBusSchedules();
    }

    private void prepareBusSchedules() {
        List<BusSchedule> busSchedules = new MockBusSchedules().getBusSchedules(companyName);
        for (BusSchedule busSchedule : busSchedules) {
            busScheduleByBusNumber.put(busSchedule.getBusNumber(), busSchedule);
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BusSchedule getBusSchedule(int busNumber)
    {
        return busScheduleByBusNumber.get(busNumber);
    }
}
