import { Grid, Stack, Typography } from "@mui/material";
import { Box } from "@mui/material";
import React, { useEffect, useState } from "react";
import Filter from "../components/Filter";
import ListCell from "../components/ListCell";
import SearchBar from "../components/SearchBar";
import SortCom from "../components/SortCom";
import { useDispatch, useSelector } from "react-redux";
import LoadingScreen from "../components/LoadingScreen";
import AdvertComponent from "../components/AdvertComponent";

export default function Listings() {
  const [propertyFilter, setpropertyFilter] = useState([]);
  const [bhkFilter, setbhkFilter] = useState({});
  const [rentFilter, setrentFilter] = useState([]);
  const [availabilityFilter, setavailabilityFilter] = useState("");
  const [floorsFilter, setfloorsFilter] = useState(0);
  const [ageFilter, setageFilter] = useState("");
  const [furnishingFilter, setfurnishingFilter] = useState("");
  const [photosFilter, setphotosFilter] = useState(false);

  const dispatch = useDispatch();
  const proplist = useSelector((state) => state.proplist);
  const { error, loading, proplistData } = proplist;
  const [properties, setProperties] = useState(proplistData);

  useEffect(() => {
    if (proplistData) setProperties(proplistData);
  }, [proplistData]);

  useEffect(() => {
    setProperties((prevState) => {
      return prevState.filter((property) => {
        if (
          property.mainDetails.type === undefined ||
          (propertyFilter[0] !== "" &&
            propertyFilter.indexOf(property.mainDetails.type) === -1)
        )
          return false;
        if (
          property.mainDetails.roomType === undefined ||
          (bhkFilter[0] !== "" &&
            bhkFilter.indexOf(property.mainDetails.roomType) === -1)
        )
          return false;
        if (
          property.rentalDetails.rent === undefined ||
          property.rentalDetails.rent < rentFilter[0] * 1000 ||
          property.rentalDetails.ren > rentFilter[1] * 1000
        )
          return false;
        if (
          property.mainDetails.propertyAge === undefined ||
          (ageFilter[0] !== "" &&
            ageFilter.indexOf(property.mainDetails.propertyAge) === -1)
        )
          return false;
        if (
          property.rentalDetails.furnishing === undefined ||
          (furnishingFilter[0] !== "" &&
            furnishingFilter.indexOf(property.rentalDetails.furnishing) === -1)
        )
          return false;
        if (
          property.rentalDetails.availability === undefined ||
          (availabilityFilter[0] !== "" &&
            availabilityFilter.indexOf(property.rentalDetails.availability) ===
              -1)
        )
          return false;
        if (
          property.images === undefined ||
          (photosFilter && property.images.length < 2)
        )
          return false;
        return true;
      });
    });
  }, [
    propertyFilter,
    bhkFilter,
    rentFilter,
    availabilityFilter,
    floorsFilter,
    ageFilter,
    furnishingFilter,
    photosFilter,
  ]);

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
            <SearchBar />
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
            setavailabilityFilter,
            setfloorsFilter,
          ]}
        />
      </Grid>

      <Grid item xs={7}>
        <Stack spacing={2}>
          <Box
            sx={{ bgcolor: "crimson", height: "80px", borderRadius: "10px" }}
          >
            <Box sx={{ display: "flex", justifyContent: "center" }}>
              <Typography sx={{ color: "white", textTransform: "none" }}>
                Showing {properties.length} results
              </Typography>
            </Box>
            <Box
              sx={{
                display: "flex",
                justifyContent: "flex-end",
                marginRight: 7,
              }}
            >
              <SortCom proplist={properties} setProperties={setProperties} />
            </Box>
          </Box>
          {loading && <LoadingScreen message="Finding properties.." />}
          <Box
            sx={{
              mb: 2,
              display: "flex",
              flexDirection: "column",
              maxHeight: "700px",
              overflow: "hidden",
              overflowY: "scroll",
            }}
          >
            <>
              {(() => {
                const arr = [];
                for (let i = 0; i < properties.length; i++) {
                  arr.push(<ListCell propData={properties[i]} />);
                }
                return arr;
              })()}
            </>
          </Box>
        </Stack>
      </Grid>

      <Grid item xs={2}>
        <Box sx={{ bgcolor: "orange" }}>
          <AdvertComponent />
        </Box>
      </Grid>
    </Grid>
  );
}
