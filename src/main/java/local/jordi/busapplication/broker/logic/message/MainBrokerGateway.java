package local.jordi.busapplication.broker.logic.message;

import local.jordi.busapplication.broker.logic.event.BrokerMessageReceived;
import local.jordi.busapplication.broker.logic.message.bus.BusGateway;
import local.jordi.busapplication.broker.logic.message.busstop.BusStopGateway;
import local.jordi.busapplication.broker.logic.message.company.CompanyGateway;
import local.jordi.busapplication.broker.logic.model.BusReference;
import local.jordi.busapplication.shared.event.EventContainer;
import local.jordi.busapplication.shared.messaging.model.busschedule.BusReplySchedule;
import local.jordi.busapplication.shared.messaging.model.busschedule.BusRequestSchedule;
import local.jordi.busapplication.shared.messaging.model.busschedule.CompanyReplySchedule;
import local.jordi.busapplication.shared.messaging.model.busschedule.CompanyRequestSchedule;
import local.jordi.busapplication.shared.messaging.model.stopreached.BusReachedStop;
import local.jordi.busapplication.shared.messaging.model.stopreached.BusStopBusReachedStop;
import local.jordi.busapplication.shared.messaging.model.stopreached.CompanyBusReachedStop;
import local.jordi.busapplication.shared.model.BusSchedule;
import local.jordi.busapplication.shared.model.BusynessLevel;

public class MainBrokerGateway implements IGateway, IGatewayLog {
    private EventContainer<BrokerMessageReceived, String> brokerMessageListeners;

    private BrokerRegisterGateway brokerRegisterGateway;
    private BusGateway busGateway;
    private BusStopGateway busStopGateway;
    private CompanyGateway companyGateway;

    public MainBrokerGateway() {
        brokerMessageListeners = new EventContainer<>();
        brokerRegisterGateway = new BrokerRegisterGateway();

        busGateway = new BusGateway(brokerRegisterGateway, this);
        companyGateway = new CompanyGateway(brokerRegisterGateway, this);
        busStopGateway = new BusStopGateway(brokerRegisterGateway, this);

        initEventListeners();
    }

    public void subscribe(BrokerMessageReceived listener)
    {
        brokerMessageListeners.Subscribe(listener);
    }

    private void initEventListeners() {
        busGateway.subscribeBusRequestScheduleReceived(this::busRequestScheduleReceived);
        busGateway.subscribeBusReachedStopReceived(this::busReachedStopReceived);
        companyGateway.subscribeCompanyReplySchedule(this::companyReplyScheduleReceived);
    }

    private void busReachedStopReceived(BusReachedStop busReachedStop) {
        CompanyBusReachedStop companyBusReachedStop = new CompanyBusReachedStop(busReachedStop.getBusNumber(), busReachedStop.getReachedStop(), busReachedStop.getNextStop());
        companyGateway.sendCompanyBusReachedStop(busReachedStop.getCompany(), companyBusReachedStop);

        BusStopBusReachedStop busStopBusReachedStop = new BusStopBusReachedStop(busReachedStop.getBusNumber(), busReachedStop.getCompany(), busReachedStop.getBusynessLevel());
        busStopGateway.sendBusStopBusReachedStop(busReachedStop.getNextStop(), busStopBusReachedStop);
    }

    private void busRequestScheduleReceived(BusRequestSchedule busRequestSchedule) {
        String company = busRequestSchedule.getCompany();
        CompanyRequestSchedule companyRequestSchedule = new CompanyRequestSchedule(busRequestSchedule.getBusNumber());

        log("BusRequestSchedule received, contents: " + busRequestSchedule);

        companyGateway.requestSchedule(company, companyRequestSchedule);
    }

    private void companyReplyScheduleReceived(CompanyReplySchedule companyReplySchedule) {
        BusReference busReference = new BusReference(companyReplySchedule.getBusNumber(), companyReplySchedule.getCompany());
        BusReplySchedule busReplySchedule = new BusReplySchedule(companyReplySchedule.getBusSchedule());

        log("CompanyReplySchedule received, contents: " + companyReplySchedule);

        busGateway.sendBusReplySchedule(busReference, busReplySchedule);

        sendFirstBusStopMessage(busReplySchedule.getBusSchedule());
    }

    private void sendFirstBusStopMessage(BusSchedule busSchedule)
    {
        String busStop = busSchedule.getScheduleItems().get(0).getBusStop();
        BusStopBusReachedStop busStopBusReachedStop = new BusStopBusReachedStop(busSchedule.getBusNumber(), busSchedule.getCompany(), BusynessLevel.LOW);
        busStopGateway.sendBusStopBusReachedStop(busStop, busStopBusReachedStop);
    }

    @Override
    public void log(String message) {
        brokerMessageListeners.Fire(message);
    }
}
