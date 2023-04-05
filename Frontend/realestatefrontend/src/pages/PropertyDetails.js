import { Grid, Box, Typography, AppBar } from "@mui/material";
import { Stack } from "@mui/system";
import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import { useDispatch, useSelector } from "react-redux";
import DetailsComponent from "../components/DetailsComponent";
import { MapContainer, Marker, TileLayer, useMap } from "react-leaflet";
import { ChatBubble } from "@mui/icons-material";
import { useParams } from "react-router-dom";
import { getPropertyDetails } from "../redux/features/proplist/actions";
import { useEffect } from "react";

export default function PropertyDetails() {
  const userLogin = useSelector((state) => state.userLogin);
  const { error, loading, userInfo } = userLogin;
  const propData = useSelector((state) => state.propData);
  const { propError, propLoading, propDetails } = propData;
  const propertyParams = useParams();
  const propId = propertyParams.id;
  const dispatch = useDispatch();
  const boxStyle = {
    bgcolor: "background.color",
    border: 1,
    borderTop: 20,
    borderBottom: 16,
    borderColor: "error.main",
    ":hover": {
      boxShadow: 10,
    },
    borderRadius: "7px",
  };
  const marker = {
    lat: propDetails.localityDetails.location[0],
    lng: propDetails.localityDetails.location[1],
  };
  const rentalEntries = {
    Availability: propDetails.rentalDetails.availability,
    Rent: propDetails.rentalDetails.rent,
    Deposit: propDetails.rentalDetails.deposit,
    "Available From": propDetails.rentalDetails.availableFrom,
    Furnishing: propDetails.rentalDetails.furnishing,
    Preference: propDetails.rentalDetails.preference,
    Negotiable: propDetails.rentalDetails.negotiable,
    Parking: propDetails.rentalDetails.parking,
  };

  const localityEntries = {
    City: propDetails.localityDetails.city,
    Locality: propDetails.localityDetails.locality,
    Landmark: propDetails.localityDetails.landmark,
    Pincode: propDetails.localityDetails.pincode,
  };

  const amenitiesEntries = {
    Bathrooms: propDetails.amenitiesDetails.bathrooms,
    Balcony: propDetails.amenitiesDetails.balcony,
    Security: propDetails.amenitiesDetails.security,
    Gym: propDetails.amenitiesDetails.gym,
    Amenities: propDetails.amenitiesDetails.amenities.toString(),
  };

  const ownerEntries = {
    "Owner Name": propDetails.ownerDetailsDetails.ownerName,
    "Owner Email": propDetails.ownerDetailsDetails.ownerEmail,
    "Owner PhoneNumber": propDetails.ownerDetailsDetails.phoneNumber,
  };
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    centerMode: true,
    slidesToShow: 1,
    slidesToScroll: 1,
    variableWidth: true,
    swipeToSlide: true,
    edgeFriction: 0.15,
    autoplay: true,
    autoplaySpeed: 2000,
  };

  const DisplayMarker = () => {
    const map = useMap();
    map.flyTo(marker, 11);
    return <Marker position={[marker.lat, marker.lng]} />;
  };

  useEffect(() => {
    dispatch(getPropertyDetails(propId));
  }, []);

  return (
    <Stack spacing={2}>
      <Grid container spacing={2}>
        <Grid item xs={6}>
          <Stack spacing={2} direction="column">
            <Box sx={{ ...boxStyle, padding: "20px" }}>
              <Stack spacing={38} direction="row">
                <Box />
                <Typography variant="h5" color="error">
                  OVERVIEW
                </Typography>
                <Box />
              </Stack>
              <></>
              <Stack spacing={2} direction="row" sx={{ padding: "20px" }}>
                <Box
                  component="img"
                  sx={{
                    height: 233,
                    width: 350,
                    borderRadius: "7px",
                  }}
                  alt="The house from the offer."
                  src={propDetails.images[0]}
                />
                <Box></Box>
                <Stack spacing={5}>
                  <Box
                    justifyContent="center"
                    alignItems="center"
                    sx={{ flexGrow: 1, textAlign: "center" }}
                  >
                    <Typography
                      sx={{ padding: "5px", opacity: 0.85, fontSize: 20 }}
                    >
                      {propDetails.mainDetails.type} |{" "}
                      {propDetails.mainDetails.roomType} | Area -{" "}
                      {propDetails.mainDetails.area}
                    </Typography>

                    <Box
                      display="flex"
                      justifyContent="center"
                      alignItems="center"
                      sx={{ flexGrow: 1, textAlign: "center" }}
                    >
                      <Stack direction="row" spacing={6}>
                        <Box>
                          <Stack spacing={3}>
                            <Stack>
                              <Typography variant="h6" color="error">
                                Floor
                              </Typography>
                              <Typography>
                                {propDetails.mainDetails.floor}
                              </Typography>
                            </Stack>
                            <Stack>
                              <Typography>
                                {propDetails.mainDetails.status}
                              </Typography>
                            </Stack>
                          </Stack>
                        </Box>

                        <Box>
                          <Stack spacing={3}>
                            <Stack>
                              <Typography variant="h6" color="error">
                                Type
                              </Typography>
                              <Typography>
                                {propDetails.mainDetails.type}
                              </Typography>
                            </Stack>
                            <Stack>
                              <Typography variant="h6" color="error">
                                Posted Date
                              </Typography>
                              <Typography>{propDetails.date}</Typography>
                            </Stack>
                          </Stack>
                        </Box>
                      </Stack>
                    </Box>
                  </Box>
                </Stack>
              </Stack>
            </Box>

            <Box sx={{ ...boxStyle, padding: "20px" }}>
              <Stack spacing={38} direction="row">
                <Box />
                <Typography variant="h5" color="error">
                  PHOTOS
                </Typography>
                <Box />
              </Stack>
              <></>
              <Stack spacing={2}>
                <Box />
                <Slider {...settings}>
                  {propDetails.images.map((image, index) => {
                    // {console.log(image)}
                    return (
                      <Box
                        component="img"
                        key={index}
                        sx={{
                          maxHeight: { xs: 233, md: 167 },
                          maxWidth: { xs: 350, md: 250 },
                          borderRadius: "7px",
                        }}
                        alt="The house from the offer."
                        src={image}
                      />
                    );
                  })}
                </Slider>
                <Box />
              </Stack>
            </Box>

            <DetailsComponent
              entries={rentalEntries}
              Heading={"Rental Details"}
              style={boxStyle}
            />

            <DetailsComponent
              entries={amenitiesEntries}
              Heading={"Amenities Details"}
              style={boxStyle}
            />

            <DetailsComponent
              entries={localityEntries}
              Heading={"Locality Details"}
              style={boxStyle}
            />

            {userInfo && userInfo.jwtToken && (
              <DetailsComponent
                entries={ownerEntries}
                Heading={"Owner Details"}
                style={boxStyle}
              />
            )}

            {userInfo && userInfo.jwtToken && <ChatBubble />}
          </Stack>
        </Grid>
        <Grid item xs={4}>
          <AppBar position="sticky">
            <div style={{ height: "100vh", width: "50vw" }}>
              <MapContainer
                center={[marker.lat, marker.lng]}
                zoom={10}
                scrollWheelZoom={true}
              >
                <TileLayer
                  attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                  url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />
                <DisplayMarker />
              </MapContainer>
            </div>
          </AppBar>
        </Grid>
      </Grid>
    </Stack>
  );
}
