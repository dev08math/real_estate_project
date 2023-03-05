import React, { useEffect, useState } from "react";
import SendIcon from '@mui/icons-material/Send';
import { Button } from "@mui/material";
import ScrollToBottom from "react-scroll-to-bottom";
import { useDispatch } from "react-redux";

import { useRecoilValue, useRecoilState } from "recoil";

import "../styles/ChatBox.css";



var stompClient = null;

export default function ChatBox() {
  const dispatch = useDispatch();

  const userLogin = useSelector((state) => state.userLogin);
  const { error, loading, userInfo } = userLogin; // have to change the state names

  const currentUser = useRecoilValue(loggedInUser);
  const [text, setText] = useState("");
  const [contacts, setContacts] = useState([]);
  const [activeContact, setActiveContact] = useRecoilState(chatActiveContact);
  const [messages, setMessages] = useRecoilState(chatMessages);
  return (
    <div id="frame">
      {error && <AlertBox message={error} />}
      {loading && <LoadingScreen message="Fetching User Data..." />}
      <div id="sidepanel">
        <div id="profile">
          <div class="wrap">
            <img
              id="profile-img"
              src={userInfo.dp}
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

        {/* <div id="contacts">
          <ul>
            {contacts.map((contact) => (
              <li
                onClick={() => setActiveContact(contact)}
                class={
                  activeContact && contact.id === activeContact.id
                    ? "contact active"
                    : "contact"
                }
              >
                <div class="wrap">
                  <span class="contact-status online"></span>
                  <img id={contact.id} src={contact.profilePicture} alt="" />
                  <div class="meta">
                    <p class="name">{contact.name}</p>
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
        </div> */}

      </div>

      <div class="content">
        <div class="contact-profile">
          {/* <img src={activeContact && activeContact.profilePicture} alt="" /> */}
          
          {/* placeholder */}
          <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzWhoN-LXL6cvf0Yf2LSE15lCO0_IhTeDSt4uaqh8d7c2u7eyal28H01wKxSwvROlTd3Y&usqp=CAU" alt="" />
          
          {/* <p>{activeContact && activeContact.name}</p> */}

          {/* placeholder */}
          <p>George</p>
        </div>
        {/* <ScrollToBottom className="messages">
          <ul>
            {messages.map((msg) => (
              <li class={msg.senderId === currentUser.id ? "sent" : "replies"}>
                {msg.senderId !== currentUser.id && (
                  <img src={activeContact.profilePicture} alt="" />
                )}
                <p>{msg.content}</p>
              </li>
            ))}
          </ul>
        </ScrollToBottom> */}
        
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
