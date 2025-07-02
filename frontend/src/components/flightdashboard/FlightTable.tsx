import type { FlightTableEntry } from '../../types/FlightTypes';
import '../../styles/FlightTable.css';
import type { FlightDto } from '../../types/Dtos';

function FlightTable({flights}: {flights: FlightDto[]}){
    function getStatusClassname(status: string){
        switch(status){
            case "IN FLIGHT": return "in-progress-color"
            case "BOARDING": return "boarding-color"
            default: return "other-color";
        }
    }

    return (
        <div className="flight-table-container">
            <div className="flight-table">
                <div className="flight-table-header">
                    <p>Callsign</p>
                    <p>Departure</p>
                    <p>Boarding</p>
                    <p>Destination</p>
                    <p>Plane</p>
                    <p>Status</p>
                </div>

                {flights.map((flight: FlightDto, index: number) => {
                    return (
                        <div key={index} className={"flight-table-row" + (index % 2 == 0 ? "": " flight-table-row-even")}>
                            <p>{flight.identifier}</p>
                            <p>{flight.departure}</p>
                            <p>{flight.destination}</p>
                            <p>{flight.callsign}</p>
                            <p>{flight.planeType}</p>
                            <p>{flight.scheduledBoarding}</p>
                        </div>
                    )
                })}
            </div>
        </div>
    )
}

export default FlightTable;