import {createSlice} from '@reduxjs/toolkit'

const initialState = {
    loading: false,
    proplistData: localStorage.getItem("proplist")
    ? JSON.parse(localStorage.getItem("proplist"))
    : [],
    error: ''
  }

const currentDetails = {
    propLoading: false,
    propDetails: localStorage.getItem("propData")
    ? JSON.parse(localStorage.getItem("propData"))
    : {},
    propError: ''
}
const proplistSlice = createSlice({
    name: "proplist",
    initialState,
    reducers:{
        proplistPending: state => {
            state.loading = true;
            state.error = '';
        },
        proplistFufilled: (state, action) => {
            state.loading = false
            state.proplistData = action.payload
            state.error = ''
        },
        proplistRejected: (state, action) =>{
            state.loading = false
            state.proplistData = {}
            state.error = action.payload
        }
    }
})


const propertyDataSlice  = createSlice({
    name: "propDetails",
    initialState: currentDetails,
    reducers:{
        propDataPending: state => {
            state.propLoading = true;
            state.propError = '';
        },
        propDataFufilled: (state, action) => {
            state.propLoading = false
            state.propDetails = action.payload
            state.propError = ''
        },
        propDataRejected: (state, action) =>{
            state.propLoading = false
            state.propDetails = {}
            state.propError = action.payload
        }
    }
})
export const proplistReducer = proplistSlice.reducer;
export const propertyDataReducer = propertyDataSlice.reducer;

export const {proplistPending, proplistFufilled, proplistRejected } = proplistSlice.actions;
export const {propDataFufilled, propDataPending, propDataRejected} = propertyDataSlice.actions;
