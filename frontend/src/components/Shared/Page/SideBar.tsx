import '../../../styles/shared.css';
import logo from '../../../assets/icon.png'
import { useDemoMode } from '../../../AppInitializer';
import HelpOutlineIcon from '@mui/icons-material/HelpOutline';
import GitHubIcon from '@mui/icons-material/GitHub';
import HomeOutlinedIcon from '@mui/icons-material/HomeOutlined';
import DescriptionOutlinedIcon from '@mui/icons-material/DescriptionOutlined';
import SupervisorAccountOutlinedIcon from '@mui/icons-material/SupervisorAccountOutlined';
import SidebarSection from './SidebarSection';
import AirplanemodeActiveOutlinedIcon from '@mui/icons-material/AirplanemodeActiveOutlined';
import GpsFixedOutlinedIcon from '@mui/icons-material/GpsFixedOutlined';
import MapOutlinedIcon from '@mui/icons-material/MapOutlined';
import MenuBookOutlinedIcon from '@mui/icons-material/MenuBookOutlined';
import CodeOutlinedIcon from '@mui/icons-material/CodeOutlined';
import DnsOutlinedIcon from '@mui/icons-material/DnsOutlined';

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
                    title="Dashboard"
                    icon={<HomeOutlinedIcon className="sidebar-icons" />}
                    links={[
                        { label: 'Flights', to: '/', element: <AirplanemodeActiveOutlinedIcon className="sidebar-icons"/>},
                        { label: 'Tracker', to: '/', element: <GpsFixedOutlinedIcon className="sidebar-icons"/>},
                        { label: 'Map', to: '/', element: <MapOutlinedIcon className="sidebar-icons"/>},
                    ]}
                />
                <SidebarSection
                    title="Project Documentation"
                    icon={<DescriptionOutlinedIcon className="sidebar-icons" />}
                    links={[
                        { label: 'Overview', to: '/docs', element: <MenuBookOutlinedIcon className="sidebar-icons"/>},
                        { label: 'Frontend', to: '/docs', element: <CodeOutlinedIcon className="sidebar-icons"/>},
                        { label: 'Backend', to: '/docs', element: <DnsOutlinedIcon className="sidebar-icons"/>},
                    ]}
                />
                <SidebarSection
                    title="Admin Dashboard"
                    icon={<SupervisorAccountOutlinedIcon className="sidebar-icons" />}
                    links={[]}
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