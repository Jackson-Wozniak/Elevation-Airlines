import { useTheme } from "@mui/material";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";

export const DataCard: React.FC<{
    header: string,
    value: number,
    accentColor: string
}> = ({header, value, accentColor}) => {
    const theme = useTheme();

    return (
        <Box sx={{display: "flex", flexDirection: "column",
            backgroundColor: theme.palette.background.accent, height: "100px", minWidth: "140px",
            alignItems: "flexStart", justifyContent: "flexStart", textAlign: "left",
            border: `1px solid ${accentColor}`, borderRadius: "5px", padding: "2px 5px 0px 5px"
        }}>
            <Typography sx={{color: theme.palette.text.primary}}>{header}</Typography>
            <Typography sx={{color: accentColor}}>{value}</Typography>
        </Box>
    )
}

export default DataCard;