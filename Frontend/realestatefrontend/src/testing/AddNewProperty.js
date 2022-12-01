import {
  Button,
  createTheme,
  Grid,
  InputAdornment,
  MenuItem,
  MenuList,
  TextField,
  ThemeProvider,
  Typography,
} from "@mui/material";
import { Box, Stack } from "@mui/system";
import React, { useState } from "react";

import CircularProgressWithLabel from "../components/CircularProgressWithLabel";

const theme = createTheme({
  palette: {
    primary: {
      main: "#00e676",
    },
    secondary: {
      main: "#ff5252",
      border: "#00e676",
      save: "#69f0ae",
    },
  },
});

export default function AddNewProperty() {
  const [mainDetails, setmainDetails] = useState({
    propertyType: "",
    bhkType: "",
    floor: "",
    totalFloors: "",
    age: "",
    area: "",
  });
  const propertyTypes = ["Office", "Villa", "Apartment", "Gated Community"];
  const bhkTypes = ["1BHK", "2BHK", "3BHK", "4BHK", "4BHK+"];
  const ageTypes = [
    "Less than a year",
    "1 to 3 years",
    "3 to 5 years",
    "5 to 7 years",
    "7 to 10 years",
    "Greater than 10 years",
  ];
  const [progress, setprogress] = useState({ value: 27 });
  return (
    <Grid container spacing={1}>
      <Grid item xs={12} />
      <Grid item xs={12} />

      <Grid item xs={2} />
      <Grid item xs={8}>
        <Stack direction="row" spacing={50}>
          <Box />
          <Typography variant="h5">Property Details</Typography>
          <CircularProgressWithLabel {...progress} />
        </Stack>
      </Grid>
      <Grid item xs={2} />

      <Grid item xs={2} />
      <Grid item xs={2}>
        <Box>
          <ThemeProvider theme={theme}>
            <MenuList spacing={1}>
              <MenuItem disableRipple sx={{ "&:hover": { background: "none" } }}>
                <Button
                  variant="none"
                  sx={{
                    color: "background.main",
                    borderLeft: 4,
                    borderColor: "secondary.border",
                  }}
                >
                  Property Details
                </Button>
              </MenuItem>
              <MenuItem sx={{ "&:hover": { background: "none" } }}>
                <Button sx={{ ":hover": { boxShadow: 10 } }}>
                  Locality Details
                </Button>
              </MenuItem>
              <MenuItem sx={{ "&:hover": { background: "none" } }}>
                <Button sx={{ ":hover": { boxShadow: 10 } }}>
                  Rental Details
                </Button>
              </MenuItem>
              <MenuItem sx={{ "&:hover": { background: "none" } }}>
                <Button sx={{ ":hover": { boxShadow: 10 } }}>Amenities</Button>
              </MenuItem>
            </MenuList>
          </ThemeProvider>
        </Box>
      </Grid>
      <Grid item xs>
        <ThemeProvider theme={theme}>
          {console.log(mainDetails)}

          <Stack spacing={2}>
            <Box />

            <Box display="flex" justifyContent="center" alignItems="center">
              <Button
                variant="contained"
                disableRipple
                style={{ textTransform: "none" }}
                sx={{ bgcolor: "secondary.main" }}
              >
                All Fields are Required to be Filled
              </Button>
            </Box>
          </Stack>

          <Stack spacing={7}>
            <Box></Box>
            <TextField
              id="property-type"
              select
              label="Property Type"
              value={mainDetails.propertyType}
              onChange={(event) => {
                setmainDetails((prevState) => ({
                  ...prevState,
                  propertyType: event.target.value,
                }));
              }}
              style={{width:250 }}
              size="small"
            >
              {propertyTypes.map((type, index) => (
                <MenuItem key={index} value={type}>
                  {type}
                </MenuItem>
              ))}
            </TextField>

            <Stack direction="row" spacing={4}>
              <TextField
                id="bhk-type"
                select
                label="BHK"
                value={mainDetails.bhkType}
                onChange={(event) => {
                  setmainDetails((prevState) => ({
                    ...prevState,
                    bhkType: event.target.value,
                  }));
                }}
                style={{ minWidth: 250 }}
                size="small"
              >
                {bhkTypes.map((type, index) => (
                  <MenuItem key={index} value={type}>
                    {type}
                  </MenuItem>
                ))}
              </TextField>

              <Stack direction="row" spacing={2}>
                <TextField
                  id="floor"
                  label="Floor"
                  value={mainDetails.floor}
                  onChange={(event) => {
                    setmainDetails((prevState) => ({
                      ...prevState,
                      floor: event.target.value,
                    }));
                  }}
                  style={{ minWidth: 160 }}
                  size="small"
                ></TextField>

                <TextField
                  id="total-Floors"
                  label="Total Floors"
                  value={mainDetails.totalFloors}
                  onChange={(event) => {
                    setmainDetails((prevState) => ({
                      ...prevState,
                      totalFloors: event.target.value,
                    }));
                  }}
                  style={{ minWidth: 160 }}
                  size="small"
                ></TextField>
              </Stack>
            </Stack>

            <Stack direction="row" spacing={4}>
              <TextField
                id="age"
                select
                label="Age"
                value={mainDetails.age}
                onChange={(event) => {
                  setmainDetails((prevState) => ({
                    ...prevState,
                    age: event.target.value,
                  }));
                }}
                style={{ minWidth: 250 }}
                size="small"
              >
                {ageTypes.map((type, index) => (
                  <MenuItem key={index} value={type}>
                    {type}
                  </MenuItem>
                ))}
              </TextField>

              <TextField
                id="area"
                label="Built Up Area"
                value={mainDetails.area}
                onChange={(event) => {
                  setmainDetails((prevState) => ({
                    ...prevState,
                    area: event.target.value,
                  }));
                }}
                style={{ minWidth: 250 }}
                size="small"
                InputProps={{
                  endAdornment: (
                    <InputAdornment position="end">Sq.ft</InputAdornment>
                  ),
                }}
              ></TextField>
            </Stack>

            <Box display="flex" justifyContent="end" alignItems="center">
              <Button
                color="warning"
                variant="contained"
                style={{ textTransform: "none" }}
              >
                Back
              </Button>
              <Button
                color="success"
                variant="contained"
                style={{ textTransform: "none" }}
                sx={{ bgcolor: "secondary.save", m:3 }}
              >
                Save and Continue
              </Button>
            </Box>
          </Stack>
        </ThemeProvider>
      </Grid>
      <Grid item xs={2} />
    </Grid>
  );
}
