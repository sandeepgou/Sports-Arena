import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import NavDropdown from 'react-bootstrap/NavDropdown'
import Container from 'react-bootstrap/Container'
import Login from './Login';
import Signup from './signup';
import Col from "react-bootstrap/esm/Col";
import '../Assets/css/style.css';
import signup from './signup';
// import Login from './Login';

export default function Header({setEmail}) {
 
  return (
    <Navbar collapseOnSelect expand="lg" variant="dark" className='header-background'>
  <Container>
  <div className='col-1'>
    <img className="image img-fluid" src={require('../Assets/images/logo.jpeg')}  fluid alt="logo" />
    </div>            
  <Navbar.Toggle aria-controls="responsive-navbar-nav" />
  <Navbar.Collapse id="responsive-navbar-nav">
    
    <Nav className="ms-auto">
      
      <Login setEmail={setEmail}/> 
      <Signup />
     
    </Nav>
  </Navbar.Collapse>
  </Container>
</Navbar>
  )
}

