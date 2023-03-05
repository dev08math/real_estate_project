import React, { useRef, useState } from 'react'
import Checkbox from '@mui/material/Checkbox';
import { Box, Button, FormControlLabel, FormGroup, Slider, Stack, Typography } from '@mui/material';

export default function Filter({setFilters}) {
  const [range, setrange] = useState([0, 100]);
  const enabled = useRef(false);

  const handlePropertyType = (event) =>{
    const { value, checked } = event.target;
    if(checked) setFilters[0]((prevState) => ([...prevState, value]));
    else setFilters[0]((prevState) => prevState.filter((e) => e !== value)); 
  }

  const handleBhkType = (event) =>{
    const { value, checked } = event.target;
    if(checked) setFilters[1]((prevState) => ([...prevState, value]));
    else setFilters[1]((prevState) => prevState.filter((e) => e !== value)); 
  }

  const handlePrice = (event) =>{
    setFilters[2](event.target.value);
    setrange(event.target.value);
  }

  const handleFurnishing = (event) =>{
    const { value, checked } = event.target;
    if(checked) setFilters[3]((prevState) => ([...prevState, value]));
    else setFilters[3]((prevState) => prevState.filter((e) => e !== value)); 
  }

  const handleAge = (event) => {
    const { value, checked } = event.target;
    if(checked) setFilters[4]((prevState) => ([...prevState, value]));
    else setFilters[4]((prevState) => prevState.filter((e) => e !== value)); 
  }
  
  const handlePhotos = () =>{
    setFilters[5]((prevState) => (!prevState));
    enabled.curPrice = !enabled.curPrice;
  }

  const boxStyle = {
    bgcolor: 'background.color',
    border: 3,
    borderColor: 'error.main',
    ':hover': {
      boxShadow: 10,
    },
    borderRadius: '7px'
  };

  const buttonStyleDisable = {
    ":hover": {
      backgroundColor: "forestgreen",
      color:'white'
    },
  };

  const buttonStyleEnable = {
    ":hover": {
      backgroundColor: "crimson",
      color:'white'
    },
  };

  console.log(enabled)

  return (
    <Box  sx={{...boxStyle, width:'320px'}}>
      <Box style={{display: 'flex', justifyContent: 'center', marginTop:4}}>
        <Button variant='contained' size='small' color='error' style={{textTransform: 'none'}}>
          <Typography sx={{color: 'white'}}>
            Filters
          </Typography>
        </Button>
      </Box>
      <Stack spacing={2} sx={{ maxHeight:"500px", overflow: 'auto' }}>
        <Box sx={{ mx:1, marginTop:1}}>
          <Typography>
            <strong> Property Type</strong> 
          </Typography>
          <FormGroup >
            <FormControlLabel control={<Checkbox size='small'  value={"Office"} onChange={handlePropertyType}/>} label="Office" />
            <FormControlLabel control={<Checkbox size='small' value={"Villa"} onChange={handlePropertyType}/>} label="Villa" />
            <FormControlLabel control={<Checkbox size='small' value={"Apartment"} onChange={handlePropertyType}/>} label="Apartment" />
            <FormControlLabel control={<Checkbox size='small' value={"Gated Community"} onChange={handlePropertyType}/>} label="Gated Community" />
          </FormGroup>
        </Box>

        <Box sx={{ mx:1}}>
        <Typography sx={{mx:1, display: 'inline-block'}}>
            <strong> Photos Only?</strong>
            {enabled.curPrice ? <Button size='small' sx={{...buttonStyleEnable, mx:2}} onClick={handlePhotos}>
                                  <Typography sx={{fontSize:14}}>
                                    Disable
                                  </Typography>
                                </Button> : 
                                <Button size='small' sx={{...buttonStyleDisable, mx:2}} onClick={handlePhotos}>
                                  <Typography  sx={{ fontSize:14}}>
                                    Enable
                                  </Typography>
                                </Button>} 
            
          </Typography >
        </Box>
        
        <Box sx={{ mx:1}}>
          <Typography sx={{ mx:1}}>
            <strong> BHK Type</strong> 
          </Typography >
          <FormGroup style={{ display: 'flex', flexDirection: 'row', mx:2}}>
            <FormControlLabel style={{ marginLeft:1, marginTop:2}} control={<Checkbox size='small' value={"1BHK"} onChange={handlePropertyType}/>} label="1BHK" />
            <FormControlLabel control={<Checkbox size='small' value={"2BHK"} onChange={handlePropertyType}/>} label="2BHK" />
            <FormControlLabel control={<Checkbox size='small' value={"3BHK"} onChange={handlePropertyType}/>} label="3BHK" />
            <FormControlLabel style={{ marginLeft:1}} control={<Checkbox size='small' value={"4BHK"} onChange={handlePropertyType}/>} label="4BHK" />
            <FormControlLabel control={<Checkbox size='small' value={"4BHK+"} onChange={handlePropertyType}/>} label="4BHK+" />
          </FormGroup>
        </Box>

        <Box sx={{ mx:1, width:250, marginTop:2}}>
          <Typography sx={{mx:1, display: 'inline-block'}}>
            <strong> Price Range:  </strong> 
            ₹{range[0]} to  ₹{range[1]} K
          </Typography >
          <Slider
            sx={{mx:2}}
            getAriaLabel={() => 'Price Range'}
            value={range}
            onChange={handlePrice}
            disableSwap
          />
        </Box>

        <Box sx={{ mx:1}}>
          <Typography sx={{ mx:1}}>
            <strong> Furnishing</strong> 
          </Typography >
          <FormGroup style={{ display: 'flex', flexDirection: 'row', mx:2}}>
            <FormControlLabel style={{ marginLeft:1, marginTop:2}} control={<Checkbox size='small' value={"Full"} onChange={handleFurnishing}/>} label="Full" />
            <FormControlLabel control={<Checkbox size='small' value={"Semi"} onChange={handleFurnishing}/>} label="Semi" />
            <FormControlLabel control={<Checkbox size='small' value={"None"} onChange={handleFurnishing}/>} label="None" />
          </FormGroup>
        </Box>

         <Box sx={{ mx:1, marginTop:1}}>
          <Typography sx={{ mx:1}}>
            <strong> Property Age</strong> 
          </Typography>
          <FormGroup sx={{ mx:1}}>
            <FormControlLabel control={<Checkbox size='small'  value={"Less than a year"} onChange={handleAge}/>} label="Less than a year" />
            <FormControlLabel control={<Checkbox size='small' value={"1 to 3 years"} onChange={handleAge}/>} label="1 to 3 years" />
            <FormControlLabel control={<Checkbox size='small' value={"3 to 5 years"} onChange={handleAge}/>} label="3 to 5 years" />
            <FormControlLabel control={<Checkbox size='small' value={"5 to 7 years"} onChange={handleAge}/>} label="5 to 7 years" />
            <FormControlLabel control={<Checkbox size='small' value={"5 to 7 years"} onChange={handleAge}/>} label="5 to 7 years" />
            <FormControlLabel control={<Checkbox size='small' value={"Greater than 10 years"} onChange={handleAge}/>} label="Greater than 10 years" />
          </FormGroup>
        </Box>
      
      </Stack>
        
    </Box>
  )
}
