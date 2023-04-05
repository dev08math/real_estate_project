import React from "react";
import { TextField, InputAdornment, Link } from "@mui/material";
import { Link as LinkIcon } from "@mui/icons-material";

const Test = () => {
  return (
    <TextField
      label="Enter website URL"
      InputProps={{
        endAdornment: (
          <InputAdornment position="end">
            <Link href="https://youtube.com" target="_blank" rel="noopener">
              <LinkIcon />
            </Link>
          </InputAdornment>
        ),
      }}
    />
  );
};

export default Test;
