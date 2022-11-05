import React from 'react'
import Paper from '@mui/material/Paper';
import { styled } from '@mui/material/styles';
import { Stack } from '@mui/system';

export default function ProdList({prodList}) {
    // console.log(prodList, prodList.length);
    const Item = styled(Paper)(({ theme }) => ({
      backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
      ...theme.typography.body2,
      padding: theme.spacing(1),
      textAlign: 'center',
      color: theme.palette.text.secondary,
    }));
  return (
    prodList ?
    <Stack spacing={2}> 
        {prodList.map(product => (
          <Item key={product.id}>
               {product.title}
          </Item>
        ))}
    </Stack> : <> {""} </>
  )
}
