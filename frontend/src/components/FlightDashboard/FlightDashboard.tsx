import Box from "@mui/material/Box";
import { useDemoMode } from "../../AppInitializer";
import Page from "../Shared/Page/Page";
import Map from '../Shared/Maps/Map';

function FlightDashboard(){
    const isDemo: boolean = useDemoMode();

    return (
        <Page>
            <Box>
                <Map/>
            </Box>
        </Page>
    )
}

export default FlightDashboard;