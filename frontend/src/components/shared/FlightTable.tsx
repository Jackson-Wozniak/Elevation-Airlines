import '../../styles/FlightTable.css';
import type { FlightDto } from '../../types/flight/flight.dto';
import FlightTakeoffOutlinedIcon from '@mui/icons-material/FlightTakeoffOutlined';
import { DataGrid, type GridColDef, type GridRenderCellParams } from '@mui/x-data-grid';
import { Stack, Typography } from '@mui/material';
import { Colors } from '../../types/shared/ApplicationThemes';

const Columns: GridColDef[] = [
    {
        field: "route",
        display: "flex",
        flex: .3,
        headerName: "Route",
        renderCell: (params: GridRenderCellParams) => {
            return (<Typography>{params.row.flightPlan.departure.airportCode} {<FlightTakeoffOutlinedIcon className="route-icon"/> } 
                {params.row.flightPlan.destination.airportCode}</Typography> )
        }
    },
    {
        field: "plane.callSign",
        headerName: "Aircraft",
        display: "flex",
        flex: .3,
        renderCell: (params: GridRenderCellParams) => {
            return (<Typography>{params.row.plane.callSign}</Typography> )
        }
    },
    {
        field: "boardingTime",
        headerName: "Boarding",
        display: "flex",
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

    return (
        <Stack width="90%" display="flex" justifyContent="center" alignItems="center" margin={0} marginTop="25px">
            <DataGrid
             getRowClassName={(params) => params.indexRelativeToCurrentPage % 2 === 0 ? 'even-row' : 'odd-row' }
            sx={{
                display: "flex", width: "100%",
                backgroundColor: "#2C303D",
                color: "white",
                border: "none",
                '& .MuiDataGrid-columnHeaderTitleContainer': {
                    backgroundColor: Colors.Dark.AccentLightest, color: "white",
                    display: "flex", alignItems: "center", justifyContent: "center"
                },
                '& .MuiDataGrid-columnHeader': {
                    backgroundColor: Colors.Dark.AccentLightest, width: "100%",
                    display: "flex", alignItems: "center", justifyContent: "center"
                },
                '& .MuiDataGrid-cell': {
                    border: "none", color: "white",
                    display: "flex", alignItems: "center", justifyContent: "center"
                },
                '& .MuiDataGrid-row:hover': {
                    backgroundColor: Colors.Dark.AccentLightest
                },
                '& .MuiDataGrid-scrollBarContent': {
                    overflow: 'hidden'
                },
                '& .even-row': {
                    backgroundColor: "rgba(36, 40, 50, 0.5)"
                }
            }}
                rows={flights.map((item, index) => ({ id: index + 1, ...item }))} columns={Columns} getRowId={(row) => row.id}
            />
        </Stack>
    )
}

export default FlightTable;