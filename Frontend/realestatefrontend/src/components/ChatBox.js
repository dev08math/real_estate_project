import React, { useEffect, useState } from "react";
import SendIcon from '@mui/icons-material/Send';
import { Alert, Button } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";

import "../styles/ChatBox.css";
import { fetchContacts, getMessages, setActiveContact } from "../redux/features/chat/actions";
import ScrollToBottom from "react-scroll-to-bottom";
import LoadingScreen from "./LoadingScreen";
import AlertBox from "./AlertBox";
import SockJS from "sockjs-client";
import { Client } from "stompjs";

var stompClient = null;

export default function ChatBox() {
  const dispatch = useDispatch();

  const userLogin = useSelector((state) => state.userLogin);
  const { error, loading, userInfo } = userLogin; 
  const contactDetails = useSelector((state) => state.contacts);
  const {contactsError, loadingContacts, contacts, activeContact} = contactDetails;
  const messageData = useSelector((state) => state.messages);
  const {messagesError, loadingMessages, messages} = messageData;

  const currentUser = userInfo;
  const [text, setText] = useState("");
  const [messageState, setMessages] = useState()

  const onError = (err) => {
    console.log(err);
  };

  
  const connect = () => {
    const socket = new SockJS("/ws");
    stompClient = Client.over(socket);
    stompClient.connect({}, onConnected, onError);
  };

  const onConnected = () => {
    console.log("connected");
    console.log(currentUser);
    stompClient.subscribe(
      "/user/" + currentUser.userName + "/queue/messages",
      onMessageReceived
    );
  };

  const sendMessage = (msg) => {
    if (msg.trim() !== "") {
      const message = {
        senderName: currentUser.name,
        recipientName: activeContact.name,
        content: msg,
        timestamp: new Date(),
      };
      stompClient.send("/app/chat", {}, JSON.stringify(message));

      const newMessages = [...messages];
      newMessages.push(message);
      setMessages(newMessages);
    }
  };

  const onMessageReceived = (msg) => {
    const notification = JSON.parse(msg.body);

    if (activeContact.userName === notification.senderName) {
      dispatch(getMessages(activeContact.userName, currentUser.userName));
      setMessages((prevState) => {return { ...prevState, msg}})
    } else {
      <Alert severity="info">Received a new message from {notification.senderName}</Alert>
    }
    dispatch(fetchContacts(currentUser.userName))
  };

  useEffect(() => {
    if (localStorage.getItem("userInfo") === null) {
      // navigate to login
    }
    connect();
    dispatch(fetchContacts(currentUser.userName))
  }, []);


  return (
    <div id="frame">
      {error && <AlertBox message={error} />}
      {loading && <LoadingScreen message="Fetching User Data..." />}
      <div id="sidepanel">
        <div id="profile">
          <div class="wrap">
            <img
              id="profile-img"
              src={userInfo.displayLink}
              class="online"
              alt=""
            />
            <p>{userInfo.username}</p> 
            <div id="status-options">
              <ul>
                <li id="status-online" class="active">
                  <span class="status-circle"></span> <p>Online</p>
                </li>
                <li id="status-away">
                  <span class="status-circle"></span> <p>Away</p>
                </li>
                <li id="status-busy">
                  <span class="status-circle"></span> <p>Busy</p>
                </li>
                <li id="status-offline">
                  <span class="status-circle"></span> <p>Offline</p>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div id="search" />

        <div id="contacts">
          <ul>
            {contacts.map((contact) => (
              <li
                onClick={dispatch(setActiveContact(contact.userName))}
                class={
                  activeContact && contact.userName === activeContact.userName
                    ? "contact active"
                    : "contact"
                }
              >
                <div class="wrap">
                  <span class="contact-status online"></span>
                  <img id={contact.userName} src={contact.displayLink} alt="" />
                  <div class="meta">
                    <p class="name">{contact.userName}</p>
                    {contact.newMessages !== undefined &&
                      contact.newMessages > 0 && (
                        <p class="preview">
                          {contact.newMessages} new messages
                        </p>
                      )}
                  </div>
                </div>
              </li>
            ))}
          </ul>
        </div>

      </div>

      <div class="content">
        <div class="contact-profile">
            <img src={activeContact && activeContact.displayLink} alt="" />
            <p>{activeContact && activeContact.userName}</p>
          </div>
          <ScrollToBottom className="messages">
          <ul>
            {messages.map((msg) => (
              <li class={msg.senderName === currentUser.userName ? "sent" : "replies"}>
                  <img src={activeContact.displayLink} alt="" />
                <p>{msg.content}</p>
              </li>
            ))}
          </ul>
        </ScrollToBottom>
        
        <div class="message-input">
          <div class="wrap">
            <input
              name="user_input"
              size="large"
              placeholder="Write your message..."
              value={text}
              onChange={(event) => setText(event.target.value)}
              onKeyDown={(event) => {
                if (event.key === "Enter") {
                  sendMessage(text);
                  setText("");
                }
              }}
            />
            <Button
              color="primary"
              variant="contained"
              onClick={() => {
                sendMessage(text);
                setText("");
              }}
            >
             <SendIcon />
            </Button>
            
          </div>
        </div>
      </div>
    </div>
  );
}
