import React, { useState} from "react";
import Alert from "@mui/material/Alert";
import AlertTitle from "@mui/material/AlertTitle";
import Dialog from "@mui/material/Dialog";
import ReportProblemIcon from '@mui/icons-material/ReportProblem';

export default function AlertBox({message}) {
  const [open, setOpen] = useState(true);
  const handleClick = () => {
    setOpen(!open);
  };

  return (
      <Dialog open={open} onClose={handleClick}>
        <Alert
          severity="warning"
          color="error"
          role="button"
          icon={<ReportProblemIcon />}
          onClose={() => {}}
          closeText="Error invoked."
          sx={{
            "& .MuiAlert-icon": {
              color: "blue"
            }
          }}
        >
          <AlertTitle>Error!</AlertTitle>
          {message}
        </Alert>
      </Dialog>
  )
}
