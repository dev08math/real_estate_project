import { configureStore } from '@reduxjs/toolkit'
import { createLogger } from 'redux-logger'

import { userDetailsReducer, userLoginReducer, userSignUpReducer } from './features/user/userSlice'
import { searchReducer } from './features/search/searchSlice';
import { propertyDataReducer, proplistReducer } from './features/proplist/propSlice';
import { propDetailsReducer } from './features/propDetails/propDetailsSlice';
import { contactsReducer, messagesReducer } from './features/chat/chatSlice';

const logger = createLogger();

const store = configureStore({
    reducer: {
      userDetails: userDetailsReducer,
      userLogin: userLoginReducer,
      userSignUp: userSignUpReducer,
      searchResults: searchReducer,
      proplist: proplistReducer,
      propDetails: propDetailsReducer,
      propData: propertyDataReducer,
      messages: messagesReducer,
      contacts: contactsReducer
    },
    middleware: getDefaultMiddleware => getDefaultMiddleware().concat(logger)
})


export default store;