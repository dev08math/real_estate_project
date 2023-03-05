import {createSlice} from '@reduxjs/toolkit'

const messagesInitialState = {
    loadingMessages: false,
    messages: {},
    messagesError: ''
  }

const contactsInitialState = {
    loadingContacts: false,
    contacts: {},
    contactsError: ''
  }

const messagesSlice = createSlice({
    name: "messages",
    initialState: messagesInitialState,
    reducers:{
        messagesPullPending: state => {
            state.loadingMessages = true;
            state.messagesError = '';
        },
        messagesPullFufilled: (state, action) => {
            state.loadingMessages = false
            state.messages = action.payload
            state.messagesError = ''
        },
        messagesPullRejected: (state, action) =>{
            state.loadingMessages = false
            state.messages = {}
            state.messagesError = action.payload
        }
    }
})

const contactsSlice = createSlice({
    name: "contacts",
    initialState:  contactsInitialState,
    reducers:{
        contactsPullPending: state => {
            state.loadingContacts = true;
            state.contactsError = '';
        },
        contactsPullFufilled: (state, action) => {
            state.loadingContacts = false
            state.contacts = action.payload
            state.contactsError = ''
        },
        contactsPullRejected: (state, action) =>{
            state.loadingContacts = false
            state.contacts = {}
            state.contactsError = action.payload
        }
    }
})

export const messagesReducer = messagesSlice.reducer;
export const contactsReducer = contactsSlice.reducer;

export const {messagesPullPending, messagesPullFufilled, messagesPullRejected } = messagesSlice.actions;
export const {contactsPullPending, contactsPullFufilled, contactsPullRejected} = contactsSlice.actions;