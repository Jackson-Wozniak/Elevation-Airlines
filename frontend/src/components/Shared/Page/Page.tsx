import Box from '@mui/material/Box';
import SideBar from './SideBar';
import ContentContainer from './ContentContainer';
import { useTheme, type Theme } from '@mui/material/styles';

const PageStyle = (theme: Theme) => { return {
    display: "flex",
    margin: 0,
    padding: 0,
    width: "100vw",
    height: "100vh",
    top: 0,
    left: 0,
    overflow: "hidden",
    backgroundColor: theme.palette.background.primary
}}

function Page({ children }: { children?: React.ReactNode }){
    const theme = useTheme();

    return (
        <Box sx={PageStyle(theme)}>
            <SideBar/>
            <ContentContainer>{children}</ContentContainer>
        </Box>
    )
}

export default Page;