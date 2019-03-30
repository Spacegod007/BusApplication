package local.jordi.busapplication.company.logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import local.jordi.busapplication.company.logic.message.CompanyBrokerGateway;
import local.jordi.busapplication.company.logic.model.Company;
import local.jordi.busapplication.company.logic.model.CompanyBusReference;
import local.jordi.busapplication.shared.messaging.model.busschedule.CompanyRequestSchedule;
import local.jordi.busapplication.shared.model.BusSchedule;

public class CompanyLogic {

    private Company company;

    private CompanyBrokerGateway companyBrokerGateway;

    private ObservableList<CompanyBusReference> companyBusses;

    public CompanyLogic(String companyName) {
        company = new Company(companyName);
        companyBrokerGateway = new CompanyBrokerGateway();
        
        companyBusses = FXCollections.observableArrayList();
        companyBrokerGateway.registerCompany(companyName);
        companyBrokerGateway.SubscribeCompanyRequestScheduleReceived(this::companyScheduleRequestReceived);
        
    }

    private void companyScheduleRequestReceived(CompanyRequestSchedule companyRequestSchedule) {
        BusSchedule busSchedule = company.getBusSchedule(companyRequestSchedule.getBusNumber());
        companyBrokerGateway.sendReplySchedule(busSchedule);
    }

    public ObservableList getActiveBusses() {
        return companyBusses;
    }
}
