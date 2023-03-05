import { Grid, Stack, Typography } from "@mui/material";
import { Box } from "@mui/material";
import React, { useState } from "react";
import Filter from "../components/Filter";
import ListCell from "../components/ListCell";
import SearchBar from "../components/SearchBar";
import SortCom from "../components/SortCom";
import SearchbarTest from "../testing/SearchbarTest";

export default function Listings() {
  // filter states
  const [propertyFilter, setpropertyFilter] = useState([]);
  const [bhkFilter, setbhkFilter] = useState({});
  const [rentFilter, setrentFilter] = useState([]);
  const [availabilityFilter, setavailabilityFilter] = useState("");
  const [floorsFilter, setfloorsFilter] = useState(0);
  const [ageFilter, setageFilter] = useState("");
  const [furnishingFilter, setfurnishingFilter] = useState("");
  const [photosFilter, setphotosFilter] = useState(false);

  const [propList, setpropList] = useState([]);

  const boxStyle = {
    bgcolor: "crimson",
    borderRadius: "7px",
  };

  console.log("Proplist ", propList);
  return (
    <Grid container spacing={2}>
      <Grid item xs={12} style={{ display: "flex", justifyContent: "center" }}>
        <Box
          sx={{
            bgcolor: "crimson",
            borderRadius: "7px",
            width: "1100px",
            maxHeight: "70px",
            marginTop: 4,
            display: "inline-block",
          }}
        >
          <Box
            sx={{
              bgcolor: "white",
              borderRadius: "7px",
              width: "400px",
              maxHeight: "60px",
              marginLeft: 42,
              marginTop: 1,
              marginBottom: 1,
              display: "inline-block",
            }}
          >
            <SearchBar propList={propList} setpropList={setpropList} />
          </Box>
        </Box>
      </Grid>

      <Grid item xs={3} style={{ display: "flex", justifyContent: "flex-end" }}>
        <Filter
          setFilters={[
            setpropertyFilter,
            setbhkFilter,
            setrentFilter,
            setfurnishingFilter,
            setageFilter,
            setphotosFilter,
          ]}
        />
      </Grid>

      <Grid item xs={7}>
        <Stack spacing={2}>
          <Box
            sx={{ bgcolor: "crimson", height: "80px", borderRadius: "10px" }}
          >
            <Box sx={{ display: "flex", justifyContent: "center" }}>
              <Typography sx={{color:'white', textTransform: "none" }}>
                Showing {propList.length} results
              </Typography>
            </Box>
            <Box
              sx={{
                display: "flex",
                justifyContent: "flex-end",
                marginRight: 7,
              }}
            >
              <SortCom propList={propList} setpropList={setpropList} />
            </Box>
          </Box>
          {/* <Box sx={{ bgcolor: "blue", ":hover": { boxShadow: 15 } }}> wo</Box> */}
          <Stack spacing={5}  style={{maxHeight:'500px', overflow:'auto'}}>
            <ListCell />
            <ListCell />
            <ListCell />
          </Stack>
        
        </Stack>
      </Grid>

      <Grid item xs={2}>
        <Box sx={{ bgcolor: "orange" }}> lo</Box>
      </Grid>
    </Grid>
  );
}
