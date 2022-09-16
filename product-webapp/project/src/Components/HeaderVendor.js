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
import { FaUser } from "react-icons/fa";
// import Login from './Login';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

export default function HeaderVendors({ setEmail }) {
  const navigate = useNavigate();
  const logout = () => {
    sessionStorage.removeItem("email")
    sessionStorage.removeItem("role")
    sessionStorage.removeItem("JWT")
    sessionStorage.removeItem("groundID")
    setEmail("");
    navigate('/')
  }
  return (
    <Navbar collapseOnSelect expand="lg" variant="dark" className='header-background'>
      <Container>

        <img className='col-1' src={require('../Assets/images/logo.jpeg')} fluid alt="logo" />

        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">

          <Nav className="ms-auto">
            {/* <Nav>Team Up</Nav>
      <Nav>Bookings</Nav> */}
            <Link className="nav-link" style={{ color: "white" }} to={'/slotcreation'}>
              <strong>Slots</strong>
            </Link>
            <Link className="nav-link" style={{ color: "white" }} to={'/vendorbooking'}>
              <strong>Bookings</strong>
            </Link>
            <Link className="nav-link" style={{ color: "white" }} to={'/editprofilevendor'}>
              <strong>My Profile</strong>
            </Link>
            <button className='btn' style={{ color: "white" }} onClick={logout}> <strong> Logout</strong> </button>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  )
}