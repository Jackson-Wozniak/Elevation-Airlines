import Box from "@mui/material/Box";
import { useDemoMode } from "../../AppInitializer";
import Page from "../Shared/Page/Page";

function FlightDashboard(){
    const isDemo: boolean = useDemoMode();

    return (
        <Page>
            <Box>
                
            </Box>
        </Page>
    )
}

export default FlightDashboard;