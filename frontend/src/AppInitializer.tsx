import { createContext, useContext } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ThemeProvider } from '@mui/material/styles';
import { AppTheme } from './theme/AppTheme';
import FlightDashboard from './components/pages/FlightDashboard/FlightDashboard';
import AdminDashboard from './components/pages/AdminDashboard/AdminDashboard';

export const DemoModeContext = createContext<boolean>(false);
export const useDemoMode = () => useContext(DemoModeContext);

function AppInitializer(){  
    return (
        <ThemeProvider theme={AppTheme}>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<FlightDashboard />} />
                    <Route path="/admin" element={<AdminDashboard/>}/>
                </Routes>
            </BrowserRouter>
        </ThemeProvider>
    );
};

export default AppInitializer;