import React from "react";
import {
  Accordion,
  AccordionSummary,
  AccordionDetails,
  Button,
  Typography,
  Card,
  Grid,
  CardContent,
  Box,
} from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";

type ApiReturnType = {
  name: string;
  type: string;
  sample: unknown;
};

type ApiEndpoint = {
  method: string;
  url: string;
  statusCode: number;
  parameters: Record<string, string>;
  requestBody?: unknown;
  returnType: string;
  returnSample: unknown;
};

type ApiDocsProps = {
  returnTypes: ApiReturnType[];
  endpoints: ApiEndpoint[];
};

function ApiEndpointSection({ returnTypes, endpoints }: ApiDocsProps) {
  return (
    <Box padding={4}>
      {/* Return Types Section */}
      <Accordion>
        <AccordionSummary expandIcon={<ExpandMoreIcon sx={{color: "white"}}/>} color={"white"} sx={{backgroundColor: "#181826", color: "white"}}>
          <Typography variant="h6">All Return Types</Typography>
        </AccordionSummary>
        <AccordionDetails sx={{backgroundColor: "#181826"}}>
          <Grid container spacing={2}>
            {returnTypes.map((rt, idx) => (
              <Grid key={idx}>
                <Card variant="outlined">
                  <CardContent>
                    <Typography variant="subtitle1" fontWeight="bold">
                      {rt.name}
                    </Typography>
                    <Typography variant="caption" color="text.secondary">
                      {rt.type}
                    </Typography>
                    <Box
                      component="pre"
                      sx={{
                        backgroundColor: "#f5f5f5",
                        padding: 2,
                        borderRadius: 1,
                        fontSize: "0.85rem",
                        overflowX: "auto",
                        marginTop: 1,
                      }}
                    >
                      {JSON.stringify(rt.sample, null, 2)}
                    </Box>
                  </CardContent>
                </Card>
              </Grid>
            ))}
          </Grid>
        </AccordionDetails>
      </Accordion>

      {/* Endpoints */}
      <Box mt={4}>
        {endpoints.map((ep, idx) => (
          <Accordion key={idx} sx={{backgroundColor: "#181826", color: "white"}}>
            <AccordionSummary expandIcon={<ExpandMoreIcon sx={{color: "white"}}/>} sx={{backgroundColor: "#181826"}}>
              <Box display="flex" gap={2} alignItems="center">
                <Button size="small" variant="contained">
                  {ep.method}
                </Button>
                <Button size="small" variant="text" sx={{ fontFamily: "monospace" }}>
                  {ep.url}
                </Button>
              </Box>
            </AccordionSummary>
            <AccordionDetails>
              <Box display="flex" flexDirection="column" gap={2}>
                <Box>
                  <Typography variant="subtitle2">Parameters:</Typography>
                  <ul style={{ paddingLeft: "1rem" }}>
                    {Object.entries(ep.parameters).map(([key, desc]) => (
                      <li key={key}>
                        <code>{key}</code>: {desc}
                      </li>
                    ))}
                  </ul>
                </Box>

                <Box>
                  <Typography variant="subtitle2">Returns:</Typography>
                  <Typography>Status Code: <code>{ep.statusCode}</code></Typography>
                  <Typography>Type: <code>{ep.returnType}</code></Typography>
                  <Box
                    component="pre"
                    sx={{
                      padding: 2,
                      borderRadius: 1,
                      fontSize: "0.85rem",
                      overflowX: "auto",
                      marginTop: 1,
                      backgroundColor: "#202036"
                    }}
                  >
                    {JSON.stringify(ep.returnSample, null, 2)}
                  </Box>
                </Box>
              </Box>
            </AccordionDetails>
          </Accordion>
        ))}
      </Box>
    </Box>
  );
};

export default ApiEndpointSection;