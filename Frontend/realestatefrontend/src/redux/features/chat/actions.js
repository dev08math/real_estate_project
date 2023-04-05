import axios from "axios";
import {
  contactsPullPending,
  contactsPullFufilled,
  contactsPullRejected,
  updateActiveContact,
  messagesPullFufilled,
  messagesPullPending,
  messagesPullRejected
} from "./chatSlice";

export const fetchContacts = (userName) => async (dispatch, getState) => {
  dispatch(contactsPullPending());

  try {
    
    const {
      searchResults: {searchResults},
      userLogin: { userInfo },
    } = getState();

    const config = {
        headers: {
            'Content-type': 'application/json',
            Authorization: `Bearer ${userInfo.access}`,
        }
    }

    const { data } = await axios.post(
      "/api/user/chat/getContactDetails",
      { userName: userName },
      config
    );

    dispatch(contactsPullFufilled(data));
  } catch (error) {
    console.log(error);
    dispatch(contactsPullRejected(error.response.data));
  }
};


export const setActiveContact= (contact) => async (dispatch) => {
    dispatch(updateActiveContact(contact))
    localStorage.setItem("activeContact", JSON.stringify(contact)) 
};


export const getMessages = (contactName, userName) => async (dispatch, getState) => {
  dispatch(messagesPullPending());

  try {

    const body = {
        senderName: contactName,
        receiverName: userName,
        limit: 10
    };
  
    const {
      searchResults: {searchResults},
      userLogin: { userInfo }
    } = getState()

    const config = {
        headers: {
            'Content-type': 'application/json',
            Authorization: `Bearer ${userInfo.access}`,
        }
    }

    const { data } = await axios.post(
      "/api/user/chat/getMessages",
      config,
      body
    );

    dispatch(messagesPullFufilled(data));
  } catch (error) {
    console.log(error);
    dispatch(messagesPullRejected(error.response.data));
  }
};
