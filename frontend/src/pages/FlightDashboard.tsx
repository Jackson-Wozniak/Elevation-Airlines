import FlightTable from "../components/flightdashboard/FlightTable";
import Container from "../components/shared/Container";
import Page from "../components/shared/Page";
import SideBar from "../components/shared/SideBar";
import type { FlightSearchConstraints, FlightTableEntry } from "../types/FlightTypes";
import type { FlightDto } from "../types/Dtos";
import { useState, useEffect } from "react";
import { getAllFlights } from "../shared-functions/FlightHttpClient";
import FlightSearch from "../components/flightdashboard/FlightSearch";
import { useDemoMode } from "../AppInitializer";

function FlightDashboard(){
    const isDemo: boolean = useDemoMode();
    const [flights, setFlights] = useState<FlightDto[]>([]);

    useEffect(() => {
        const httpClientGetAllFlights = async () => {
            let data = await getAllFlights(isDemo);
            return data;
        }

        httpClientGetAllFlights()
            .then(flights => setFlights([...flights]));
    }, []);

    function searchFlights(constraints: FlightSearchConstraints){

    }

    return (
        <Page>
            <SideBar/>
            <Container>
                {/* <FlightSearch updateSearchConstraints={searchFlights}/> */}
                <FlightTable flights={flights}/>
            </Container>
        </Page>
    )
}

export default FlightDashboard;