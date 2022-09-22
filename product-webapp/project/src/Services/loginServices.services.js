import axios from "axios";

export function postData(formValues, callback, errorcallback){
  axios.post("http://44.208.58.122:8080/authentication/api/v1/userauth",formValues)
  .then(res => {
    //do something
    if(callback != null){
       callback(res);
    }
  })
  .catch(err => {
    // catch error
    if(errorcallback != null){
       errorcallback(err);
    }
  })
}

// export function getData(config, callback, errorcallback){
//     axios.get("http://localhost:8080/user/api/v1/newplayer")
//     .then(res => {
//       //do something
//       if(callback != null){
//          callback(res);
//       }
//     })
//     .catch(err => {
//       // catch error
//       if(errorcallback != null){
//          errorcallback(err);
//       }
//     })
// }

// export function getData(config, callback, errorcallback){
//   axios.get("http://localhost:8080/newground")
//   .then(res => {
//     //do something
//     if(callback != null){
//        callback(res);
//     }
//   })
//   .catch(err => {
//     // catch error
//     if(errorcallback != null){
//        errorcallback(err);
//     }
//   })
// }
