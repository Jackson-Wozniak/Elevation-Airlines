import '../styles/Navbar.css';
import { useNavigate } from 'react-router-dom';

function Navbar() {
    const navigate = useNavigate();

    return (  
        <div className="navbar">
            <button onClick={() => navigate("/")} id="logo-navbar-button">
                <img src={require('../images/logo_resized.png')} alt="logo" id="logo-navbar"/>
            </button>
            <div className="middle-navbar-spacer"></div>
            <div className="navbar-links">
                <button className="navbar-util-buttons" onClick={() => navigate("/flights")}>Flight Tracker</button>
                <button className="navbar-util-buttons">Reserve Tickets</button>
            </div>
        </div>
    );
}

export default Navbar;