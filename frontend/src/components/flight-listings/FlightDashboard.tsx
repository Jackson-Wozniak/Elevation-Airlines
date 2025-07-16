import FlightTable from "../flight-listings/FlightTable";
import Container from "../shared/Container";
import Page from "../shared/Page";
import SideBar from "../shared/SideBar";
import type { FlightSearchConstraints } from "./../../types/FlightTypes";
import type { FlightDto } from "./../../types/Dtos";
import { useState, useEffect } from "react";
import { getAllFlights } from "./../../shared-functions/FlightHttpClient";
import { useDemoMode } from "./../../AppInitializer";

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