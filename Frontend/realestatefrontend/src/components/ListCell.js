import { Box, Stack, TextField, Typography } from "@mui/material";
import React from "react";
import { Link } from "react-router-dom";

export default function ListCell({ props }) {
  const boxStyle = {
    bgcolor: "background.color",
    border: 1,
    borderColor: "gray",
    ":hover": {
      boxShadow: " 0 5px 5px crimson",
    },
    borderRadius: "7px",
    minHeight: "250px",
  };

  return (
    <Box sx={{ ...boxStyle }}>
      <Link to={`/property/${props.propData.propId}`}>
        <Box
          component="img"
          sx={{
            display: "inline-block",
            maxHeight: 190,
            maxWidth: 250,
            margin: 4,
          }}
          alt="The house"
          src={props.propData.images[0]}
        />
        <Box sx={{ display: "inline-block", marginLeft: 35 }}>
          <Stack spacing={2} direction="row">
            <Typography variant="h4" sx={{ textDecoration: "underline" }}>
              {props.propData.mainDetails.type} |{" "}
              {props.propData.mainDetails.roomType} Floor -{" "}
              {props.propData.mainDetails.floor} | Area -{" "}
              {props.propData.mainDetails.area}
            </Typography>
            <Typography variant="h4">
              Near - {props.propData.localityDetails.location}
            </Typography>
          </Stack>
        </Box>
        <Box
          sx={{
            bgcolor: "#ffd700",
            borderColor: "gray",
            marginTop: 1,
            marginRight: 1,
            marginBottom: 1,
            minHeight: "220px",
            minWidth: "220px",
            display: "inline-block",
            boxShadow: 3,
          }}
        ></Box>
      </Link>
    </Box>
  );
}
