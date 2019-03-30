package local.jordi.busapplication.company.logic;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import local.jordi.busapplication.company.logic.message.CompanyBrokerGateway;
import local.jordi.busapplication.company.logic.model.Company;
import local.jordi.busapplication.company.logic.model.CompanyBusReference;
import local.jordi.busapplication.shared.messaging.model.busschedule.CompanyRequestSchedule;
import local.jordi.busapplication.shared.messaging.model.stopreached.CompanyBusReachedStop;
import local.jordi.busapplication.shared.model.BusSchedule;

public class CompanyLogic {

    private Company company;

    private CompanyBrokerGateway companyBrokerGateway;

    private ObservableList<CompanyBusReference> companyBuses;

    public CompanyLogic(String companyName) {
        company = new Company(companyName);
        companyBrokerGateway = new CompanyBrokerGateway();
        
        companyBuses = FXCollections.observableArrayList();
        companyBrokerGateway.registerCompany(companyName);
        companyBrokerGateway.SubscribeCompanyRequestScheduleReceived(this::companyScheduleRequestReceived);
        companyBrokerGateway.SubscribeCompanyBusReachedStop(this::companyBusReachedStopReceived);
    }

    private void companyBusReachedStopReceived(CompanyBusReachedStop companyBusReachedStop) {
        for (int i = 0; i < companyBuses.size(); i++) {
            if (companyBuses.get(i).getBusNumber() == companyBusReachedStop.getBusNumber()) {
                int finalI = i;
                Platform.runLater(() -> companyBuses.set(finalI, new CompanyBusReference(companyBusReachedStop.getBusNumber(), companyBusReachedStop.getReachedStop(), companyBusReachedStop.getNextStop())));
                return;
            }
        }
    }

    private void companyScheduleRequestReceived(CompanyRequestSchedule companyRequestSchedule) {
        BusSchedule busSchedule = company.getBusSchedule(companyRequestSchedule.getBusNumber());
        companyBrokerGateway.sendReplySchedule(busSchedule);

        Platform.runLater(() -> {
            String nextBusStop = busSchedule.getScheduleItems().get(0).getBusStop();
            companyBuses.add(new CompanyBusReference(companyRequestSchedule.getBusNumber(), "", nextBusStop));
        });
    }

    public ObservableList getActiveBusses() {
        return companyBuses;
    }
}
