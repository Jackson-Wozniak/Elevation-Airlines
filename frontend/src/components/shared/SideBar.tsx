import '../../styles/shared.css';
import logo from '../../assets/icon.png'
import { Link } from 'react-router-dom';

function SideBar(){
    
    function routeTo(path: string){

    }

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
        </div>
    )
}

export default SideBar;