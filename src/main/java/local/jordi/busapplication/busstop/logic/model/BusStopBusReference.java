package local.jordi.busapplication.busstop.logic.model;

import local.jordi.busapplication.shared.model.BusynessLevel;

public class BusStopBusReference {
    private int busNumber;
    private String company;
    private BusynessLevel busynessLevel;

    public BusStopBusReference(int busNumber, String company, BusynessLevel busynessLevel) {
        setBusNumber(busNumber);
        setCompany(company);
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

    public BusynessLevel getBusynessLevel() {
        return busynessLevel;
    }

    public void setBusynessLevel(BusynessLevel busynessLevel) {
        this.busynessLevel = busynessLevel;
    }

    @Override
    public String toString()
    {
        return company + " " + busNumber + " Busyness: "+ busynessLevel;
    }
}
