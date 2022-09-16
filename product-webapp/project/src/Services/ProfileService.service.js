import axios from "axios";

const baseURL = "https://sportsarena.stackroute.io/user";
export function getData( callback, errorcallback) {
  axios.get(`${baseURL}/api/v1/player/userEmail/`+sessionStorage.getItem("email"))
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

export function getDataByPlayerEmail( email,callback, errorcallback) {
  axios.get(`${baseURL}/api/v1/player/userEmail/`+email)
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

export function putData( data, callback, errorcallback) {
  console.log("inside put data fn");
  axios.put(`${baseURL}/api/v1/player`, data)
    .then(res => {
      //do something
      console.log(res.data);
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

export function getGroundProfileData(groundID, callback, errorcallback) {
  axios.get(`${baseURL}/api/v1/ground/groundID/`+sessionStorage.getItem("groundID"))
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

export function putGroundData( data, callback, errorcallback) {
  console.log("inside put data fn");
  axios.put(`${baseURL}/api/v1/ground`, data)
    .then(res => {
      //do something
      console.log(res.data);
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
// export function deleteData(id, type, callback, errorcallback) {
//   axios.delete(`${baseURL}/${type}/${id}`)
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

// export function postData(type, data, callback, errorcallback) {
//   axios.patch(`${baseURL}/${type}`, data)
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