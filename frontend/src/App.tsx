import { Routes, Route } from "react-router-dom";
import DocumentationPage from "./pages/DocumentationPage";
import './styles/shared.css'
import HomePage from "./pages/HomePage";

function App() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="docs" element={<DocumentationPage />} />
    </Routes>
  )
}

export default App
