import React, { useState } from 'react'
import StripeCheckout from 'react-stripe-checkout';
import moment from 'moment';
import axios from 'axios';
import {payment,postBooking,changeSlotStatus} from './../Services/BookingService.service';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import {getDataByPlayerEmail} from './../Services/ProfileService.service';

export default function CardPayment(props) {
    console.log("slot details ",props.slot);
    console.log("Booking details ",typeof(props.groundDetail.charge));
    const amount1 = JSON.stringify(props.groundDetail.charge);
    console.log(typeof(amount1));
    console.log(amount1)

    const loggedIn = sessionStorage.getItem("email");
    const today = moment().format("YYYY/MM/DD");

    const notify = () => toast.success("Slot Booked successfully!");
    const error_notify=()=>toast.error('🦄 Sorry!!! Some error has occured.');

    const[bookingDetails] = useState({
        groundId:props.groundDetail.groundID,
        userEmail:loggedIn,
        amount: props.groundDetail.charge
    })

    const [playerName,setPlayerName]=useState();

    //Getting the name of logged in player
    getDataByPlayerEmail(loggedIn,(res)=>{
        console.log("name :",res.data.name)
        setPlayerName(res.data.name)
    });

    function handleToken(token){
        console.log(token);
        const data = {
            userEmail:{token}.token.email,
            amount:amount1,
            paymentDate:today,
            transactionID:{token}.token.id,
            transactionStatus:"",
            paymentMode:"CardPayment",
            groundID:bookingDetails.groundId
           };
        console.log("obj data ",data);  
         
        
        payment(data,(res)=>{
            console.log(res)
            if(res.status===200){
                
                
                
                const bookingDetails = {
                    playerEmail: loggedIn,
                    playerName: playerName,
                    ownerEmail: props.groundDetail.ownerEmail,
                    groundName: props.groundDetail.groundName,
                    city: props.groundDetail.city,
                    addressLineOne: props.groundDetail.addressLineOne,
                    slotID: props.slot.slotId,
                    slotDate: props.slot.slotDate,
                    slotStartTime: props.slot.startTime,
                    slotEndTime: props.slot.endTime,
                    status: "BOOKED"
                }
                postBooking(bookingDetails,(res)=>{
                    console.log("slot details1 ",props.slot);
                    console.log("Booking details : ",bookingDetails);
                    if(res.status==200){
                        props.slot.status = "BOOKED";
                        changeSlotStatus(props.slot,(res)=>{
                            console.log("props slot ",props.slot);
                            console.log("slot response",res)
                        },(err)=>{
                            console.log("slot error ",err)
                        })
                        notify();
                    }
                },(err)=>{
                    console.log(err);
                })
            }
        },(err)=>{
               error_notify();
               console.log(err);
        })
      }

  return (
    <div className='container'>
        <StripeCheckout
          stripeKey='pk_test_51LQTCTA511UTIYLSCjsv8FspyBRLSl6TSu0eFh2xVUf3Ec9Wp1xaIcibclsYgt7WGJ2oCwg0k8rqUvxYoRhfnkfW00EJV1qO3e'
          token={handleToken}
          currency="INR"
          amount={bookingDetails.amount*100}
          email = {bookingDetails.userEmail}
        >
            <button className='btn btn-primary'>Pay Now</button>
        </StripeCheckout>
      
    </div>
  )
}
