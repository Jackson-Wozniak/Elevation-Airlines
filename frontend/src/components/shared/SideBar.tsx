import '../../styles/shared.css';
import logo from '../../assets/icon.png'
import { Link } from 'react-router-dom';
import { useDemoMode } from '../../AppInitializer';
import HelpOutlineIcon from '@mui/icons-material/HelpOutline';
import GitHubIcon from '@mui/icons-material/GitHub';

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
                <Link className="sidebar-links" to="/">Home</Link>
                <Link className="sidebar-links" to="documentation">Project Docs</Link>
                <Link className="sidebar-links" to="admin">Admin Dashboard</Link>
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