import '@mui/material/styles';

declare module '@mui/material/styles' {
    interface TypeBackground {
        primary?: string;
        secondary?: string;
        accent?: string;
    }
    interface Theme {
        display: 'desktop' | 'mobile';
        width: number;
        height: number;
    }
    interface ThemeOptions {
        display?: 'desktop' | 'mobile';
        width?: number;
        height?: number;
    }
}