import { useEffect, useState } from "react";
import Page from "../Shared/Page/Page";
import FlightTable from "./FlightTable";
import { fetchFlights } from "../../api/FlightHttpClient";
import type { FlightDto } from "../../types/flight/flight.dto";
import Box from "@mui/material/Box";

const FlightListingsPage: React.FC = () => {
    const [flights, setFlights] = useState<FlightDto[]>([]);

    useEffect(() => {
        const fetchFlightApi = async () => {
            fetchFlights().then(f => setFlights(f));
        }
        fetchFlightApi();
    }, []);

    return (
        <Page>
            <Box sx={{width: "100%", height: "100%", display: "flex", justifyContent: "center"}}>
                <FlightTable flights={flights}/>
            </Box>
        </Page>
    )
}

export default FlightListingsPage;