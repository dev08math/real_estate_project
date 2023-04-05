import axios from 'axios'
import { modifySearchTerms, searchFufilled, searchPending, searchRejected } from './searchSlice';

export const fetchsearch = (searchKey) => async dispatch =>{
    dispatch(searchPending());

    try {
        console.log(searchKey)
        const word = searchKey
        const config = {
            headers: {
                'Content-type': 'application/json'
            },
            params: {word}
        }

        const { data } = await axios.get(
            '/api/properties/typeAhead',
            config
        )
        // console.log(data);
        dispatch(searchFufilled(data));
    }
    catch(error){
        console.log(error)
        dispatch(searchRejected(error.response.data))
    }
}

export const updateSearchTerms = (data) => async dispatch =>{
    dispatch(modifySearchTerms(data));
    localStorage.setItem('searchTerms', JSON.stringify(data))
}