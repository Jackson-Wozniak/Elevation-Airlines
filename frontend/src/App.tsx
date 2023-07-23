import './styles/App.css';
import { Routes, Route } from 'react-router-dom';
import LandingPage from './pages/LandingPage';
import FlightPage from './pages/FlightPage';

function App() {
  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/flights" element={<FlightPage />} />
    </Routes>
  );
}

export default App;
