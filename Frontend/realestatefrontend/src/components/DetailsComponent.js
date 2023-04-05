import { Box, List, ListItem, Stack, Typography } from '@mui/material'
import React from 'react'

export default function DetailsComponent(props) {
  return (
    <Box sx={{...props.style, padding:'20px'}}>
                        <Stack spacing={32} direction='row'>
                            <Box/>
                            <Typography variant="h5" color='error'>{props.Heading}</Typography>
                            <Box/>
                        </Stack>
                        <></>
                        <Stack spacing={2}>
                            <Box />
                            <Stack spacing={1} direction='row'>

                                <List sx = {{listStyleType: 'disc', pl: 2,}}>
                                    {Object.entries(props.entries).map(entry =>{
                                        return (<ListItem sx = {{display: 'list-item'}} key={entry[0]}>
                                            <Typography>
                                                <strong>{entry[0]}</strong>
                                            </Typography>
                                        </ListItem>)
                                    })}
                                </List>
                                
                                <List sx = {{pl: 2}}>
                                    {Object.entries(props.entries).map(entry =>{
                                        return (<ListItem sx = {{display: 'list-item'}} key={entry[0]}>
                                            <Typography>{entry[1]}</Typography>
                                        </ListItem>)
                                    })}
                                </List>

                            </Stack>
                            <Box />
                        </Stack>
                    </Box>
  )
}
