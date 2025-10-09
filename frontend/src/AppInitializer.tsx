import { createContext, useContext } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ThemeProvider } from '@mui/material/styles';
import { AppTheme } from './theme/AppTheme';
import FlightListingsPage from './components/FlightListings/FlightListingsPage';

export const DemoModeContext = createContext<boolean>(false);
export const useDemoMode = () => useContext(DemoModeContext);

function AppInitializer(){  
    return (
        <ThemeProvider theme={AppTheme}>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<FlightListingsPage />} />
                </Routes>
            </BrowserRouter>
        </ThemeProvider>
    );
};

export default AppInitializer;