import React, { useEffect, useState } from 'react';
import { FaMapMarkerAlt } from "react-icons/fa";
import {FaRegCalendarCheck} from "react-icons/fa";
import {FaRegClock} from "react-icons/fa";
import { getPlayerBookings,changeSlotStatus,bookingCancel,getPlayerBookingsByStatus ,getPlayerBookingsByDate} from '../Services/BookingService.service';
import moment from 'moment';
import ReactPaginate from 'react-paginate';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import Pagination from 'react-bootstrap/Pagination';
import { BsFillArrowLeftSquareFill } from "react-icons/bs";
import { BsFillArrowRightSquareFill } from "react-icons/bs";import axios from 'axios';
;

export default function PlayerBookings() {
  const loggedIn = "sayali1604meshram@gmail.com";
  const [condition,setCondition]=useState('BOOKED');
  const [data,setData]=useState([]);
  const [filteredData,setFilteredData]=useState([]);
  const [selDate,setSelDate] = useState();
  const [bookingData,setBookingData] = useState([]);
  
  const [currentPage, setCurrentPage] = useState(0);
  const [itemOffset, setItemOffset] = useState(0);


  const today = moment().format("YYYY-MM-DD");
  const cTime = moment().format("HH:mm");

  const [currTime, setCurrTime] = useState(cTime);

  const notify = () => toast.success("Booking CANCELLED successfully!");
  const error_notify=()=>toast.error('🦄 Sorry!!! Some error has occured.');

  const updateTime = ()=>{
    let cTime = moment().format("HH:mm");
    setCurrTime(cTime);
  }
  setInterval(updateTime,1000); 

  useEffect(() => {
    setCondition("BOOKED"); 
    console.log(condition)
    getPlayerBookings(sessionStorage.getItem("email"),(res) => {
      setFilteredData(res.data);
      setBookingData(res.data);
      
    }, (err) => {
      console.log("Error", err);
    })
  }, [itemOffset, 8]);
  // ***********Pagination Stuff***********//
      const PER_PAGE = 8;
      const offset = currentPage * PER_PAGE;
      const currentPageData = filteredData.slice(offset, offset + PER_PAGE);

      const pageCount = Math.ceil(filteredData.length / PER_PAGE);

      function handlePageClick({ selected: selectedPage }) {
        setCurrentPage(selectedPage);
      }
  //***************END Pagination Stuff******************//
  function filterData(status){
    
    setCondition(status);
    getPlayerBookingsByStatus(sessionStorage.getItem("email"),status,(res)=>{
      setFilteredData(res.data);
      setCurrentPage(0);
    },(err)=>{
      console.log(err);
    })

  } 
  function getPastBookings(){

    setCondition("PastBooking");
    
    console.log("Today ",today);

    getPlayerBookingsByStatus(sessionStorage.getItem("email"),"BOOKED",(res)=>{
      console.log(res.data);
      const updatedData=res.data.filter((currElem)=>{
        
        return currElem.slotDate < today;
      });
      setFilteredData(updatedData);
    },(err)=>{
      console.log("Error",err);
    })
    
  }
  function handleChange(e){
      console.log(e.target.value);
      setSelDate(e.target.value); 
  }
  function handleSubmit(e){
    e.preventDefault();
    //setCondition("Search");
    var newfromdate = moment(selDate, "YYYY-MM-DD").format("YYYY-MM-DD");
    console.log("New Date",newfromdate);
    if(condition == "BOOKED" || condition == "CANCELLED"){
      getPlayerBookingsByDate(condition,newfromdate,(res)=>{
       
        setFilteredData(res.data);
      },(err)=>{
        console.log(err);
      });
    }else{
      const dataByDate=filteredData.filter((currElem)=>{
        return currElem.slotDate == newfromdate;
      });
      setFilteredData(dataByDate);
    }
    
  }

  /***************Cancel Booking***************** */
  function cancelBooking(bookingID){
    //alert(cId);
   const data = {status:"CANCELLED"} 
  //  setBookingData({...bookingData,status:"CANCELLED"})
   bookingCancel(bookingID,"BOOKED",(res)=>{
     //alert(res.status);
     console.log(res);
     notify();
     filterData('BOOKED');
       //alert("Booking CANCELLED successfully");
       let data1 = {status:"AVAILABLE"};
      //  axios.get(`http://localhost:8080/booking-Management/api/v1/slot/slotID/`+sId)
      //  .then((res)=>{
      //   res.data.status="AVAILABLE"
      //   changeSlotStatus(res.data,(res1)=>{
          
      //       notify();
      //       filterData('BOOKED');
          
          
      //   },(err)=>{
      //    console.log("Error :",err);
      //    error_notify();
      //     //alert("error 1",{err});
      //   })
      //  })
       
       
       //window.location.reload(false);
     
   },(err)=>{
     alert(err);
     console.log(err);
   })
}
/**********************End Cancel Booking******* */
  
  return (
    
    <div className='container-fluid mt-5 p-5'>
      
      <h1 className='text-center mb-5'>Bookings</h1>
      <div className='row'>
        <div className='col-12 col-lg-8'>
          <div className='d-flex justify-content-center'>
            {(condition=="BOOKED")?<button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block' style={{backgroundColor:"#4285F4",color:"#f2f2f2"}} onClick={()=>{filterData('BOOKED')}}>Booked</button>
            :<button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block'  onClick={()=>{filterData('BOOKED')}}>Booked</button>}
            
            {(condition=="BOOKED")?<button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none' style={{backgroundColor:"#4285F4",color:"#f2f2f2"}} onClick={()=>{filterData('BOOKED')}}>Booked</button>:
            <button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none' onClick={()=>{filterData('BOOKED')}}>Booked</button>}
            {/* <button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block' style={{backgroundColor:"#0f0f0a",color:"#f2f2f2"}} onClick={()=>{filterData('BOOKED')}}>BOOKED</button> */}
            
            {/* <button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none' onClick={()=>{filterData('BOOKED')}}>BOOKED</button> */}
            
            
            {(condition=="CANCELLED")?<button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block' style={{backgroundColor:"#4285F4",color:"#f2f2f2"}} onClick={()=>{filterData('CANCELLED')}}>Cancelled</button>
            :<button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block' onClick={()=>{filterData('CANCELLED')}}>Cancelled</button>}
            
            {(condition=="CANCELLED")?<button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none' style={{backgroundColor:"#4285F4",color:"#f2f2f2"}}onClick={()=>{filterData('CANCELLED')}}>Cancelled</button>:
            <button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none' onClick={()=>{filterData('CANCELLED')}}>Cancelled</button>}
            {/* <button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block' onClick={()=>{filterData('CANCELLED')}}>CANCELLED</button> */}
            {/* <button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none' onClick={()=>{filterData('CANCELLED')}}>CANCELLED</button> */}
            

            {(condition=="PastBooking")?<button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block' style={{backgroundColor:"#4285F4",color:"#f2f2f2"}} onClick={()=>{getPastBookings()}}>Past Bookings</button>:
            <button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block' onClick={()=>{getPastBookings()}}>Past Bookings</button>}
            
            {(condition=="PastBooking")?<button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none' style={{backgroundColor:"#4285F4",color:"#f2f2f2"}} onClick={()=>{getPastBookings()}}>Past Bookings</button>:
            <button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none' onClick={()=>{getPastBookings()}}>Past Bookings</button>}
            {/* <button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block' onClick={()=>{getPastBookings()}}>Past Bookings</button> */}
            {/* <button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none'>Past Bookings</button> */}
          </div>
        </div>
        <div className='col-12 col-lg-3'> 
          
            <form onSubmit={handleSubmit}>
            <div className="row">
            <div className='col-sm-12 col-md-9 my-3 my-lg-0'>
            <input type="date" name="selDate" value={selDate} onChange={handleChange} className='form-control ' required/>
            </div>

            <div className='col-sm-12 col-md-2 my-3 my-lg-0 text-center'>
            <button className='btn btn-primary'>Search</button>
            </div>
            </div>
            </form>
          
          {/* <input type="date" className='form-control'/> <button className='btn btn-primary'>Search</button> */}
        </div>
      </div>
      {/* Display details in card */}
      
      <div className='row my-5' >
        {//console.log("Testing123",currentPageData)
        /* <div className='col-lg-3 col-md-4 col-sm-12 my-5 mx-sm-0 mx-md-4 outer-div'> */}
        {
          currentPageData && currentPageData.map((elem)=>{
              const {bookingID,slotID,groundName,addressLineOne,city,slotStartTime,slotEndTime,slotDate,status} = elem;
              // console.log(elem);
              return(
                <div className='col-xl-3 col-lg-4 col-md-6 col-sm-12 my-5 '>
                <div className='row'>
                 <div className='col-10 mx-auto outer-div'>
                   <h6 className='text-center my-4'>{groundName}</h6>
                   <div className="row my-2 mx-2">
                     <div className='col-2'><FaMapMarkerAlt /></div>
                     <div className='col-10' data-bs-toggle="tooltip" data-bs-placement="right" title={addressLineOne}>{addressLineOne}, {city}</div>
                   </div>
                   <div className="row my-2 mx-2">
                     <div className='col-2 '><FaRegCalendarCheck /></div>
                     <div className='col-10'>{slotDate}</div>
                   </div>
                   <div className="row my-2 mx-2">
                     <div className='col-2'><FaRegClock /></div>
                     <div className='col-10'>{moment(slotStartTime, "HH:mm").format("hh:mm a")} - {moment(slotEndTime, "HH:mm").format("hh:mm a")}</div>
                   </div>
                   <div className='row my-4'>
                    {/* <BookDiv/>
                    <CancelDiv/> */}
                    
                     <div className='col-md-6 col-sm-12 text-center my-sm-1'>
                     {condition == "PastBooking"?<strong></strong>:condition == "CANCELLED"?<strong style={{color:"red"}}>{status}</strong>:<strong>{status}</strong>}
                      </div>
                    <div className='col-md-6 col-sm-12 text-center my-sm-1' >
                      {
                        condition=="BOOKED"&&today==slotDate&&currTime<slotStartTime?<button className='btn btn-primary btn-sm mx-auto' onClick={()=>{cancelBooking(bookingID)}}>Cancel</button>:condition=="BOOKED"&&today<slotDate?<button className='btn btn-primary btn-sm mx-auto' onClick={()=>{cancelBooking(bookingID)}}>Cancel</button>:""
                      }
                    </div>
                   </div>
                 </div>
                </div>
             </div>
     
              );
          })
        }
        
                
      </div>
      {/* End card display */}
      
     
      <div className='d-flex justify-content-center'>
      <ReactPaginate 
      pageCount={pageCount}
      onPageChange={handlePageClick}
        breakClassName={'page-item'}
        breakLinkClassName={'page-link'}
        containerClassName={'pagination'}
        pageClassName={'page-item'}
        pageLinkClassName={'page-link'}
        previousClassName={'page-item'}
        previousLinkClassName={'page-link'}
        nextClassName={'page-item'}
        nextLinkClassName={'page-link'}
        activeClassName={'active'}
      />
      
      </div>
      <ToastContainer />
    </div>


  )
}
