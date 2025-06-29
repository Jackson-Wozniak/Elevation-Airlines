import { Routes, Route } from "react-router-dom";
import DocumentationPage from "./pages/DocumentationPage";
import './styles/shared.css'
import FlightDashboard from "./pages/FlightDashboard";
import FlightDetailsPage from "./pages/FlightDetailsPage";

function App() {
  return (
    <Routes>
      <Route path="/" element={<FlightDashboard />} />
      <Route path="flights/:flightId" element={<FlightDetailsPage />} />
      <Route path="docs" element={<DocumentationPage />} />
    </Routes>
  )
}

export default App
