import { Autocomplete, Chip, TextField } from '@mui/material';
import React, { useRef, useState } from 'react'
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchsearch, updateSearchTerms } from '../redux/features/search/actions';
import SearchIcon from '@mui/icons-material/Search';
import InputAdornment from "@material-ui/core/InputAdornment";
import { Link } from 'react-router-dom';
import { fetchProperties } from '../redux/features/proplist/actions';

export default function SearchBar() {
    const [list, setList] = useState([]);
    const dispatch = useDispatch();
    const searchResults = useSelector((state) => state.searchResults);
    const { error, loading, searchData, searchTerms} = searchResults;
    const searchKey = useRef("");
    const [searchList, setSearchList] = useState(searchTerms)

    function onAddtion(value) {
        setSearchList([...searchList, value]);
        dispatch(updateSearchTerms(searchList))
      }

    const onDeletion = (value) => {
        console.log(value);
        let val = "";

       setSearchList(() => {
            let i = 0, newList = searchList.slice();
            while (i < newList.length) {
                if (newList[i].title === val) newList.splice(i, 1);
                else i++;
            }
            return newList;
        });
        dispatch(updateSearchTerms(searchList))
    };

    useEffect(() => {
      if(searchData) 
      setList(searchData)
    }, [searchData])
    

    const onSearch = (value) =>{
      if(value.nativeEvent.data) 
        searchKey.current = searchKey.current + value.nativeEvent.data;
      else if(searchKey.current.length) 
        searchKey.current = searchKey.current.substring(0, searchKey.current.length-1)
      dispatch(fetchsearch(searchKey.current));
      // console.log(searchData)
    }

    const clickHandler = () =>{
      dispatch(fetchProperties());
    }

  return (
    <div>
        <Autocomplete
            multiple
            sx={{maxHeight:'60px'}}
            onChange={(event, value, reason) => {
              console.log("event: ", event);
              console.log("value : ", value);
              console.log("reason: ", reason);

              if (reason === "createOption" || reason === "selectOption")
                onAddtion(value);
              else if (reason === "removeOption") onDeletion(value);
            }}
            id="tags-filled"
            options={list}
            freeSolo
            onInputChange={onSearch}
            renderTags={(value, getTagProps) =>
              value.map((option, index) => (
                <Chip
                  color="error"
                  variant="outlined"
                  label={option}
                  {...getTagProps({ index })}
                />
              ))
            }
            renderInput={(params) => (
              <TextField {...params}
                         variant="filled" 
                         placeholder={searchList.length > 0 ? "" : "Your desired place(s) ?" } 
                         InputProps={{
                          ...params.InputProps,
                          endAdornment: (
                            <InputAdornment position="end" onClick={clickHandler}>
                              <Link to={"/listings"}>
                                <SearchIcon />
                              </Link>
                            </InputAdornment>
                          )
                        }} />
            )}
          />
    </div>
  )
}
