package github.wozniak.flighttrackingservice.airline_management.fleet_manager.enums;

public enum PlaneState {
    AT_GATE("At gate"),
    BOARDING("Boarding"),
    TAXIING("Taxiing"),
    IN_FLIGHT("In Flight"),
    LANDED("Landed");

    private final String status;

    PlaneState(String str){
        this.status = str;
    }

    @Override
    public String toString(){
        return status;
    }
}
