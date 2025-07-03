import type { FlightTableEntry } from '../../types/FlightTypes';
import '../../styles/FlightTable.css';
import type { FlightDto } from '../../types/Dtos';
import FlightTakeoffOutlinedIcon from '@mui/icons-material/FlightTakeoffOutlined';

function FlightTable({flights}: {flights: FlightDto[]}){
    function getStatusClassname(status: string){
        switch(status){
            case "SCHEDULED": 
                return "scheduled-color";
            case "BOARDING": 
                return "boarding-color";
            case "TAXIING":
            case "IN FLIGHT": 
                return "in-progress-color";
            case "ARRIVED":
                return "arrived-color";
            default: return "other-color";
        }
    }

    return (
        <div className="flight-table-container">
            <div className="flight-table">
                <div className="flight-table-header">
                    <p>Callsign</p>
                    <p>Route</p>
                    <p>Boarding</p>
                    <p>Plane</p>
                    <p>Status</p>
                </div>

                {flights.map((flight: FlightDto, index: number) => {
                    return (
                        <div key={index} className={"flight-table-row" + (index % 2 == 0 ? "": " flight-table-row-even")}>
                            <p>{flight.callsign}</p>
                            <p>{flight.departure} {<FlightTakeoffOutlinedIcon className="route-icon"/> } {flight.destination}</p>
                            <p>{flight.scheduledBoarding}</p>
                            <p>{flight.planeType}</p>
                            <p className={getStatusClassname(flight.status)}>{flight.status}</p>
                        </div>
                    )
                })}
            </div>
        </div>
    )
}

export default FlightTable;