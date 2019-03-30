package local.jordi.busapplication.broker.logic.model;

import java.util.Objects;

public class BusReference
{
    private int busNumber;
    private String busCompany;

    public BusReference(int busNumber, String busCompany) {
        setBusNumber(busNumber);
        setBusCompany(busCompany);
    }

    public int getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(int busNumber) {
        this.busNumber = busNumber;
    }

    public String getBusCompany() {
        return busCompany;
    }

    public void setBusCompany(String busCompany) {
        this.busCompany = busCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusReference that = (BusReference) o;
        return busNumber == that.busNumber &&
                Objects.equals(busCompany, that.busCompany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(busNumber, busCompany);
    }

    @Override
    public String toString()
    {
        return busCompany + " " + busNumber;
    }
}
