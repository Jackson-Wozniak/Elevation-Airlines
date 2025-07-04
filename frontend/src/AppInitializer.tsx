import { createContext, useContext, useEffect, useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import FlightDashboard from './pages/FlightDashboard';
import FlightDetailsPage from './pages/FlightDetailsPage';
import DocumentationPage from './pages/DocumentationPage';
import { isServerRunning } from './shared-functions/FlightHttpClient';

export const DemoModeContext = createContext<boolean>(false);
export const useDemoMode = () => useContext(DemoModeContext);

function AppInitializer(){
    const [isDemoMode, setIsDemoMode] = useState<boolean>(false);
    const [initialized, setInitialized] = useState<boolean>(false);

    useEffect(() => {
        const pingServerOnStartup = async () => {
            let serverIsRunning: boolean = await isServerRunning();
            setIsDemoMode(!serverIsRunning); //if server is running we don't use demo mode
        }
        pingServerOnStartup().then(() => setInitialized(true));
    }, []);
  
    if (!initialized) {
        return <div>Loading...</div>;
    }
  
    return (
        <DemoModeContext.Provider value={isDemoMode}>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<FlightDashboard />} />
                <Route path="flights/:flightId" element={<FlightDetailsPage />} />
                <Route path="docs" element={<DocumentationPage />} />
            </Routes>
        </BrowserRouter>
      </DemoModeContext.Provider>
    );
};

export default AppInitializer;