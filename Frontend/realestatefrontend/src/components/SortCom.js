import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  FormControl,
  FormControlLabel,
  FormLabel,
  Radio,
  RadioGroup,
  Slide,
  Stack,
  ToggleButton,
  ToggleButtonGroup,
  Typography,
} from "@mui/material";
import React, { useState } from "react";
import ArrowUpwardIcon from "@mui/icons-material/ArrowUpward";
import ArrowDownwardIcon from "@mui/icons-material/ArrowDownward";

const Transition = React.forwardRef(function Transition(params, ref) {
    return <Slide direction="up" ref={ref} {...params} />;
  });

export default function SortCom(props) {
  const [open, setOpen] = useState(false);
  const [sortEnabled, setSortEnabled] = useState(false);
  const [draftAspect, setDraftAspect] = useState("none");
  const [draftDirection, setDraftDirection] = useState("none");
  const [prevAspect, setPrevAspect] = useState("none");
  const [prevDirection, setPrevDirection] = useState("none");
  const [dirColor, setDirColor] = useState("default");

  const handleSortOpen = () => {
    setOpen(true);
  };

  const handleAspect = (event) => {
    setDraftAspect(event.target.value);
  };

  const handleDirection = (event, dir) => {
    setDraftDirection(dir);
    setDirColor(dir === "increase" ? "success" : "error");
  };

  console.log(draftAspect, open);
  const handleSortCancel = () => {
    setOpen(false);
    setDraftAspect(prevAspect);
    setDraftDirection(prevDirection);
    setDirColor(prevDirection === "increase" ? "success" : "error");
  };

  const sortList = () => {
    if (draftAspect === "none") return;
    const sorted = [...props.proplist].sort((a, b) =>
      draftDirection === "decrease"
        ? b[draftAspect] - a[draftAspect]
        : a[draftAspect] - b[draftAspect]
    );
    setOpen(false);
    props.setProperties(sorted);
    setPrevAspect(draftAspect);
    setPrevDirection(draftDirection);
    setSortEnabled(true);
  };


  return (
    <div>
    <Button
      variant="contained"
      color="error"
      onClick={handleSortOpen}
      spacing={2}
      size="small"
      style={{textTransform: 'none'}}
    >
      <Typography>{draftAspect}</Typography>
    </Button>
    <Dialog
      open={open}
      TransitionComponent={Transition}
      keepMounted
      onClose={handleSortCancel}
      aria-describedby="alert-dialog-for-sort"
    >
      <DialogTitle sx={{marginLeft:12}} >{"Sort by?"}</DialogTitle>
      <DialogContent>
        <FormControl>
          <FormLabel id="aspect-list">Aspects:</FormLabel>

          <Stack direction="row" spacing={6}>
            <RadioGroup
              aria-labelledby="aspect-list"
              defaultValue="female"
              name="radio-buttons-group"
              value={draftAspect}
              onChange={handleAspect}
            >
              <FormControlLabel
                value="postingDate"
                control={<Radio />}
                label="Posting Date"
              />

              <FormControlLabel
                value="price"
                control={<Radio />}
                label="Pricing"
              />

              <FormControlLabel
                value="avail"
                control={<Radio />}
                label="Availability"
              />
            </RadioGroup>

            <ToggleButtonGroup
              value="dummyvalue"
              aria-label="text formatting"
              exclusive
              onChange={handleDirection}
            >
              <ToggleButton
                value="increase"
                size="small"
                label="increasing"
              >
                {draftDirection === "increase" ? (
                  <ArrowUpwardIcon color={dirColor} />
                ) : (
                  <ArrowUpwardIcon />
                )}
              </ToggleButton>
              <ToggleButton
                value="decrease"
                size="small"
                label="decreasing"
              >
                {draftDirection === "decrease" ? (
                  <ArrowDownwardIcon color={dirColor} />
                ) : (
                  <ArrowDownwardIcon />
                )}
              </ToggleButton>
            </ToggleButtonGroup>
          </Stack>
        </FormControl>
      </DialogContent>
      <DialogActions>
        <Button
          variant="outlined"
          color="error"
          onClick={handleSortCancel}
        >
          Cancel
        </Button>
        <Button variant="outlined" color="success" onClick={sortList}>
          Sort it
        </Button>
      </DialogActions>
    </Dialog>
  </div>
  );
}
