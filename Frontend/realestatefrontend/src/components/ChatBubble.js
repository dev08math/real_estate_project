import React, { useState } from "react";
import { Fab, Dialog } from "@mui/material";
import { Chat, ChatBubble } from "@mui/icons-material";
import ChatBox from "./ChatBox";
const Test = () => {
  const [open, setOpen] = useState(false);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <>
      <Fab
        sx={{
          position: "fixed",
          bottom: 40,
          right: 40,
        }}
        onClick={handleOpen}
      >
        <Chat />
      </Fab>
      <Dialog open={open} onClose={handleClose}>
        <ChatBox />
      </Dialog>
    </>
  );
};

export default ChatBubble;
