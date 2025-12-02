import type React from "react";
import Page from "../../layout/Page";
import DataCard from "./DataCard";
import Typography from "@mui/material/Typography";
import { useEffect, useState } from "react";
import type { FlightDto } from "../../../types/flight/flight.dto";
import { fetchFlights } from "../../../api/FlightHttpClient";
import FlightTable from "../../shared/FlightTable";
import Box from "@mui/material/Box";
import { useTheme } from "@mui/material/styles";
import { Colors } from "../../../theme/Colors";
import Stack from "@mui/material/Stack";
import { fetchAirlineOperationData } from "../../../api/AirlineOperationDataClient";
import type { AirlineOperationDataDto } from "../../../types/airline/AirlineOperationData";

const AdminDashboard: React.FC = () => {
    const theme = useTheme();
    const [airlineData, setAirlineData] = useState<AirlineOperationDataDto>();
    const [upcomingFlights, setUpcomingFlights] = useState<FlightDto[]>([]);

    useEffect(() => {
        const fetchData = async () => {
            fetchFlights().then(f => {
                f.sort((flight1: FlightDto, flight2: FlightDto) => 
                    new Date(flight1.boardingTime).getTime() 
                    - new Date(flight2.boardingTime).getTime());
                f = f.slice(0, 5);
                setUpcomingFlights(f)
            });
            fetchAirlineOperationData().then((data: AirlineOperationDataDto) => {
                setAirlineData(data)
            });
        }
        fetchData();
    }, []);

    return (
        <Page>
            <Box sx={{
                width: "100%", height: "100%", display: "flex", flexDirection: "column", 
                justifyContent: "flexStart", alignItems: "center", textAlign: "center"
            }}>
                <Stack width="100%" direction="row" justifyContent="space-evenly" mt="15px" mb="20px">
                    <DataCard header="Flights Currently Scheduled" 
                        value={airlineData?.flightsCurrentlyScheduled ?? 0} accentColor={Colors.LightBlue}/>
                    <DataCard header="Planes Currently Flying" 
                        value={airlineData?.planesCurrentlyFlying ?? 0} accentColor={Colors.LightGreen}/>
                    <DataCard header="Total Miles Flown" 
                        value={airlineData?.totalMilesFlown ?? 0} accentColor={Colors.Purple}/>
                    <DataCard header="Total Flights Completed" 
                        value={airlineData?.totalFlightsCompleted ?? 0} accentColor={Colors.LightBlue}/>
                </Stack>

                <Typography variant="h4" color={theme.palette.text.primary}>Upcoming Flight(s)</Typography>
                <FlightTable flights={upcomingFlights}/>
            </Box>
        </Page>
    )
}

export default AdminDashboard;