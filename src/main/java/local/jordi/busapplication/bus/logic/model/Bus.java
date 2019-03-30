package local.jordi.busapplication.bus.logic.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import local.jordi.busapplication.shared.model.BusSchedule;
import local.jordi.busapplication.shared.model.BusScheduleItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Bus
{
    private String company;
    private int number;
    private int passengers;
    private int capacity;

    private ObservableList<BusScheduleItem> scheduleItems;

    public Bus(String company, int number, int capacity) {
        setCompany(company);
        setNumber(number);
        setCapacity(capacity);

        passengers = 0;
    }

    public ObservableList<BusScheduleItem> getBusSchedule() {
        return scheduleItems;
    }

    public void setBusSchedule(BusSchedule busSchedule)
    {
        scheduleItems = FXCollections.observableArrayList(busSchedule.getScheduleItems());
    }

    public String busStopReached()
    {
        int passengersToRemove = ThreadLocalRandom.current().nextInt(0, passengers);
        removePassengers(passengersToRemove);

        int addPassengers = ThreadLocalRandom.current().nextInt(0, capacity);
        if ((addPassengers + passengers) > capacity)
        {
            passengers = capacity;
        }

        return pollFromItems().getBusStop();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPassengers() {
        return passengers;
    }

    public int getCapacity() {
        return capacity;
    }

    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void addPassagers(int passengers)
    {
        this.passengers += passengers;
    }

    public void removePassengers(int passengers)
    {
        this.passengers -= passengers;
    }

    private BusScheduleItem pollFromItems()
    {
        BusScheduleItem busScheduleItem = scheduleItems.get(0);

        List<BusScheduleItem> items = new ArrayList<>();
        for (int i = 1; i < scheduleItems.size(); i++) {
            items.add(scheduleItems.get(i));
        }
        scheduleItems.setAll(items);

        return busScheduleItem;
    }
}
