import { Grid, Box, Typography, List, ListItem} from '@mui/material'
import {Stack } from '@mui/system'
import React from 'react'
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

export default function PropertyDetails() {
    const boxStyle = {
        bgcolor: 'background.color',
        border: 1,
        borderTop: 20,
        borderBottom: 16,
        borderColor: 'error.main',
        ':hover': {
          boxShadow: 10,
        },
        borderRadius: '7px',
        display: 'flex'
    };

    const imageList = ['https://assets-news.housing.com/news/wp-content/uploads/2022/01/11171103/World%E2%80%99s-15-Most-Beautiful-Houses-That-Will-Leave-You-Awestruck-01.jpg',
                       'https://assets-news.housing.com/news/wp-content/uploads/2022/01/11171226/World%E2%80%99s-15-Most-Beautiful-Houses-That-Will-Leave-You-Awestruck-02.png',
                       'https://assets-news.housing.com/news/wp-content/uploads/2022/01/10150003/most-beautiful-houses1.png',
                       'https://assets-news.housing.com/news/wp-content/uploads/2022/01/11171313/World%E2%80%99s-15-Most-Beautiful-Houses-That-Will-Leave-You-Awestruck-03.jpg',
                       'https://assets-news.housing.com/news/wp-content/uploads/2022/01/11172632/World%E2%80%99s-15-Most-Beautiful-Houses-That-Will-Leave-You-Awestruck-04.jpg' ];
    
    const details = {
        "Name" : "Mathew Prince",
        "ID" : "15724X",
        "feifhihihri" : "fehifheifej",
        "ckjdkc" : "eicudhfvuiofhvfeovheovhefovehnvjoefnv",
        "Description" : "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset shee"
    }

    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        centerMode: true,
        slidesToShow: 1,
        slidesToScroll: 1,
        centerMode: true,
        variableWidth: true,
        swipeToSlide: true,
        edgeFriction: 0.15,
        autoplay: true,
        autoplaySpeed: 2000,
    };

  return (
    <Stack spacing={2}>
        <Box>Add the nav links for the deatils card</Box>
        <Grid container spacing={2}>
            <Grid item xs={6}>
                <Stack spacing={2} direction="column">
                    <Box sx={{...boxStyle, padding:'20px'}}>
                        <Stack spacing={38} direction='row'>
                            <Box/>
                            <Typography variant="h5" color='error'>OVERVIEW</Typography>
                            <Box/>
                        </Stack>
                        <></>
                        <Stack spacing={2} direction='row' sx={{padding:'20px'}}>
                        <Box
                            component="img"
                            sx={{
                            height: 233,
                            width: 350,
                            // maxHeight: { xs: 233, md: 167 },
                            // maxWidth: { xs: 350, md: 250 },
                            borderRadius: '7px'
                            }}
                            alt="The house from the offer."
                            src="https://images.unsplash.com/photo-1512917774080-9991f1c4c750?auto=format&w=350&dpr=2"/>
                        <Box>
                        </Box> 
                        <Stack spacing={5}>

                            <Box  justifyContent="center" alignItems="center" sx={{flexGrow: 1, textAlign:'center'}}>
                            <Typography sx={{ padding:'5px', opacity:0.85, fontSize:20}}>
                            3BHK | FLAT | 99sqft | Near Delhi
                            </Typography>

                            <Box display="flex" justifyContent="center" alignItems="center" sx={{flexGrow: 1, textAlign:'center'}}>
                                <Stack direction='row' spacing={6}>

                                    <Box>
                                        <Stack spacing={3}>
                                            <Stack>
                                                <Typography variant="h6" color='error'>Floor</Typography>
                                                <Typography>4</Typography>
                                            </Stack>
                                            <Stack>
                                                <Typography variant="h6" color='error'>Status</Typography>
                                                <Typography>Vacant</Typography>
                                            </Stack>
                                        </Stack>
                                    </Box>

                                    <Box>
                                        <Stack spacing={3}>
                                            <Stack>
                                                <Typography variant="h6" color='error'>Type</Typography>
                                                <Typography>Apartment</Typography>
                                            </Stack>
                                            <Stack>
                                                <Typography variant="h6" color='error'>Posted Date</Typography>
                                                <Typography>12/12/2021</Typography>
                                            </Stack>
                                        </Stack>
                                    </Box>

                                </Stack>
                            </Box>
                        </Box>
                        </Stack>

                        </Stack>
                    </Box>

                    <Box sx={{...boxStyle, padding:'20px'}}>
                        <Stack spacing={38} direction='row'>
                            <Box/>
                            <Typography variant="h5" color='error'>PHOTOS</Typography>
                            <Box/>
                        </Stack>
                        <></>
                        <Stack spacing={2}>
                            <Box />
                            <Slider {...settings}>
                                {imageList.map((image, index) => {
                                    // {console.log(image)}
                                    return (<Box
                                    component="img"
                                    key={index}
                                    sx={{
                                    maxHeight: { xs: 233, md: 167 },
                                    maxWidth: { xs: 350, md: 250 },
                                    borderRadius: '7px'
                                    }}
                                    alt="The house from the offer."
                                    src={image}/>)
                                })}
                            </Slider>
                            <Box />
                        </Stack>
                    </Box>
                    
                    <Box sx={{...boxStyle, padding:'20px'}}>
                        <Stack spacing={32} direction='row'>
                            <Box/>
                            <Typography variant="h5" color='error'>MORE DETAILS</Typography>
                            <Box/>
                        </Stack>
                        <></>
                        <Stack spacing={2}>
                            <Box />
                            <Stack spacing={1} direction='row'>

                                <List sx = {{listStyleType: 'disc', pl: 2,}}>
                                    {Object.entries(details).map(entry =>{
                                        return (<ListItem sx = {{display: 'list-item'}} key={entry[0]}>
                                            <Typography>
                                                <strong>{entry[0]}</strong>
                                            </Typography>
                                        </ListItem>)
                                    })}
                                </List>
                                
                                <List sx = {{pl: 2}}>
                                    {Object.entries(details).map(entry =>{
                                        return (<ListItem sx = {{display: 'list-item'}} key={entry[0]}>
                                            <Typography>{entry[1]}</Typography>
                                        </ListItem>)
                                    })}
                                </List>

                            </Stack>
                            <Box />
                        </Stack>
                    </Box>
                    
                    
                </Stack>
            </Grid>
            <Grid item xs={4}>
                <Box>Add the map component here </Box>
            </Grid>
        </Grid> 
    </Stack>
  )
}
