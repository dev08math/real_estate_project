import { Backdrop, CircularProgress, Stack, Typography } from '@mui/material'
import React from 'react'

export default function LoadingScreen({message}) {
  return (
    <div>
        <Backdrop  sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }} open={true}> 
            <Stack gap={1} justifyContent="center" alignItems="center">
            <CircularProgress color="error" />
            <Typography>{message}</Typography>
            </Stack>
         </Backdrop>
    </div>
  )
}
