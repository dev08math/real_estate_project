import {
  Button,
  createTheme,
  Grid,
  InputAdornment,
  MenuItem,
  TextField,
  ThemeProvider,
  Typography,
} from "@mui/material";
import { Box, Stack } from "@mui/system";
import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import CircularProgressWithLabel from "../components/CircularProgressWithLabel";
import DetailsOptons from "../components/DetailsOptons";
import { mainDetailsUpdate } from "../redux/features/propDetails/propDetailsSlice";
import { Link } from "react-router-dom";

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

export default function MainDetails() {
  const dispatch = useDispatch();
  const propertyDetails = useSelector((state) => state.propDetails);
  const [mainDetails, setmainDetails] = useState((propertyDetails) =>
    propertyDetails.mainDetails === {}
      ? {
          propertyType: "",
          bhkType: "",
          floor: "",
          totalFloors: "",
          age: "",
          area: "",
        }
      : propertyDetails.mainDetails
  );
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

  return (
    <Grid container spacing={1}>
      <Grid item xs={12} />
      <Grid item xs={12} />

      <Grid item xs={2} />
      <Grid item xs={8}>
        <Stack direction="row" spacing={50}>
          <Box />
          <Typography variant="h5">Property Details</Typography>
        </Stack>
      </Grid>
      <Grid item xs={2} />

      <Grid item xs={2} />
      <Grid item xs={2}>
        <Box>
          <DetailsOptons Option={"Property Details"} />
        </Box>
      </Grid>
      <Grid item xs>
        <ThemeProvider theme={theme}>
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
              required
              label="Property Type"
              value={mainDetails.propertyType}
              onChange={(event) => {
                setmainDetails((prevState) => ({
                  ...prevState,
                  propertyType: event.target.value,
                }));
              }}
              style={{ width: 250 }}
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
                required
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
                  required
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
                  required
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
                required
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
                required
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
              <Link to={"/localityDetails"}>
                <Button
                  color="success"
                  variant="contained"
                  type="submit"
                  onSubmit={() => {
                    dispatch(mainDetailsUpdate(mainDetails));
                  }}
                  style={{ textTransform: "none" }}
                  sx={{ bgcolor: "secondary.save", m: 3 }}
                >
                  Save and Continue
                </Button>
              </Link>
            </Box>
          </Stack>
        </ThemeProvider>
      </Grid>
      <Grid item xs={2} />
    </Grid>
  );
}
