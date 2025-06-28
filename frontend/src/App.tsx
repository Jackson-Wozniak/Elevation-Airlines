import { Routes, Route } from "react-router-dom";
import DocumentationPage from "./pages/DocumentationPage";
import './styles/shared.css'
import FlightDashboard from "./pages/FlightDashboard";

function App() {
  return (
    <Routes>
      <Route path="/" element={<FlightDashboard />} />
      <Route path="docs" element={<DocumentationPage />} />
    </Routes>
  )
}

export default App
