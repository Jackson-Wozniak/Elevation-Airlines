import '../../../styles/shared.css';
import logo from '../../../assets/icon.png'
import { useDemoMode } from '../../../AppInitializer';
import HelpOutlineIcon from '@mui/icons-material/HelpOutline';
import GitHubIcon from '@mui/icons-material/GitHub';
import HomeOutlinedIcon from '@mui/icons-material/HomeOutlined';
import SupervisorAccountOutlinedIcon from '@mui/icons-material/SupervisorAccountOutlined';
import SidebarSection from './SidebarSection';
import AirplanemodeActiveOutlinedIcon from '@mui/icons-material/AirplanemodeActiveOutlined';
import GpsFixedOutlinedIcon from '@mui/icons-material/GpsFixedOutlined';
import MapOutlinedIcon from '@mui/icons-material/MapOutlined';
import AirplaneTicketIcon from '@mui/icons-material/AirplaneTicket';
import BookOnlineIcon from '@mui/icons-material/BookOnline';
import ConfirmationNumberIcon from '@mui/icons-material/ConfirmationNumber';
import DashboardIcon from '@mui/icons-material/Dashboard';
import RouteIcon from '@mui/icons-material/Route';
import ListAltIcon from '@mui/icons-material/ListAlt';
import ConnectingAirportsIcon from '@mui/icons-material/ConnectingAirports';
import FlightClassIcon from '@mui/icons-material/FlightClass';
import PublicIcon from '@mui/icons-material/Public';
import FlightTakeoffIcon from '@mui/icons-material/FlightTakeoff';

const DEMO_MESSAGE: string = "The server could not be reached. If you believe there was an issue, reload the page."
    + "\n\nDemo mode uses sample data in place of all API calls to simulate application functionality. Enjoy!";

function SideBar(){
    return (
        <div id="sidebar-container">
            <div id="sidebar-logo-container">
                <div id="sidebar-logo-caption">
                    <p>ELEVATION</p><img src={logo} height={20}></img><p>AIRLINES</p>
                </div>
                <p id="sidebar-logo-subtext">REACHING NEW HEIGHTS</p>
            </div>
            <div id="sidebar-menu-container">
                <SidebarSection
                    title="Home"
                    icon={<HomeOutlinedIcon className="sidebar-icons" />}
                    links={[
                        { label: 'Flight Listings', to: '/', element: <FlightTakeoffIcon className="sidebar-icons"/>},
                        { label: 'Live Flight Map', to: '/', element: <PublicIcon className="sidebar-icons"/>},
                    ]}
                />
                <SidebarSection
                    title="Reservations"
                    icon={<AirplaneTicketIcon className="sidebar-icons" />}
                    links={[
                        { label: 'Buy Tickets', to: '/', element: <FlightClassIcon className="sidebar-icons"/>},
                        { label: 'View Ticket Status', to: '/', element: <BookOnlineIcon className="sidebar-icons"/>},
                    ]}
                />
                <SidebarSection
                    title="Admin"
                    icon={<SupervisorAccountOutlinedIcon className="sidebar-icons" />}
                    links={[
                        { label: 'Dashboard', to: '/', element: <DashboardIcon className="sidebar-icons"/>},
                        { label: 'Route Network', to: '/', element: <RouteIcon className="sidebar-icons"/>},
                        { label: 'Flight Event Queue', to: '/', element: <ListAltIcon className="sidebar-icons"/>},
                        { label: 'Plane Tracking', to: '/', element: <ConnectingAirportsIcon className="sidebar-icons"/>},
                    ]}
                />
            </div>

            <div id="sidebar-filler-container"></div>
            <div id="sidebar-utils-container">
                {useDemoMode() && (
                    <button className="sidebar-demo-button" onClick={() => alert(DEMO_MESSAGE)}>
                        DEMO MODE <HelpOutlineIcon style={{marginLeft: "4px", color: "crimson"}}/>
                    </button>
                )}
                    <a href="https://github.com/Jackson-Wozniak/Elevation-Airlines" target="_blank" className="sidebar-demo-button">
                        <GitHubIcon style={{ color: "white"}} />
                    </a>
            </div>
        </div>
    )
}

export default SideBar;