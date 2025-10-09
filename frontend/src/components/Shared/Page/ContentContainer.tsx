import Box from '@mui/material/Box';
import '../../../styles/shared.css';

const ContentContainerStyle = {
    minWidth: "78%",
    maxWidth: "78%",
    minHeight: "100%",
    margin: 0,
    padding: 0,
    overflowY: "auto",
    scrollbarWidth: "thin",
    scrollbarColor: "rgba(255, 255, 255, 0.2) transparent",
}

function ContentContainer({ children }: { children?: React.ReactNode }){
    return (
        <Box sx={ContentContainerStyle}>
            {children}
        </Box>
    )
}

export default ContentContainer;