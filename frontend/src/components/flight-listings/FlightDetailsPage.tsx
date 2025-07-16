import { useParams } from "react-router-dom";
import SideBar from "../components/shared/SideBar";
import Container from "../components/shared/Container";
import Page from "../components/shared/Page";


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