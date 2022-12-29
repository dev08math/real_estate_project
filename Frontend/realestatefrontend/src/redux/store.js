import { configureStore } from '@reduxjs/toolkit'
import { createLogger } from 'redux-logger'

import { userDetailsReducer, userLoginReducer } from './features/user/userSlice'

const logger = createLogger();

const store = configureStore({
    reducer: {
      userDetails: userDetailsReducer,
      userLogin: userLoginReducer
    },
    middleware: getDefaultMiddleware => getDefaultMiddleware().concat(logger)
})


export default store;