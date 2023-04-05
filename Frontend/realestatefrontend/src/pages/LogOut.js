import { Button, Grid, Typography } from '@mui/material'
import React, { useEffect } from 'react'
import { useDispatch } from 'react-redux'
import { Link } from 'react-router-dom'
import { logOutUser } from '../redux/features/user/userSlice';

export default function LogOut() {
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(logOutUser());
    }, [])
    
  return (
    <Grid container spacing={2}>
        <Grid item xs={12} />
        <Grid item xs={12} />
        <Grid item xs={12} />
        <Grid item xs={12} />
        <Grid item xs={12} />
        <Grid item xs={4} />
        <Grid item xs={6}>
            <Typography variant='h4' style={{color: "crimson"}}>
                You have been successfully logged out.
            </Typography>
        </Grid>
        <Grid item xs={3} />
        <Grid item xs={3} />
        <Grid item xs={6}>
            <Button variant="contained"> 
                <Link to={"/login"}>
                    Sign in again
                </Link>
            </Button>
        </Grid>
    </Grid>
  )
}
