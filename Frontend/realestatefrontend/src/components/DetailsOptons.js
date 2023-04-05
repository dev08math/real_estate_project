import {
  Button,
  createTheme,
  MenuItem,
  MenuList,
  ThemeProvider,
} from "@mui/material";
import React from "react";
import { Link } from "react-router-dom";

function DetailsOptons({ Option }) {
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

  return (
    <ThemeProvider theme={theme}>
      <MenuList spacing={1}>
        <MenuItem disableRipple sx={{ "&:hover": { background: "none" } }}>
          <Link to={"/mainDetails"}>
            {Option === "Property Details" ? (
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
            ) : (
              <Button sx={{ ":hover": { boxShadow: 10 } }}>
                Property Details
              </Button>
            )}
          </Link>
        </MenuItem>
        <MenuItem disableRipple sx={{ "&:hover": { background: "none" } }}>
          <Link to={"/localityDetails"}>
            {Option === "Locality Details" ? (
              <Button
                variant="none"
                sx={{
                  color: "background.main",
                  borderLeft: 4,
                  borderColor: "secondary.border",
                }}
              >
                Locality Details
              </Button>
            ) : (
              <Button sx={{ ":hover": { boxShadow: 10 } }}>
                Locality Details
              </Button>
            )}
          </Link>
        </MenuItem>
        <MenuItem disableRipple sx={{ "&:hover": { background: "none" } }}>
          <Link to={"/rentalDetails"}>
            {Option === "Rental Details" ? (
              <Button
                variant="none"
                sx={{
                  color: "background.main",
                  borderLeft: 4,
                  borderColor: "secondary.border",
                }}
              >
                Rental Details
              </Button>
            ) : (
              <Button sx={{ ":hover": { boxShadow: 10 } }}>
                Rental Details
              </Button>
            )}
          </Link>
        </MenuItem>
        <MenuItem disableRipple sx={{ "&:hover": { background: "none" } }}>
          <Link to={"/amenitiesDetails"}>
            {Option === "Amenities" ? (
              <Button
                variant="none"
                sx={{
                  color: "background.main",
                  borderLeft: 4,
                  borderColor: "secondary.border",
                }}
              >
                Amenities
              </Button>
            ) : (
              <Button sx={{ ":hover": { boxShadow: 10 } }}>Amenities</Button>
            )}
          </Link>
        </MenuItem>
      </MenuList>
    </ThemeProvider>
  );
}

export default DetailsOptons;
