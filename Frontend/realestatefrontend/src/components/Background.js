import { Typography } from "@mui/material";
import React from "react";
import TypeWriter from "typewriter-effect";

export default function Background() {
  return (
    <div
      style={{ color: "white", background: "transparent", boxShadow: "none" }}
    >
      <div className="text">
        <Typography  variant="h2">
          Random Marketing Slogan. Find your desired 'whatever' in
        </Typography>
        <Typography display="inline" variant="h2"color={"crimson"}>
          <TypeWriter
            options={{
              strings: [
                "Pune",
                "Mumbai",
                "Kochi",
                "Delhi",
                "Gurgaon",
                "Hyderabad",
                "Bangalore",
                "Indore",
                "Chennai",
                "Ahemdabad",
              ],
              autoStart: true,
              loop: true,
            }}
            onInit={(typewriter) => {
              typewriter.pauseFor(150).deleteAll().start();
            }}
          />
        </Typography>
      </div>
    </div>
  );
}
