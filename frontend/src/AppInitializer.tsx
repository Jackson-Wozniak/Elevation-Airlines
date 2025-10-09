import { createContext, useContext } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import FlightDashboard from './components/FlightDashboard/FlightDashboard';
import { ThemeProvider } from '@mui/material/styles';
import { AppTheme } from './theme/AppTheme';

export const DemoModeContext = createContext<boolean>(false);
export const useDemoMode = () => useContext(DemoModeContext);

function AppInitializer(){  
    return (
        <ThemeProvider theme={AppTheme}>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<FlightDashboard />} />
                </Routes>
            </BrowserRouter>
        </ThemeProvider>
    );
};

export default AppInitializer;