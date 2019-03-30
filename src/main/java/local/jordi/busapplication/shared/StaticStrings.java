package local.jordi.busapplication.shared;

public class StaticStrings {

    //<editor-fold desc="Component names">

    //<editor-fold desc="Bus company names">
    public static final String ARRIVA_COMPANY_NAME = "Arriva";
    public static final String BRAVO_COMPANY_NAME = "Bravo";
    //</editor-fold>

    //<editor-fold desc="Bus stop names">
    public static final String BUSSTOP_A = "BusStopA";
    public static final String BUSSTOP_B = "BusStopB";
    public static final String BUSSTOP_C = "BusStopC";
    public static final String BUSSTOP_D = "BusStopD";
    //</editor-fold>

    //</editor-fold>

    //<editor-fold desc="Queues">

    //<editor-fold desc="Broker registration">
    public static final String BROKER_REGISTER_COMPANY_QUEUE = "BrokerRegisterCompany";
    public static final String BROKER_REGISTER_BUS_QUEUE = "BrokerRegisterBus";
    public static final String BROKER_REGISTER_BUSSTOP_QUEUE = "BrokerRegisterBusstop";
    //</editor-fold>

    //<editor-fold desc="Bus schedule">
    public static final String REQUEST_SCHEDULE_BROKER_QUEUE = "RequestScheduleBroker";
    public static final String REQUEST_SCHEDULE_COMPANY_QUEUE = "RequestScheduleCompany";
    public static final String REPLY_SCHEDULE_BROKER_QUEUE = "ReplyScheduleBroker";
    public static final String REPLY_SCHEDULE_BUS_QUEUE = "ReplyScheduleBus";
    //</editor-fold>

    //<editor-fold desc="Bus stop reached">
    public static final String BUSSTOP_REACHED_BROKER_QUEUE = "BusStopReachedBroker";
    public static final String BUSSTOP_REACHED_COMPANY_QUEUE = "BusStopReachedCompany";
    //</editor-fold>

    //</editor-fold>

    private StaticStrings() {
    }
}
