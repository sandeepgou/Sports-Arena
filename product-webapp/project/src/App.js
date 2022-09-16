import React from 'react'
import EditProfilePlayer from './Pages/EditProfilePlayer';
import EditProfileVendor from './Pages/EditProfileVendor';
import PlayerBookings from './Pages/PlayerBookings';
import VendorBookings from './Pages/VendorBookings';
import GroundList from './Pages/GroundList';
import Header from './Components/Header';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import './Assets/css/style.css';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import HeaderPlayer from './Components/HeaderPlayer';
import SlotCreation from './Pages/SlotCreation';
import TeamUp from './Pages/TeamUp';
import Payment from './Pages/Payment';
import Footer from './Components/Footer';
import ChatFeature from './Pages/ChatFeature';
import { BrowserRouter as Router, Link, Routes, Route } from 'react-router-dom'
import DetailView from './Pages/detailview';
import CardPayment from './Pages/CardPayment';
import Home from './Components/Home';
import HeaderVendors from './Components/HeaderVendor';
import Signup from './Components/signup';
import Login from './Components/Login';
import {useState,useEffect} from 'react'

function App() {
  const [email,setEmail] = useState("")
  useEffect(()=>{
    let emailid=sessionStorage.getItem("email")
    setEmail(emailid)
  },[email])
  
  return (
    
    // <div className="App">
    //   <header className="App-header">
    //     <img src={logo} className="App-logo" alt="logo" />
    //     <p>
    //       Edit <code>src/App.js</code> and save to reload.
    //     </p>
    //     <a
    //       className="App-link"
    //       href="https://reactjs.org"
    //       target="_blank"
    //       rel="noopener noreferrer"
    //     >
    //       Learn React
    //     </a>
    //   </header>
    // </div>
    <>

    {/* <HeaderPlayer/> */}
     {/* <Header/>  */}
     {/* <TeamUp/> */}
     
   
    {(!email)?<Header setEmail={setEmail}/>:(sessionStorage.getItem("role")=="newplayer")?<HeaderPlayer setEmail={setEmail}/>:<HeaderVendors setEmail={setEmail}/>}
    {/* <VendorBookings/> */}
       {/* <TeamUp/> */}
    {/* <HeaderPlayer/> */}
     {/* <Header/>  */}
    {/* <HeaderVendors/> */}
    <Routes>
      <Route exact path='/' element={<Home/>}/>
      {/* <Route path="/login" element={<Login setEmail={setEmail}/>}/>
      <Route path="/singup" element={<Signup/>}/>  */}
      <Route path="/editprofileplayer" element={<EditProfilePlayer />} />
      <Route path="/editprofilevendor" element={<EditProfileVendor/>} />
      <Route path="/slotcreation" element={<SlotCreation/>} />
      <Route path="/list"  element={<GroundList/>}/>
      <Route path="/detailview" element={<DetailView/>}/>
      <Route path="payment" element={<CardPayment/>}/>
    <Route path="/teamUp" element={<TeamUp/>} />
    <Route path="/playerbooking" element={<PlayerBookings/>} />
    <Route path="/vendorbooking" element={<VendorBookings/>} />

  <Route path="/chatFeature" element={<ChatFeature />} />
  </Routes>
  {/* <CardPayment/> */}
    <Footer/>


    </>
    
  );
}

export default App;


