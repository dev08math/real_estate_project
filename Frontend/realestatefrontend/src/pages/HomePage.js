import { Grid } from '@mui/material'
import React from 'react'
import Background from '../components/Background'
import SearchBar from '../components/SearchBar'

export default function HomePage() {
  return (
    <Grid container spacing={10}>
      <Grid item xs={12}>
        <Background />
      </Grid>
      <Grid item xs={12}></Grid>
      <Grid item xs={12}></Grid>
      <Grid item xs={12}></Grid>
      <Grid item xs={3}></Grid>
      <Grid item xs={6}>
        <SearchBar />
      </Grid>
    </Grid>
  )
}
