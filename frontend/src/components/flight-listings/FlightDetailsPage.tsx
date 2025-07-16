import { useParams } from "react-router-dom";
import SideBar from "../shared/SideBar";
import Container from "../shared/Container";
import Page from "../shared/Page";


function FlightDetailsPage(){
    const flightId = useParams();

    return (
        <Page>
            <SideBar/>
            <Container>

            </Container>
        </Page>
    )
}

export default FlightDetailsPage;