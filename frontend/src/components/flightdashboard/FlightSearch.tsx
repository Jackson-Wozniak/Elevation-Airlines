import type { FlightSearchConstraints } from "../../types/FlightTypes";
import '../../styles/FlightSeach.css';

function FlightSearch({updateSearchConstraints}: {updateSearchConstraints: (constraints: FlightSearchConstraints) => void;}){
    return (
        <div id="flight-search-container">
            <div id="search-function-container">
                <button onClick={() => updateSearchConstraints({})}>Search</button>
            </div>
            <div id="search-presets-container">
                <button className="flight-search-preset">BOARDING</button>
                <button className="flight-search-preset">AT GATE</button>
                <button className="flight-search-preset">LIVE FLIGHTS</button>
            </div>
        </div>
    )
}

export default FlightSearch;