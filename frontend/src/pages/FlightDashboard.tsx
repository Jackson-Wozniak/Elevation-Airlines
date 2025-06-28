import FlightTable from "../components/flightdashboard/FlightTable";
import Container from "../components/shared/Container";
import Page from "../components/shared/Page";
import SideBar from "../components/shared/SideBar";
import type { FlightTableEntry } from "../types/FlightTypes";

function FlightDashboard(){
    const flight: FlightTableEntry = {
        callsign: "ELEV 120",
        departureCode: "KBOS",
        destinationCode: "KLAX",
        departureTime: "06/28/2025 11:00am",
        arrivalTime: "06/28/2025 3:00pm",
        planeType: "Boeing 737",
        status: "IN FLIGHT"
    };
    const flight2: FlightTableEntry = {
        callsign: "ELEV 232",
        departureCode: "KBOS",
        destinationCode: "KLAX",
        departureTime: "06/28/2025 11:00am",
        arrivalTime: "06/28/2025 3:00pm",
        planeType: "Boeing 737",
        status: "BOARDING"
    };

    const flight3: FlightTableEntry = {
        callsign: "ELEV 991",
        departureCode: "KBOS",
        destinationCode: "KLAX",
        departureTime: "06/31/2025 9:00am",
        arrivalTime: "06/28/2025 3:00pm",
        planeType: "Boeing 737 MAX 10",
        status: "AT GATE"
    };

    return (
        <Page>
            <SideBar/>
            <Container>
                <FlightTable flights={[flight, flight2, flight3, flight, flight2, flight3, flight2, flight]}/>
            </Container>
        </Page>
    )
}

export default FlightDashboard;