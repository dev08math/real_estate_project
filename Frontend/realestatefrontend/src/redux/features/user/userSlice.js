import {createSlice} from '@reduxjs/toolkit'

const initialState = {
    loading: false,
    userInfo: localStorage.getItem("userInfo")
    ? JSON.parse(localStorage.getItem("userInfo"))
    : {},
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
        },
        logOutUser: (state) =>{
            state.loading = false
            state.userInfo = {}
            state.error = ""
        },
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

const userSignUplice = createSlice({
    name: "userSignUp",
    initialState,
    reducers:{
        signUpPending: state => {
            state.loading = true;
            state.error = '';
        },
        signUpFufilled: (state, action) => {
            state.loading = false
            state.userInfo = action.payload
            state.error = ''
        },
        signUpRejected: (state, action) =>{
            state.loading = false
            state.userInfo = {}
            state.error = action.payload
        }
    }
})

export const userDetailsReducer = userDetailsSlice.reducer;
export const userLoginReducer = userLoginSlice.reducer;
export const userSignUpReducer = userSignUplice.reducer;

export const {detailsPending, detailsFufilled, detailsRejected, logOutUser } = userDetailsSlice.actions;
export const {loginPending, loginFufilled, loginRejected} = userLoginSlice.actions;
export const {signUpPending, signUpFufilled, signUpRejected} = userSignUplice.actions;