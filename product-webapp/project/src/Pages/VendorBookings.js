import React,{useEffect, useState} from 'react';
import { FaMapMarkerAlt } from "react-icons/fa";
import {FaRegCalendarCheck} from "react-icons/fa";

import {FaRegClock} from "react-icons/fa";
import moment from 'moment';
import ReactPaginate from 'react-paginate';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { getVendorBookings,getVendorBookingsByDate } from '../Services/BookingService.service';
import {BsFillFilePersonFill} from "react-icons/bs";


export default function VendorBookings() {
    const [filteredData,setFilteredData]=useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [condition,setCondition]=useState('BOOKED');
    const today = moment().format("YYYY-MM-DD");
  const cTime = moment().format("HH:mm");
    const [selDate,setSelDate] = useState();

    const loggedIn = sessionStorage.getItem("email");

    useEffect(()=>{
      setCondition("BOOKED"); 
      getVendorBookings(loggedIn,"BOOKED",(res)=>{
        setFilteredData(res.data);
      console.log("Filtered Ddata",filteredData);
      setCurrentPage(0);
    },(err)=>{
      console.log(err);
    })
    }
    ,[])

    // ***********Pagination Stuff***********//
    const PER_PAGE = 6;
    const offset = currentPage * PER_PAGE;
    const currentPageData = filteredData.slice(offset, offset + PER_PAGE);

    const pageCount = Math.ceil(filteredData.length / PER_PAGE);

    function handlePageClick({ selected: selectedPage }) {
      setCurrentPage(selectedPage);
    }
//***************END Pagination Stuff******************//

function filterData(status){
    setCondition(status);
    getVendorBookings(loggedIn,status,(res)=>{
        setFilteredData(res.data);
      console.log("Filtered Ddata",filteredData);
      setCurrentPage(0);
    },(err)=>{
      console.log(err);
    })

  }

  function getPastBookings(status, selectDate){

   setCondition("PastBooking");
    
    console.log("Today ",today);

    getVendorBookings(loggedIn,"BOOKED",(res)=>{
      console.log(res.data);
      const updatedData=res.data.filter((currElem)=>{
        return currElem.slotDate < today;
      });
      setFilteredData(updatedData);
    },(err)=>{
      console.log("Error",err);
    })
    
  }  


    /*************Form handling****** */
    function handleSubmit(e){
        e.preventDefault();
        //setCondition("Search");
        var newfromdate = moment(selDate, "YYYY-MM-DD").format("YYYY-MM-DD");
        console.log("New Date",newfromdate);
        if(condition == "BOOKED" || condition == "CANCELLED"){
          getVendorBookingsByDate(condition,newfromdate,(res)=>{
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
    
    function handleChange(e){
        console.log(e.target.value); 
         setSelDate(e.target.value); 
    }
    /***************End Form Handling********* */

    return (
        <div className='container-fluid mt-5 p-5'>
      <h1 className='text-center mb-5'>Bookings</h1>
      <div className='row'>
        <div className='col-12 col-lg-8'>
          <div className='d-flex justify-content-center'>
            {/* Booked */}
            {(condition==="BOOKED")?<button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block' style={{backgroundColor:"#4285F4",color:"#f2f2f2"}} onClick={()=>{filterData('BOOKED')}}>Booked</button>
            :<button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block'  onClick={()=>{filterData('BOOKED')}}>Booked</button>}
            
            {(condition==="BOOKED")?<button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none' style={{backgroundColor:"#4285F4",color:"#f2f2f2"}} onClick={()=>{filterData('BOOKED')}}>Booked</button>:
            <button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none' onClick={()=>{filterData('BOOKED')}}>Booked</button>}
            
            {/* Cancelled */}
            {(condition==="CANCELLED")?<button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block' style={{backgroundColor:"#4285F4",color:"#f2f2f2"}} onClick={()=>{filterData('CANCELLED')}}>Cancelled</button>
            :<button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block' onClick={()=>{filterData('CANCELLED')}}>Cancelled</button>}
            
            {(condition==="CANCELLED")?<button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none' style={{backgroundColor:"#4285F4",color:"#f2f2f2"}}onClick={()=>{filterData('CANCELLED')}}>Cancelled</button>:
            <button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none' onClick={()=>{filterData('CANCELLED')}}>Cancelled</button>}
            
            {/* Past Bookings */}
            {(condition==="PastBooking")?<button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block' style={{backgroundColor:"##4285F4",color:"#f2f2f2"}} onClick={()=>{getPastBookings()}}>Past Bookings</button>:
            <button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block' onClick={()=>{getPastBookings()}}>Past Bookings</button>}
            
            {(condition==="PastBooking")?<button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none' style={{backgroundColor:"4285F4",color:"#f2f2f2"}} onClick={()=>{getPastBookings()}}>Past Bookings</button>:
            <button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none' onClick={()=>{getPastBookings()}}>Past Bookings</button>}

             {/* <button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block' onClick={()=>{filterData('Booked')}}>Booked</button>
            <button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none' onClick={()=>{filterData('Booked')}}>Booked</button>
            <button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block' onClick={()=>{filterData('Cancelled')}}>Cancelled</button>
            <button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none' onClick={()=>{filterData('Cancelled')}}>Cancelled</button>
            <button className='btn btn-warning btn-lg d-none d-md-block d-lg-block d-xl-block' onClick={()=>{getPastBookings()}}>Past Bookings</button>
            <button className='btn btn-warning btn-sm d-xs-block d-sm-block d-md-none d-lg-none d-xl-none'>Past Bookings</button>  */}
          </div>
        </div>
        <div className='col-12 col-lg-3'>
        <form onSubmit={handleSubmit}>
          <div className="row justify-content-center">
            
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
        {console.log("Testing123",currentPageData)/* <div className='col-lg-3 col-md-4 col-sm-12 my-5 mx-sm-0 mx-md-4 outer-div'> */}
        {
          currentPageData && currentPageData.map((elem)=>{
              const {playerName,groundName,addressLineOne,city,slotStartTime,slotEndTime,slotDate,status} = elem;
              return(
                <div className='col-xl-3 col-lg-4 col-md-6 col-sm-12 my-5 '>
                <div className='row'>
                 <div className='col-10 mx-auto outer-div'>
                   <h6 className='text-center my-4 '><BsFillFilePersonFill/> &nbsp; &nbsp;{playerName}</h6>
                   {/* <div className="row my-2">
                     <div className='col-2'><BsFillFilePersonFill /></div>
                     <div className='col-10'>{name}</div>
                   </div> */}
                   <div className="row my-2 mx-2">
                     <div className='col-2 '><FaMapMarkerAlt /></div>
                     <div className='col-10'>{groundName}</div>
                   </div>
                   <div className="row my-2 mx-2">
                     <div className='col-2 '><FaRegCalendarCheck /></div>
                     <div className='col-10'>{moment(slotDate, "YYYY-MM-DD").format("Do MMM YYYY")}</div>
                   </div>
                   <div className="row my-2 mx-2">
                     <div className='col-2'><FaRegClock /></div>
                     <div className='col-10'>{moment(slotStartTime, "HH:mm").format("hh:mm a")} - {moment(slotEndTime, "HH:mm").format("hh:mm a")}</div>
                   </div>
                   <div className='row my-4'>
                    {/* <BookDiv/>
                    <CancelDiv/> */}
                    {/* {if(status == "Cancelled")?:} */}
                     <div className='col-md-6 col-sm-12 text-center my-sm-1'>
                      {/* <strong >{status}</strong> */}
                      {condition === "PastBooking"?<strong></strong>:condition === "CANCELLED"?<strong style={{color:"red"}}>{status}</strong>:<strong>{status}</strong>}
                      </div>
                    <div className='col-md-6 col-sm-12 text-center my-sm-1' >
                      {
                        //condition==="Booked"&&today===date&&currTime<startTime?<button className='btn btn-primary btn-sm mx-auto' onClick={()=>{cancelBooking(id,slotId)}}>Cancel</button>:condition==="Booked"&&today<date?<button className='btn btn-primary btn-sm mx-auto' onClick={()=>{cancelBooking(id,slotId)}}>Cancel</button>:""
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
      {/* <ToastContainer />  */}
    </div>

    )
}
