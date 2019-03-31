package local.jordi.busapplication.broker.logic.message.company;

import local.jordi.busapplication.broker.logic.event.CompanyReplyScheduleReceivedEvent;
import local.jordi.busapplication.broker.logic.message.BrokerRegisterGateway;
import local.jordi.busapplication.broker.logic.message.IGatewayLog;
import local.jordi.busapplication.shared.messaging.model.brokerregistration.CompanyRegistration;
import local.jordi.busapplication.shared.messaging.model.busschedule.CompanyRequestSchedule;
import local.jordi.busapplication.shared.messaging.model.stopreached.CompanyBusReachedStop;

import java.util.HashMap;
import java.util.Map;

public class CompanyGateway {
    private  IGatewayLog gatewayLog;

    private CompanyReceiverGateway companyReceiverGateway;
    private Map<String, CompanySenderGateway> companyGatewaysByCompanyName;

    public CompanyGateway(BrokerRegisterGateway registerGateway, IGatewayLog gatewayLog) {
        this.gatewayLog = gatewayLog;
        companyGatewaysByCompanyName = new HashMap<>();
        companyReceiverGateway = new CompanyReceiverGateway();

        registerGateway.subscribeCompanyRegistrationReceived(this::companyRegistrationReceived);
    }

    public void requestSchedule(String company, CompanyRequestSchedule companyRequestSchedule) {
        if (companyGatewaysByCompanyName.containsKey(company))
        {
            CompanySenderGateway companySenderGateway = companyGatewaysByCompanyName.get(company);

            gatewayLog.log("Sending CompanyRequestSchedule, contents: " + companyRequestSchedule);

            companySenderGateway.requestSchedule(companyRequestSchedule);
        }
    }

    public void subscribeCompanyReplySchedule(CompanyReplyScheduleReceivedEvent listener)
    {
        companyReceiverGateway.subscribeCompanyReplySchedule(listener);
    }

    private void companyRegistrationReceived(CompanyRegistration companyRegistration) {
        CompanySenderGateway companySenderGateway = new CompanySenderGateway(companyRegistration.getCompanyScheduleRequestListeningQueue(), companyRegistration.getCompanyBusReachedStopListeningQueue());
        companyGatewaysByCompanyName.put(companyRegistration.getCompanyName(), companySenderGateway);

        gatewayLog.log("CompanyRegistration received, registered: " + companyRegistration.getCompanyName());
    }

    public void sendCompanyBusReachedStop(String company, CompanyBusReachedStop companyBusReachedStop) {
        if (companyGatewaysByCompanyName.containsKey(company))
        {
            CompanySenderGateway companySenderGateway = companyGatewaysByCompanyName.get(company);

            gatewayLog.log("Sending CompanyBusReachedStop, contents: " + companyBusReachedStop);

            companySenderGateway.sendCompanyBusReachedStop(companyBusReachedStop);
        }
    }
}
