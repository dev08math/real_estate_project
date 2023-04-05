import { amenitiesDetailsUpdate, localityDetailsUpdate, mainDetailsUpdate, rentalDetailsUpdate } from './propDetailsSlice';

export const updateMainDetails = (data) => (dispatch, getState) =>{
    dispatch(mainDetailsUpdate(data));
    localStorage.setItem('propertyDetails.mainDetails', JSON.stringify(data))  
}

export const updateLocalityDetails = (data) => (dispatch, getState) =>{
    dispatch(localityDetailsUpdate(data));
    localStorage.setItem('propertyDetails.localityDetails', JSON.stringify(data))  
}

export const updateRentalDetails = (data) => (dispatch, getState) =>{
    dispatch(rentalDetailsUpdate(data));
    localStorage.setItem('propertyDetails.rentalDetails', JSON.stringify(data))  
}

export const updateAmenitiesDetails = (data) => (dispatch, getState) =>{
    dispatch(amenitiesDetailsUpdate(data));
    localStorage.setItem('propertyDetails.amenitiesDetails', JSON.stringify(data))  
}