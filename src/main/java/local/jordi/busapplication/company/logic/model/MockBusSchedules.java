package local.jordi.busapplication.company.logic.model;

import local.jordi.busapplication.shared.StaticStrings;
import local.jordi.busapplication.shared.model.BusSchedule;
import local.jordi.busapplication.shared.model.BusScheduleItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBusSchedules {

    public List<BusSchedule> getBusSchedules(String companyname)
    {
        List<BusSchedule> busSchedules = new ArrayList<>();

        for (int i = 1; i < 10; i++)
        {
            busSchedules.add(new BusSchedule(i, companyname,
                    new BusScheduleItem(StaticStrings.BUSSTATION, new Date()),
                    new BusScheduleItem(StaticStrings.BUSSTOP_A, new Date()),
                    new BusScheduleItem(StaticStrings.BUSSTOP_B, new Date()),
                    new BusScheduleItem(StaticStrings.BUSSTOP_C, new Date()),
                    new BusScheduleItem(StaticStrings.BUSSTOP_D, new Date()),
                    new BusScheduleItem(StaticStrings.BUSSTATION, new Date())
            ));
        }

        return busSchedules;
    }
}
