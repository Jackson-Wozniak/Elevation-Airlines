import { createTheme, type Theme } from "@mui/material/styles";

export const AppTheme = (): Theme => createTheme({
    palette : {
        primary: {
            main: "#51a1daff",
            contrastText: "#ffffff"
        },
        secondary: {
            main: "#e2ee15",
            contrastText: "#797979"
        },
        mode: 'dark',
        background : {
            primary: "#0d1117",
            secondary: "#151b23",
            accent: "#2e2e2e"
        },
        text: {
            primary: "#ffffff",
            secondary: "#d0d0d0ff"
        }
    }
});