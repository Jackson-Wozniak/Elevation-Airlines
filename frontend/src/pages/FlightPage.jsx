import '../styles/FlightPage.css';
import Navbar from '../components/Navbar';
import { useEffect, useState } from 'react';
import FlightLog from '../components/FlightLog';
import { FlightSummary } from '../client/AirlineHttpClient';
import { getAllFlights } from '../client/AirlineHttpClient';

function FlightPage() {
    const [flights, setFlights] = useState([]);

    useEffect(() => {
        const fetchData = async () => setFlights(await getAllFlights());

        fetchData();
    }, [flights]);

    return (  
        <div className="flight-page">
            <Navbar />
            <div className="flight-table">
            {flights.map((flight, index) => (
                <FlightLog key={index} flight={flight}/>
            ))};
            </div>
        </div>
    );
}

export default FlightPage;