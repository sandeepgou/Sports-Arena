import axios from "axios";

const baseURL = "https://sportsarena.stackroute.io/booking-Management";
const paymentURL = "https://sportsarena.stackroute.io/payment";
export function getPlayerBookingsAll(name,value, type, callback, errorcallback) {
  axios.get(`${baseURL}/${type}?${name}=${value}`)
    .then(res => {
      //do something
      if (callback != null) {
        callback(res);
      }
    })
    .catch(err => {
      // catch error
      if (errorcallback != null) {
        errorcallback(err);
      }
    })
}

export function getPlayerBookings(email,callback,errorcallback) {
    // const url = `${baseURL}/api/v1/bookingslist/playerEmail/`+email;
    // console.log(url);
    axios.get(`${baseURL}/api/v1/bookingslist/playerEmail/`+email)
      .then(res => {
        //do something
        console.log("getPlayerBookings fn")
        if (callback != null) {
          callback(res);
        }
       })
      .catch(err => {
        // catch error
        if (errorcallback != null) {
          errorcallback(err);
        }
      })
  }

  export function getPlayerBookingsByStatus(email,status,callback,errorcallback) {
    // const url = `${baseURL}/api/v1/bookingslist/`+email;
    // console.log(url);
    
    axios.get(`${baseURL}/api/v1/bookingslist/playerEmail/`+email+`/status/`+status)
      .then(res => {
        //do something
        console.log(res);
        console.log("getPlayerBookings fn")
        if (callback != null) {
          callback(res);
        }
       })
      .catch(err => {
        // catch error
        if (errorcallback != null) {
          errorcallback(err);
        }
      })
  }

  export function getPlayerBookingsByDate(status,date, callback, errorcallback) {
    // const url = `${baseURL}/${type}?${name1}=${value1}&&${name2}=${value2}&&${name3}=${value3}`;
    // console.log(url);
    
    axios.get(`${baseURL}/api/v1/booking/playerEmail/`+sessionStorage.getItem("email")+`/status/`+status+`/date/`+date)
      .then(res => {
        //do something
        if (callback != null) {
          callback(res);
        }
      })
      .catch(err => {
        // catch error
        if (errorcallback != null) {
          errorcallback(err);
        }
      })
  }

  export function bookingCancel(bookingID,status, callback, errorcallback) {
    axios.delete(`${baseURL}/api/v1/booking/id/`+bookingID+`/status/`+status)
      .then(res => {
        //do something
        if (callback != null) {
          callback(res);
        }
      })
      .catch(err => {
        // catch error
        if (errorcallback != null) {
          errorcallback(err);
        }
      })
  }
  

  export function changeSlotStatus(data,callback,errorcallback){
    axios.put(`${baseURL}/api/v1/slot`,data ,callback,errorcallback).then(res=>{
      if (callback != null){
        callback(res);
      }
    }).catch(err=>{
      if (errorcallback != null) {
        errorcallback(err);
      }
    })
  } 


  /******************For Vendor ***************** */
 
  export function getVendorBookings(email,status, callback, errorcallback) {
   // const url = `${baseURL}/api/v1/booking/ownerEmail`+email+`status`+status;

    axios.get(`${baseURL}/api/v1/booking/ownerEmail/`+sessionStorage.getItem("email")+`/status/`+status)
      .then(res => {
        //do something
        if (callback != null) {
          callback(res);
        }
      })
      .catch(err => {
        // catch error
        if (errorcallback != null) {
          errorcallback(err);
        }
      })
  }
  // var  email="ownerEmail3@gmail.com"
  export function getVendorBookingsByDate(status,slotDate, callback, errorcallback) {
   // const url = `${baseURL}/${type}?${name1}=${value1}&&${name2}=${value2}&&${name3}=${value3}`;
   // console.log(url);
    axios.get(`${baseURL}/api/v1/booking/ownerEmail/`+sessionStorage.getItem("email")+`/status/`+status+`/date/`+slotDate)
      .then(res => {
        //do something
        if (callback != null) {
          callback(res);
        }
      })
      .catch(err => {
        // catch error
        if (errorcallback != null) {
          errorcallback(err);
        }
      })
  }

  /*********************Payment service************* */
  export function payment(data,callback,errorcallback){
    console.log("Amount ",data.amount);
    const header = {'token':data.transactionID,
    'amount':data.amount}
    // header.append(
    //   'token',data.transactionID,
    //   'amount',data.amount

    // )
    axios.post(`${paymentURL}/api/v1/charge/`+data.transactionID+`/`+data.amount,callback,errorcallback)
      .then(res => {
        //do something
        if (callback != null) {
          callback(res);
        }
      })
      .catch(err => {
        // catch error
        if (errorcallback != null) {
          errorcallback(err);
        }
      })
  }

  /**********************End Payment***************** */

  /****************Add Booking************************ */
  export function postBooking(data,callback,errorcallback){
    axios.post(`${baseURL}/api/v1/newbooking`,data,callback,errorcallback)
      .then(res => {
        //do something
        if (callback != null) {
          callback(res);
        }
      })
      .catch(err => {
        // catch error
        if (errorcallback != null) {
          errorcallback(err);
        }
      })
  }