import axios from "axios";
import {
  messagesPullPending,
  messagesPullFufilled,
  messagesPullRejected,
  contactsPullPending,
  contactsPullFufilled,
  contactsPullRejected,
} from "./chatSlice";

export const fetchContacts = (userName) => async (dispatch) => {
  dispatch(contactsPullPending());
  // INPUT MESSAGE STATUS TOGGLING DISPATCHER, FIRST ALTER THE CONTACT STATE IN FRONTEND BEFORE DISPATCHING

  try {
    const config = {
      headers: {
        "Content-type": "application/json",
      },
    };

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

export const fetchDetails = () => async (dispatch, getState) => {
  dispatch(detailsPending());

  try {
    const {
      userLogin: { userInfo },
    } = getState();

    const config = {
      headers: {
        "Content-type": "application/json",
        Authorization: `Bearer ${userInfo.access}`,
      },
    };

    const { data } = await axios.get("/api/user/details", config);

    dispatch(detailsFufilled(data));
  } catch (error) {
    console.log(error);
  }
};
