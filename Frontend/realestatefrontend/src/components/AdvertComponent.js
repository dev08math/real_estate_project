import { Typography } from '@mui/material';
import React from 'react'
import Slider from 'react-slick';

export default function AdvertComponent() {
    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        vertical: true,
        verticalSwiping: true,
        variableWidth: true,
        swipeToSlide: true,
        slidesToShow: 3,
        slidesToScroll: 1,
        edgeFriction: 0.15,
        autoplay: true,
        autoplaySpeed: 2000,
    };

  return (
    <div>
        <Slider {...settings}>
            <Typography variant='h5'>
                Random Advertisement 1
            </Typography>
            <Typography variant='h5'>
                Random Advertisement 2
            </Typography>
            <Typography variant='h5'>
                Random Advertisement 3
            </Typography>
        </Slider>
    </div>
  )
}
