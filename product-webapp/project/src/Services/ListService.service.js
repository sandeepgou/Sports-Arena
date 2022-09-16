import axios from "axios";

const baseURL= "https://sportsarena.stackroute.io/user";
export function getGroundList( callback, errorcallback) {
    // const url = `${baseURL}/${type}`;
    // console.log(url);
    axios.get(`${baseURL}/api/v1/groundslist`)
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
  

  export function getGroundCityAndSport( city,groundType, callback, errorcallback) {
    // const url =`${baseURL}/?city=${city}&&groundType=${groundType}`;
    // console.log(url);
    axios.get(`${baseURL}/api/v1/groundslist/city/`+city+`/groundType/`+groundType)
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
  

 
  // export function getView( type, callback, errorcallback) {
  //   axios.get(`${baseURL}/${type}`)
  //     .then(res => {
  //       //do something
  //       if (callback != null) {
  //         callback(res);
  //       }
  //     })
  //     .catch(err => {
  //       // catch error
  //       if (errorcallback != null) {
  //         errorcallback(err);
  //       }
  //     })
  // }