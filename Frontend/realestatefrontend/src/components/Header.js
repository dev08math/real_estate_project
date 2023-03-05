import { React, useEffect, useState } from "react";
import {
  AppBar,
  Box,
  Toolbar,
  Typography,
  Menu,
  Container,
  Avatar,
  Button,
  Tooltip,
  MenuItem,
  IconButton,
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import AddHomeIcon from "@mui/icons-material/AddHome";
import AddHomeRoundedIcon from "@mui/icons-material/AddHomeRounded";
import HolidayVillageIcon from '@mui/icons-material/HolidayVillage';
import { Link, useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";

const pages = ["Products", "Pricing", "Blog"];
const settings = ["Profile", "Account", "Dashboard", "Logout"];

function Header() {
  const [anchorElNav, setAnchorElNav] = useState(null);
  const [anchorElUser, setAnchorElUser] = useState(null);
  const [bgStyle, setbgStyle] = useState({'textColor':{}, 'bgColor':{}});
  const location = useLocation();

  useEffect(() => {
    if(location.pathname === '/'){
      setbgStyle({
        'textColor':{color: 'crimson'},
        'bgColor':{background: 'transparent'}})}
    else{
      setbgStyle({
      'textColor':{color: 'white'},
      'bgColor':{background: 'crimson'}})}
  }, [location]);
  
  const userLogin = useSelector((state) => state.userLogin)
  const {userInfo} = userLogin
  const dispatch = useDispatch();

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };
  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  return (
    <AppBar position="static" style={{...bgStyle.bgColor}}>
      <Container maxWidth="xl">
        <Toolbar disableGutters>
            <HolidayVillageIcon sx={{ fontSize: 40, display: { xs: 'none', md: 'flex' }, mr: 1, ...bgStyle.textColor }} />
          <Typography
            variant="h6"
            noWrap
            component="a"
            href="/"
            sx={{
              mr: 2,
              display: { xs: 'none', md: 'flex' },
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.3rem',
              textDecoration: 'none',
              fontSize: 40,
              ...bgStyle.textColor 
            }}
          >
            iEstator
          </Typography>

          <Box sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}>
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
              color="inherit"
            >
              <MenuIcon />
            </IconButton>
            <Menu
              id="menu-appbar"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: "bottom",
                horizontal: "left",
              }}
              keepMounted
              transformOrigin={{
                vertical: "top",
                horizontal: "left",
              }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
              sx={{
                display: { xs: "block", md: "none" },
              }}
            >
              {pages.map((page) => (
                <MenuItem key={page} onClick={handleCloseNavMenu}>
                  <Typography textAlign="center">{page}</Typography>
                </MenuItem>
              ))}
            </Menu>
          </Box>

          <Box
            justifyContent="flex-end"
            p={2}
            sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}
          >
            <Button variant="contained" sx={{backgroundColor: 'transparent'}} >
              <AddHomeIcon />
              New Property
            </Button>
          </Box>

          <Box
            justifyContent="flex-end"
            p={2}
            sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}
          >
            <Button variant="contained" color="success">
              <AddHomeRoundedIcon />
            </Button>
          </Box>

          {userInfo && userInfo.username ? (
            <Box sx={{ flexGrow: 0 }}>
            <Tooltip title="Open settings">
              <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                <Avatar alt="User Dp" src={userInfo.dp} />
              </IconButton>
            </Tooltip>
            <Menu
              sx={{ mt: '45px' }}
              id="menu-appbar"
              anchorEl={anchorElUser}
              anchorOrigin={{
                vertical: 'top',
                horizontal: 'right',
              }}
              keepMounted
              transformOrigin={{
                vertical: 'top',
                horizontal: 'right',
              }}
              open={Boolean(anchorElUser)}
              onClose={handleCloseUserMenu}
            >
              {settings.map((setting) => (
                <MenuItem key={setting} onClick={handleCloseUserMenu}>
                  <Typography textAlign="center">{setting}</Typography>
                </MenuItem>
              ))}
            </Menu>
          </Box> 
          ) : (
            <Box justifyContent="flex-end" style={{ bgcolor: "white" }}>
            <Button variant="contained" sx={{backgroundColor: 'transparent'}} >
              <Link to={'/login'} style={{ textDecoration: "none", color: "white" }}>
                Login
              </Link>
            </Button>
          </Box>
          )}
          
        </Toolbar>
      </Container>
    </AppBar>
  );
}
export default Header;
