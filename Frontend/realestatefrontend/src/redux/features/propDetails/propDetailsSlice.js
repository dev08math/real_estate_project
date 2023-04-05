import {createSlice} from '@reduxjs/toolkit'

const initialState = {
    propertyDetails: localStorage.getItem("propDetails")
    ? JSON.parse(localStorage.getItem("propDetails"))
    : { mainDetails:{}, localityDetails:{}, rentalDetails:{}, amenitesDetials:{amenities:[]}},
  }

const propDetails = createSlice({
    name: "propDetails",
    initialState,
    reducers:{
        mainDetailsUpdate: (state, action) => {
            state.propertyDetails.mainDetails = action.payload
        },
        localityDetailsUpdate: (state, action) => {
            state.propertyDetails.localityDetails = action.payload
        },
        rentalDetailsUpdate: (state, action) => {
            state.propertyDetails.rentalDetails = action.payload
        },
        amenitiesDetailsUpdate: (state, action) => {
            state.propertyDetails.amenitesDetials= action.payload
        }
    }
})


export const propDetailsReducer = propDetails.reducer;

export const {mainDetailsUpdate, localityDetailsUpdate, rentalDetailsUpdate ,amenitiesDetailsUpdate} = propDetails.actions;
