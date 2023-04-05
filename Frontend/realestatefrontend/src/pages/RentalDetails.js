import {
  Box,
  Button,
  createTheme,
  FormControl,
  FormControlLabel,
  FormLabel,
  Grid,
  InputAdornment,
  MenuItem,
  Radio,
  RadioGroup,
  Stack,
  TextField,
  ThemeProvider,
  Typography,
} from "@mui/material";
import React from "react";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import CircularProgressWithLabel from "../components/CircularProgressWithLabel";
import DetailsOptons from "../components/DetailsOptons";
import { rentalDetailsUpdate } from "../redux/features/propDetails/propDetailsSlice";
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

export default function RentalDetails() {
  const dispatch = useDispatch();
  const dateNow = new Date();
  const year = dateNow.getFullYear();
  const monthWithOffset = dateNow.getUTCMonth() + 1;
  const month =
    monthWithOffset.toString().length < 2
      ? `0${monthWithOffset}`
      : monthWithOffset;
  const date =
    dateNow.getUTCDate().toString().length < 2
      ? `0${dateNow.getUTCDate()}`
      : dateNow.getUTCDate();
  const currentDate = `${year}-${month}-${date}`;
  const initialState = {
    availablity: "Only Rent",
    rent: "",
    deposit: "",
    availableFrom: currentDate,
    preference: "",
    parking: "",
    furnishing: "",
    negotiable: "No",
  };
  const propertyDetails = useSelector((state) => state.propDetails);
  const [rentalDetails, setRentalDetails] = useState((propertyDetails) =>
    propertyDetails.rentalDetails === {}
      ? initialState
      : propertyDetails.rentalDetails
  );
  const preferenceList = ["All", "Bachelor", "Family"];
  const parkingOptions = ["Not Available", "Available"];
  const furnishingStatus = ["Unfurnished", "Semi-Furnished", "Fully Furnished"];

  const handleAvailability = (event) => {
    setRentalDetails((prevState) => ({
      ...prevState,
      availablity: event.target.value,
    }));
  };

  const handleNegotiability = (event) => {
    setRentalDetails((prevState) => ({
      ...prevState,
      negotiable: event.target.value,
    }));
  };

  return (
    <Grid container spacing={1}>
      <Grid item xs={12} />
      <Grid item xs={12} />

      <Grid item xs={2} />
      <Grid item xs={8}>
        <Stack direction="row" spacing={50}>
          <Box />
          <Typography variant="h5">Rental Details</Typography>
        </Stack>
      </Grid>
      <Grid item xs={2} />

      <Grid item xs={2} />
      <Grid item xs={2}>
        <Box>
          <DetailsOptons Option={"Rental Details"} />
        </Box>
      </Grid>
      <Grid item xs>
        <ThemeProvider theme={theme}>
          {console.log(rentalDetails)}

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
            <FormControl required>
              <FormLabel id="radio-buttons-group-label1">
                Property Available for
              </FormLabel>
              <RadioGroup
                aria-labelledby="radio-buttons-group-label1"
                defaultValue="Only Rent"
                name="radio-buttons-group1"
                row
              >
                <FormControlLabel
                  value="Only Rent"
                  control={<Radio />}
                  label="Only Rent"
                  onChange={handleAvailability}
                />
                <FormControlLabel
                  value="Only Lease"
                  control={<Radio />}
                  label="Only Lease"
                  onChange={handleAvailability}
                />
              </RadioGroup>
            </FormControl>

            <Stack direction="row" spacing={4}>
              <TextField
                id="rent"
                label="Expected Rent"
                required
                value={rentalDetails.rent}
                onChange={(event) => {
                  setRentalDetails((prevState) => ({
                    ...prevState,
                    rent: event.target.value,
                  }));
                }}
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start">₹</InputAdornment>
                  ),
                  endAdornment: (
                    <InputAdornment position="end">/Month </InputAdornment>
                  ),
                }}
                style={{ minWidth: 250 }}
                size="small"
              />

              <TextField
                id="rent"
                label="Expected Deposit"
                required
                value={rentalDetails.deposit}
                onChange={(event) => {
                  setRentalDetails((prevState) => ({
                    ...prevState,
                    deposit: event.target.value,
                  }));
                }}
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start">₹</InputAdornment>
                  ),
                }}
                style={{ minWidth: 250 }}
                size="small"
              />
            </Stack>

            <Stack direction="row" spacing={4}>
              <TextField
                type="date"
                label="Available From"
                required
                value={rentalDetails.availableFrom}
                data-testid="date"
                onChange={(event) => {
                  setRentalDetails((prevState) => ({
                    ...prevState,
                    availableFrom: event.target.value,
                  }));
                }}
                InputProps={{
                  inputProps: {
                    min: currentDate,
                    max: "2050-05-11",
                    tabIndex: -1,
                  },
                }}
                style={{ minWidth: 250 }}
                size="small"
              />

              <TextField
                id="preference"
                select
                label="Preferred Tenants"
                required
                value={rentalDetails.preference}
                onChange={(event) => {
                  setRentalDetails((prevState) => ({
                    ...prevState,
                    preference: event.target.value,
                  }));
                }}
                style={{ minWidth: 250 }}
                size="small"
              >
                {preferenceList.map((type, index) => (
                  <MenuItem key={index} value={type}>
                    {type}
                  </MenuItem>
                ))}
              </TextField>
            </Stack>

            <Stack direction="row" spacing={4}>
              <TextField
                id="furnishing"
                select
                label="Furnishing"
                required
                value={rentalDetails.furnishing}
                onChange={(event) => {
                  setRentalDetails((prevState) => ({
                    ...prevState,
                    furnishing: event.target.value,
                  }));
                }}
                style={{ minWidth: 250 }}
                size="small"
              >
                {furnishingStatus.map((type, index) => (
                  <MenuItem key={index} value={type}>
                    {type}
                  </MenuItem>
                ))}
              </TextField>

              <TextField
                id="parking"
                select
                label="Parking Options"
                required
                value={rentalDetails.parking}
                onChange={(event) => {
                  setRentalDetails((prevState) => ({
                    ...prevState,
                    parking: event.target.value,
                  }));
                }}
                style={{ minWidth: 250 }}
                size="small"
              >
                {parkingOptions.map((type, index) => (
                  <MenuItem key={index} value={type}>
                    {type}
                  </MenuItem>
                ))}
              </TextField>

              <FormControl required>
                <FormLabel id="radio-buttons-group-label2">
                  Negotiable?
                </FormLabel>
                <RadioGroup
                  aria-labelledby="radio-buttons-group-label2"
                  defaultValue="No"
                  name="radio-buttons-group2"
                  row
                >
                  <FormControlLabel
                    value="No"
                    control={<Radio />}
                    label="No"
                    onChange={handleNegotiability}
                  />
                  <FormControlLabel
                    value="Yes"
                    control={<Radio />}
                    label="Yes"
                    onChange={handleNegotiability}
                  />
                </RadioGroup>
              </FormControl>
            </Stack>

            <Box display="flex" justifyContent="end" alignItems="center">
              <Link to={"/localityDetails"}>
                <Button
                  color="warning"
                  variant="contained"
                  style={{ textTransform: "none" }}
                >
                  Back
                </Button>
              </Link>
              <Link to={"/amenitiesDetails"}>
                <Button
                  color="success"
                  variant="contained"
                  type="submit"
                  onSubmit={() => {
                    dispatch(rentalDetailsUpdate(rentalDetails));
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
