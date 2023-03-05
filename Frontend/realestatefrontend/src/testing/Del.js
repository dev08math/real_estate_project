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
        // dots: true,
        // infinite: true,
        // speed: 500,
        // centerMode: true,
        // slidesToShow: 1,
        // slidesToScroll: 1,
        // centerMode: true,
        // variableWidth: true,
        // swipeToSlide: true,
        // edgeFriction: 0.15,
        // autoplay: true,
        // autoplaySpeed: 200,
        // cssEase: "linear"

        // dots: true,
        // infinite: true,
        // slidesToShow: 1,
        // slidesToScroll: 1,
        // autoplay: true,
        // autoplaySpeed: 300,

        // className: "center",
        // centerMode: true,
        // infinite: true,
        // centerPadding: "60px",
        // slidesToShow: 3,
        // speed: 500

        // infinite: true,
        // dots: true,
        // slidesToShow: 1,
        // arrows: true,
        // slidesToScroll: 1,
        // lazyLoad: true
    };

  return (
    // <Stack spacing={2}>
    //     <Box>Add the nav links for the deatils card</Box>
    //     <Grid container spacing={2}>
    //         <Grid item>
          
    //                 <Box sx={{...boxStyle, padding:'20px'}}>
                      
    //                         {/* <Box /> */}
    //                         <Box>
    //                         <Slider {...settings}>
    //                             {imageList.map((image, index) => {
    //                                 {console.log(image)}
    //                                 return (<Box
    //                                 component="img"
    //                                 key={index}
    //                                 sx={{
    //                                 maxHeight: { xs: 233, md: 167 },
    //                                 maxWidth: { xs: 350, md: 250 },
    //                                 borderRadius: '7px'
    //                                 }}
    //                                 alt="The house from the offer."
    //                                 src={image}/>)
    //                             })}
    //                         </Slider>
    //                         </Box>
                            
    //                         {/* <Box /> */}
                     
    //                 </Box>
                    
    //         </Grid>
    //     </Grid> 
    // </Stack>

    <div>
    <Slider {...settings}>
      {imageList.map((item) => (
        <div key={item}>
          <img src={item}  alt='ytyt' />
        </div>
      ))}
    </Slider>
  </div>
//   <div className="App">
//   test
//   <Slider {...settings}>
//     <Box>
//       <h3>1</h3>
//     </Box>
//     <div>
//       <h3>2</h3>
//     </div>
//     <div>
//       <h3>3</h3>
//     </div>
//   </Slider>
// </div>
  )
}
