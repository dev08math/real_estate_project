import { AppBar, Button, Grid, ModalRoot, Typography } from "@mui/material";
import { React, useRef, useState } from "react";
import { MapContainer, TileLayer, Marker, Popup, useMapEvents, useMap } from "react-leaflet";
import { Icon } from "leaflet";

import homePng from "../assets/Mapicons/house.png";
import officePng from "../assets/Mapicons/office.png";
import apartmentPng from "../assets/Mapicons/apartment.png";
import tempListings from "../assets/Data/Dummydata";

export default function Listings() {
  const homeIcon = new Icon({
    iconUrl: homePng,
    iconSize: [40, 40],
  });

  const officeIcon = new Icon({
    iconUrl: officePng,
    iconSize: [40, 40],
  });

  const apartmentIcon = new Icon({
    iconUrl: apartmentPng,
    iconSize: [40, 40],
  });

  const [lat, setLat] = useState(51.505);
  const [long, setLong] = useState(-0.09);
  const [allListings, setListings] = useState(tempListings);
  const [marker, setMarker] = useState({'lat': 51.50, 'lng': -0.1});
  const [zoomLevel, setzoomLevel] = useState(5);

  const MapContent = () => {  
    const map = useMapEvents({
      click: (e) => {
        setMarker(e.latlng);
      }
    });
    return null;
}


  return (
    <Grid container>
      <Grid item xs={4}>
        <Typography variant="h6">
          It is a long established fact that a reader will be distracted by the
          readable content of a page when looking at its layout. The point of
          using Lorem Ipsum is that it has a more-or-less normal distribution of
          letters, as opposed to using 'Content here, content here', making it
          look like readable English. Many desktop publishing packages and web
          page editors now use Lorem Ipsum as their default model text, and a
          search for 'lorem ipsum' will uncover many web sites still in their
          infancy. Various versions have evolved over the years, sometimes by
          accident, sometimes on purpose (injected humour and the like). It is a
          long established fact that a reader will be distracted by the readable
          content of a page when looking at its layout. The point of using Lorem
          Ipsum is that it has a more-or-less normal distribution of letters, as
          opposed to using 'Content here, content here', making it look like
          readable English. Many desktop publishing packages and web page
          editors now use Lorem Ipsum as their default model text, and a search
          for 'lorem ipsum' will uncover many web sites still in their infancy.
          Various versions have evolved over the years, sometimes by accident,
          sometimes on purpose (injected humour and the like). It is a long
          established fact that a reader will be distracted by the readable
          content of a page when looking at its layout. The point of using Lorem
          Ipsum is that it has a more-or-less normal distribution of letters, as
          opposed to using 'Content here, content here', making it look like
          readable English. Many desktop publishing packages and web page
          editors now use Lorem Ipsum as their default model text, and a search
          for 'lorem ipsum' will uncover many web sites still in their infancy.
          Various versions have evolved over the years, sometimes by accident,
          sometimes on purpose (injected humour and the like).
        </Typography>
      </Grid>
      <Grid item xs={8}>
        <AppBar position="sticky">
          <div style={{ height: "100vh" }}>
            <MapContainer
              center={[marker.lat,  marker.lng]}
              zoom={10}
              scrollWheelZoom={true}
            >
              <TileLayer
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
              />

              {/* {tempListings.map((listing) => {
                function displayIcon() {
                  if (listing.listing_type === "House") {
                    return homeIcon;
                  } else if (listing.listing_type === "Apartment") {
                    return apartmentIcon;
                  } else if (listing.listing_type === "Office") {
                    return officeIcon;
                  }
                }
                return (
                  <Marker
                    key={listing.id}
                    icon={displayIcon()}
                    position={[
                      listing.location.coordinates[0],
                      listing.location.coordinates[1],
                    ]}
                  >
                    <Popup>
                      <Typography variant="h5">{listing.title}</Typography>
                      <img
                        src={listing.picture1}
                        style={{
                          height: "14rem",
                          width: "18rem",
                          cursor: "pointer",
                        }}
                      />
                      <Typography variant="body1">
                        {listing.description}
                      </Typography>
                      <Button variant="contained" fullWidth>
                        Details
                      </Button>
                    </Popup>
                  </Marker>
                );
              })} */}

              <Marker  icon={homeIcon} position={[lat, long]}>
                <Popup>
                  <Typography variant="h2">Hey there</Typography>
                  <Typography variant="body1">This is body1</Typography>
                </Popup>
              </Marker>
              <MapContent/>
            {/* {markers.map((marker, i) => (
                    <Marker key={`marker-${i}`} position={marker}>
                      <Popup>
                        <span>
                          A pretty CSS3 popup. <br /> Easily customizable.
                        </span>
                      </Popup>
                    </Marker>
                  ))}
            </MapContainer> */}
            <Marker position={[marker.lat, marker.lng]}>
              <Popup>
                <span>
                  A pretty CSS3 popup. <br /> Easily customizable.
                </span>
              </Popup>
            </Marker>
            </MapContainer>
          </div>
        </AppBar>
      </Grid>
    </Grid>
  );
}
