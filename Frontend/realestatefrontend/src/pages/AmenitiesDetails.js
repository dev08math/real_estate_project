import {
  Box,
  Button,
  Checkbox,
  createTheme,
  FormControl,
  FormControlLabel,
  FormGroup,
  FormLabel,
  Grid,
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
import { amenitiesDetailsUpdate } from "../redux/features/propDetails/propDetailsSlice";
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

export default function AmenitiesDetails() {
  const dispatch = useDispatch();
  const initialState = {
    bathrooms: "",
    balcony: "",
    security: false,
    gym: false,
    amenities: [],
  };
  const propertyDetails = useSelector((state) => state.propDetails);
  const [amenitiesDetails, setAmenitiesDetails] = useState(initialState);

  const handleGymAvailability = (event) => {
    setAmenitiesDetails((prevState) => ({
      ...prevState,
      gym: event.target.value,
    }));
  };

  const handleSecurity = (event) => {
    setAmenitiesDetails((prevState) => ({
      ...prevState,
      security: event.target.value,
    }));
  };

  const handleAmenitiesList = (event) => {
    var index = amenitiesDetails.amenities.indexOf(event.target.name);
    if (index === -1) amenitiesDetails.amenities.push(event.target.name);
    else amenitiesDetails.amenities.splice(index);
    setAmenitiesDetails((prevState) => ({
      ...prevState,
      amenities: amenitiesDetails.amenities,
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
          <Typography variant="h6">Amenities Details</Typography>
        </Stack>
      </Grid>
      <Grid item xs={2} />

      <Grid item xs={2} />
      <Grid item xs={2}>
        <Box>
          <DetailsOptons Option={"Amenities"} />
        </Box>
      </Grid>
      <Grid item xs>
        <ThemeProvider theme={theme}>
          {console.log(amenitiesDetails)}

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

          <Stack spacing={6}>
            <Box></Box>
            <Stack direction="row" spacing={7}>
              <FormControl required>
                <FormLabel id="radio-buttons-group-label1">Gym?</FormLabel>
                <RadioGroup
                  aria-labelledby="radio-buttons-group-label1"
                  defaultValue={amenitiesDetails.gym}
                  name="radio-buttons-group1"
                  row
                >
                  <FormControlLabel
                    value={true}
                    control={<Radio />}
                    label="Yes"
                    onChange={handleGymAvailability}
                  />
                  <FormControlLabel
                    value={false}
                    control={<Radio />}
                    label="No"
                    onChange={handleGymAvailability}
                  />
                </RadioGroup>
              </FormControl>

              <FormControl required>
                <FormLabel id="radio-buttons-group-label2">Security?</FormLabel>
                <RadioGroup
                  aria-labelledby="radio-buttons-group-label2"
                  defaultValue={amenitiesDetails.security}
                  name="radio-buttons-group2"
                  row
                >
                  <FormControlLabel
                    value={true}
                    control={<Radio />}
                    label="Yes"
                    onChange={handleSecurity}
                  />
                  <FormControlLabel
                    value={false}
                    control={<Radio />}
                    label="No"
                    onChange={handleSecurity}
                  />
                </RadioGroup>
              </FormControl>
            </Stack>

            <Stack direction="row" spacing={4}>
              <TextField
                id="bathrooms"
                label="Bathrooms"
                required
                value={amenitiesDetails.bathrooms}
                onChange={(event) => {
                  setAmenitiesDetails((prevState) => ({
                    ...prevState,
                    bathrooms: event.target.value,
                  }));
                }}
                style={{ minWidth: 250 }}
                size="small"
              />

              <TextField
                id="balcony"
                label="Balconies"
                required
                value={amenitiesDetails.balcony}
                onChange={(event) => {
                  setAmenitiesDetails((prevState) => ({
                    ...prevState,
                    balcony: event.target.value,
                  }));
                }}
                style={{ minWidth: 250 }}
                size="small"
              />
            </Stack>

            <FormControl>
              <FormLabel id="amenities-list">
                <Typography variant="h6">
                  Select all the available amenities
                </Typography>
              </FormLabel>
              <FormGroup aria-labelledby="amenities-list" name="group1" row>
                <Stack spacing={8} direction="row">
                  <FormControlLabel
                    control={
                      <Checkbox
                        checked={amenitiesDetails.amenities.includes("List")}
                        onChange={handleAmenitiesList}
                        name="Lift"
                      />
                    }
                    label="Lift"
                  />
                  <FormControlLabel
                    control={
                      <Checkbox
                        checked={amenitiesDetails.amenities.includes(
                          "Internet Services"
                        )}
                        onChange={handleAmenitiesList}
                        name="Internet Services"
                      />
                    }
                    label="Internet Services"
                  />

                  <FormControlLabel
                    control={
                      <Checkbox
                        checked={amenitiesDetails.amenities.includes(
                          "Air Conditioner"
                        )}
                        onChange={handleAmenitiesList}
                        name="Air Conditioner"
                      />
                    }
                    label="Air Conditioner"
                  />
                  <FormControlLabel
                    control={
                      <Checkbox
                        checked={amenitiesDetails.amenities.includes(
                          "Intercom"
                        )}
                        onChange={handleAmenitiesList}
                        name="Intercom"
                      />
                    }
                    label="Intercom"
                  />
                </Stack>
              </FormGroup>

              <FormGroup aria-labelledby="amenities-list" name="group2" row>
                <Stack spacing={8} direction="row">
                  <FormControlLabel
                    control={
                      <Checkbox
                        checked={amenitiesDetails.amenities.includes(
                          "Fire Safety"
                        )}
                        onChange={handleAmenitiesList}
                        name="Fire Safety"
                      />
                    }
                    label="Fire Safety"
                  />
                  <FormControlLabel
                    control={
                      <Checkbox
                        checked={amenitiesDetails.amenities.includes("Park")}
                        onChange={handleAmenitiesList}
                        name="Park"
                      />
                    }
                    label="Park"
                  />
                  <FormControlLabel
                    control={
                      <Checkbox
                        checked={amenitiesDetails.amenities.includes(
                          "Visitor Parking"
                        )}
                        onChange={handleAmenitiesList}
                        name="Visitor Parking"
                      />
                    }
                    label="Visitor Parking"
                  />
                  <FormControlLabel
                    control={
                      <Checkbox
                        checked={amenitiesDetails.amenities.includes(
                          "Gas Pipeline"
                        )}
                        onChange={handleAmenitiesList}
                        name="Gas Pipeline"
                      />
                    }
                    label="Gas Pipeline"
                  />
                </Stack>
              </FormGroup>
            </FormControl>

            <Box display="flex" justifyContent="end" alignItems="center">
              <Link to={"/rentalDetails"}>
                <Button
                  color="warning"
                  variant="contained"
                  style={{ textTransform: "none" }}
                >
                  Back
                </Button>
              </Link>
                <Link to={"/imageDetails"}>
                  <Button
                    color="success"
                    variant="contained"
                    type="submit"
                    onSubmit={() => {
                      dispatch(amenitiesDetailsUpdate(amenitiesDetails));
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
