import {createSlice} from '@reduxjs/toolkit'

const initialState = {
    loading: false,
    searchData: [],
    searchTerms: localStorage.getItem("searchTerms")
    ? JSON.parse(localStorage.getItem("searchTerms"))
    : [],
    error: ''
  }

const searchSlice = createSlice({
    name: "search",
    initialState,
    reducers:{
        searchPending: state => {
            state.loading = true;
            state.error = '';
        },
        searchFufilled: (state, action) => {
            state.loading = false
            state.searchData = action.payload
            state.error = ''
        },
        searchRejected: (state, action) =>{
            state.loading = false
            state.searchData = {}
            state.error = action.payload
        },
        modifySearchTerms: (state, action) =>{
            state.searchTerms = action.payload
        }
    }
})


export const searchReducer = searchSlice.reducer;

export const {searchPending, searchFufilled, searchRejected, modifySearchTerms } = searchSlice.actions;
