import axios from 'axios'
import { detailsFufilled, detailsPending, detailsRejected, loginFufilled, loginPending, loginRejected } from './userSlice'

export const loginUser = (email, password) => async dispatch  => {

    dispatch(loginPending());

    try {
        const config = {
            headers: {
                'Content-type': 'application/json'
            }
        }

        const { data } = await axios.post(
            '/api/user/login',
            { 'email': email, 'password': password },
            config
        )

        dispatch(loginFufilled(data));
    }
    catch(error){
        console.log(error)
        dispatch(loginRejected(error.response.data))
    }
    
}

export const fetchDetails = () => async (dispatch, getState) =>{
    dispatch(detailsPending());

    try {
        const {
            userLogin: { userInfo },
        } = getState()

        const config = {
            headers: {
                'Content-type': 'application/json',
                Authorization: `Bearer ${userInfo.access}`
            }
        }

        const { data } = await axios.get(
            '/api/user/details',
            config
        )
  
        dispatch(detailsFufilled(data));
    }
    catch(error){
        console.log(error)
    }
}