import { AppBar, Autocomplete, Box, Button, createTheme, Grid, InputAdornment, MenuItem, MenuList, Stack, TextField, ThemeProvider, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react'
import LocationOnIcon from '@mui/icons-material/LocationOn';
import "leaflet-control-geocoder/dist/Control.Geocoder.css";
import "leaflet-control-geocoder/dist/Control.Geocoder";
import L from 'leaflet'

import CircularProgressWithLabel from '../components/CircularProgressWithLabel';
import { MapContainer, Marker, Popup, TileLayer, useMap, useMapEvents } from 'react-leaflet';


const theme = createTheme({
    palette: {
        primary: {
            main: "#00e676",
          },
        secondary: {
            main: "#ff5252",
            border: "#00e676",
            save: "#69f0ae"
        },
    }
  });  

  const initialState = {'city':'', 'locality':'', 'landmark': '', 'pincode' : '', 'location' : ''};

export default function LocalityDetails() {
    const [localityDetails, setlocalityDetails] = useState(initialState);
    const cities = ['Pune', 'Mumbai', 'Kochi', 'Delhi', 'Gurgaon', 'Hyderabad', 'Bangalore', 'Indore', 'Chennai', 'Ahemdabad']
    const [progress, setprogress] = useState({'value' : 27});
    const [marker, setMarker] = useState({'lat': '', 'lng': ''});
    const [showMarker, setshowMarker] = useState(false);
    const [search, setSearch] = useState('');
    // 51.50, 'lng': -0.1});

    const DisplayMarker = () =>{
      const map = useMap();
      if(showMarker){
        map.flyTo(marker, 11);
        return (
          <Marker position={[marker.lat, marker.lng]}>
            <Popup>
              <span>
                A pretty CSS3 popup. <br /> Easily customizable.
              </span>
            </Popup>
          </Marker>
        )
      }
    }
    const MapContent = () => {  
      const map = useMapEvents({
        click: (e) => {
          setshowMarker(true)
          setMarker(e.latlng);
          // DisplayMarker();
        }
      });
      return null;
    }

    const SetView = () =>{
      const map = useMap();
      if(marker.lat === '' && marker.lng === '') map.setView([22.3511148, 78.6677428], 4)
    }

    useEffect(() =>{
      const geocoder = L.Control.Geocoder.nominatim();
      if(search){
        geocoder.geocode(search, (results) => {
          //console.log(results);
          var r = results[0];
          if (r) {
            const location = r?.center;
            console.log("Location =>", location)
            if(location){ 
              setshowMarker(true);
              setMarker(location)
          }
            //console.log(r);
          }
        });
      }
    }, [search])

  return (
    <Grid container spacing={1}>
      <Grid item xs={12} />
      <Grid item xs={12} />

      <Grid item xs={2} />
      <Grid item xs={8}>
        <Stack direction='row' spacing={50}>
            <Box />
            <Typography variant="h5">Locality Details</Typography>
            <CircularProgressWithLabel {...progress}/>
        </Stack>
      </Grid>
      <Grid item xs={2} />

      <Grid item xs={2} />
      <Grid item xs={2}>
        <Box>
          <ThemeProvider theme={theme}>
          <MenuList spacing={1}>
            <MenuItem sx={{ "&:hover": { background: "none" } }}>
              <Button sx={{ ":hover": { boxShadow: 10 } }}>
                Property Details
              </Button>
            </MenuItem>
            <MenuItem disableRipple sx={{ "&:hover": { background: "none" } }}>
              <Button  
                variant="none"
                sx={{
                  color: "background.main",
                  borderLeft: 4,
                  borderColor: "secondary.border",
                }}>
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
            {console.log(localityDetails)}

            <Stack spacing={2}>
                <Box />

                <Box display="flex" justifyContent="center" alignItems="center">
                <Button
                variant="contained"
                disableRipple
                style={{ textTransform: "none" }}
                sx={{bgcolor:"secondary.main"}}
                >
                All Fields are Required to be Filled
                </Button>
            </Box>

            </Stack>

            <Stack spacing={7}>
            <Box></Box>

            <Stack direction="row" spacing={13}>
              <Autocomplete
                freeSolo
                value={localityDetails.city}
                onChange={(event, value) => {
                  setlocalityDetails(() => ({
                  ...initialState,
                  city: value
                  }));
                  setSearch(value)
                }}
                disablePortal
                id="city"
                options={cities}
                sx={{ width: 250 }}
                renderInput={(params) => <TextField {...params} label="City" />}
                size="small"
              />
      
              <TextField
              id="locality"
              label="Locality"
              value={localityDetails.locality}
              placeholder="Eg. Job Hunt Nagar.."
              onChange={(event) => {
                  setlocalityDetails((prevState) => ({
                  ...initialState,
                  city: prevState.city,
                  locality: event.target.value,
                  }));
                  setSearch((event.target.value + ", " + localityDetails.city))
              }}
              style={{ minWidth: 250 }}
              size="small"
              InputProps={{
                  startAdornment: (
                  <InputAdornment position="start">
                      <LocationOnIcon />
                  </InputAdornment>
                  ),
              }}
              ></TextField>

            </Stack>
            
            <Stack direction="row" spacing={13}>
          
              <TextField
                  id="pincode"
                  label="Pincode"
                  value={localityDetails.pincode}
                  onChange={(event) => {
                    setlocalityDetails((prevState) => ({
                      ...prevState,
                      pincode: event.target.value,
                    }));
                    setSearch((event.target.value + ", " + localityDetails.locality + ", " + localityDetails.city))
                  }}
                  style={{ width: 250 }}
                  size="small"
              ></TextField>

                <TextField
                  id="landmark"
                  label="Landmark/Street Name"
                  value={localityDetails.landmark}
                  placeholder="Eg. Please Hire Me Street.."
                  onChange={(event) => {
                    setlocalityDetails((prevState) => ({
                      ...prevState,
                      landmark: event.target.value,
                    }));
                  }}
                  style={{ width: 250 }}
                  size="small"
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
                sx={{ bgcolor: "secondary.save", m:1 }}
              >
                Save and Continue
              </Button>
            </Box>
            
            </Stack>

            <Box sx={{ maxWidth:800}}>
            <TextField
                id="searchIndex"
                label="Search"
                value={search}
                onChange={(event) => {setSearch(event.target.value)}}
                style={{ width: 250, margin:10 }}
                size="small"
            ></TextField>
            <AppBar position="sticky">
              <div style={{ height: "50vh" }}>
                <MapContainer
                  center={[marker.lat,  marker.lng]}
                  zoom={10}
                  scrollWheelZoom={true}
                >
                  <TileLayer
                    attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                  />
                  <MapContent/>
                  <SetView />
                  {console.log(marker)}
                  <DisplayMarker />
                </MapContainer>
              </div>
            </AppBar>
            </Box>
        </ThemeProvider>
      </Grid>
      <Grid item xs={2} />
    </Grid>
  )
}
