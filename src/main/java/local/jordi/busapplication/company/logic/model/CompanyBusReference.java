package local.jordi.busapplication.company.logic.model;

import java.util.Objects;

public class CompanyBusReference {
    private int busNumber;
    private String lastDestination;
    private String nextDestination;

    public CompanyBusReference(int busNumber, String lastDestination, String nextDestination) {
        setBusNumber(busNumber);
        setLastDestination(lastDestination);
        setNextDestination(nextDestination);
    }

    public int getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(int busNumber) {
        this.busNumber = busNumber;
    }

    public String getLastDestination() {
        return lastDestination;
    }

    public void setLastDestination(String lastDestination) {
        this.lastDestination = lastDestination;
    }

    public String getNextDestination() {
        return nextDestination;
    }

    public void setNextDestination(String nextDestination) {
        this.nextDestination = nextDestination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyBusReference that = (CompanyBusReference) o;
        return busNumber == that.busNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(busNumber);
    }

    @Override
    public String toString() {
        return "Bus " + busNumber + ": " + lastDestination + " ->" + nextDestination;
    }
}
