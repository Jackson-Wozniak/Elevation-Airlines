import type { FlightTableEntry } from '../../types/FlightTypes';
import '../../styles/FlightTable.css';
import type { FlightDto } from '../../types/Dtos';
import FlightTakeoffOutlinedIcon from '@mui/icons-material/FlightTakeoffOutlined';
import { DataGrid, type GridColDef, type GridRenderCellParams } from '@mui/x-data-grid';
import { Box, Stack, Typography } from '@mui/material';
import { grey } from '@mui/material/colors';
import { Colors } from '../../types/shared/ApplicationThemes';

const Columns: GridColDef[] = [
    { 
        field: "callsign",
        headerName: "Call Sign",
        flex: .15,
    },
    {
        field: "departure",
        display: "flex",
        flex: .3,
        headerName: "Route",
        renderCell: (params: GridRenderCellParams) => {
            return (<Typography>{params.row.departure} {<FlightTakeoffOutlinedIcon className="route-icon"/> } 
                {params.row.destination}</Typography> )
        }
    },
    {
        field: "planeType",
        headerName: "Aircraft",
        flex: .15
    },
    {
        field: "scheduledBoarding",
        headerName: "Boarding",
        flex: .2
    },
    {
        field: "status",
        headerName: "Status",
        flex: .2,
        display: "flex",
        align: "center"
    }
];

const FlightTable: React.FC<{
    flights: FlightDto[]
}> = ({
    flights
}) => {
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

    function getRowId(row: FlightDto){
        return row.identifier;
    }

    return (
        <Stack width="80vw" display="flex" justifyContent="center" alignItems="center" marginTop="25px">
            <DataGrid 
             getRowClassName={(params) => params.indexRelativeToCurrentPage % 2 === 0 ? 'even-row' : 'odd-row' }
            sx={{ 
                display: "flex", width: "80%",
                backgroundColor: "#2C303D",
                color: "white",
                border: "none",
                '& .MuiDataGrid-columnHeaderTitleContainer': {
                    backgroundColor: Colors.Dark.AccentLightest, color: "white",
                    display: "flex", alignItems: "center", justifyContent: "center"
                },
                '& .MuiDataGrid-columnHeader': {
                    backgroundColor: Colors.Dark.AccentLightest,
                    display: "flex", alignItems: "center", justifyContent: "center"
                },
                '& .MuiDataGrid-cell': {
                    border: "none", color: "white",
                    display: "flex", alignItems: "center", justifyContent: "center"
                },
                '& .MuiDataGrid-row:hover': {
                    backgroundColor: Colors.Dark.AccentLightest
                },
                '& .even-row': {
                    // backgroundColor: "#2C303D"
                    backgroundColor: "rgba(36, 40, 50, 0.5)"
                }
            }}
                rows={flights} columns={Columns} getRowId={getRowId}
            />
        </Stack>
    )
}
// function FlightTable(){
    

//     return (
//         <div className="flight-table-container">
//             <div className="flight-table">
//                 <div className="flight-table-header">
//                     <p>Callsign</p>
//                     <p>Route</p>
//                     <p>Boarding</p>
//                     <p>Plane</p>
//                     <p>Status</p>
//                 </div>

//                 {flights.map((flight: FlightDto, index: number) => {
//                     return (
//                         <div key={index} className={"flight-table-row" + (index % 2 == 0 ? "": " flight-table-row-even")}>
//                             <p>{flight.callsign}</p>
//                             <p>{flight.departure} {<FlightTakeoffOutlinedIcon className="route-icon"/> } {flight.destination}</p>
//                             <p>{flight.scheduledBoarding}</p>
//                             <p>{flight.planeType}</p>
//                             <p className={getStatusClassname(flight.status)}>{flight.status}</p>
//                         </div>
//                     )
//                 })}
//             </div>
//         </div>
//     )
// }

export default FlightTable;