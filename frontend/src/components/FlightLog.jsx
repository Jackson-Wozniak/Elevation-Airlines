import '../styles/FlightPage.css';

function FlightLog(props){
    return(
        <div className="flight-log-container">
            <div className="plane-info">
                <p>{props.flight.callsign}</p>
                <p>{props.flight.planeType}</p>
            </div>
            <div className="route-info">
                <p className="time">{props.flight.flightTime}</p>
                <div className="route">
                    <span>{props.flight.departure}</span>
                    <span className="arrow">→</span>
                    <span>{props.flight.destination}</span>
                </div>
            </div>
            <div className="details-info">
                <p>{props.flight.distanceMiles} miles</p>
                <p>Takeoff: {props.flight.departureTime}</p>
            </div>
        </div>
    );   
}

export default FlightLog;