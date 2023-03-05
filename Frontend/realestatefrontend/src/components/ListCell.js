import { Box } from "@mui/material";
import React from "react";

export default function ListCell() {
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
      <Box
        component="img"
        sx={{
          display: "inline-block",
          maxHeight: 190,
          maxWidth: 250,
          margin:4
        }}
        alt="The house from the offer."
        src="https://images.unsplash.com/photo-1512917774080-9991f1c4c750?auto=format&w=350&dpr=2"
      />
      <Box sx={{ display: "inline-block", marginLeft: 35 }} />
      <Box
        sx={{
          bgcolor: "#ffd700",
          borderColor: "gray",
          marginTop:1,
          marginRight: 1,
          marginBottom:1,
          minHeight: "220px",
          minWidth: "220px",
          display: "inline-block",
          boxShadow: 3,
        }}
      ></Box>
    </Box>
  );
}
