import axios from "axios";
import {
  proplistFufilled,
  proplistPending,
  proplistRejected,
  propDataFufilled,
  propDataPending,
  propDataRejected
} from "./propSlice";

export const fetchProperties = () => async (dispatch, getState) => {
  dispatch(proplistPending());

  try {
    const {
      searchResults: { searchResults },
    } = getState();

    const config = {
      headers: {
        "Content-type": "application/json",
      },
    };

    const { data } = await axios.get(
      "api/properties/fetchPropertyDetails",
      searchResults.searchTerms,
      config
    );
    // console.log(data);
    dispatch(proplistFufilled(data));
    localStorage.setItem("proplist", JSON.stringify(data));
  } catch (error) {
    console.log(error);
    dispatch(proplistRejected(error.response.data));
  }
};


export const getPropertyDetails = (id) => async (dispatch, getState) => {
  dispatch(propDataPending());

  try {
    const searchData = {"propId" : id};
    const config = {
      headers: {
        "Content-type": "application/json",
      },
    };

    const { data } = await axios.get(
      "api/properties/fetchPropertyDetails",
      searchData,
      config
    );

    dispatch(propDataFufilled(data));
    localStorage.setItem("propData", JSON.stringify(data));
  } catch (error) {
    console.log(error);
    dispatch(propDataRejected(error.response.data));
  }
};