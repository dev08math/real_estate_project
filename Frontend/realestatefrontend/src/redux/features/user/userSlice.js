import {createSlice} from '@reduxjs/toolkit'

const initialState = {
    loading: false,
    userInfo: {},
    error: ''
  }

const userDetailsSlice = createSlice({
    name: "userDetails",
    initialState,
    reducers:{
        detailsPending: state => {
            state.loading = true;
            state.error = '';
        },
        detailsFufilled: (state, action) => {
            state.loading = false
            state.userInfo = action.payload
            state.error = ''
        },
        detailsRejected: (state, action) =>{
            state.loading = false
            state.userInfo = {}
            state.error = action.payload
        }
    }
})

const userLoginSlice = createSlice({
    name: "userLogin",
    initialState,
    reducers:{
        loginPending: state => {
            state.loading = true;
            state.error = '';
        },
        loginFufilled: (state, action) => {
            state.loading = false
            state.userInfo = action.payload
            state.error = ''
        },
        loginRejected: (state, action) =>{
            state.loading = false
            state.userInfo = {}
            state.error = action.payload
        }
    }
})

export const userDetailsReducer = userDetailsSlice.reducer;
export const userLoginReducer = userLoginSlice.reducer;

export const {detailsPending, detailsFufilled, detailsRejected } = userDetailsSlice.actions;
export const {loginPending, loginFufilled, loginRejected} = userLoginSlice.actions;