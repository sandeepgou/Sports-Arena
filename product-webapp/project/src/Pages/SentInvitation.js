import React from "react";
import { FaMapMarkerAlt } from "react-icons/fa";
import { MdOutlineSportsSoccer } from "react-icons/md";
import {useNavigate} from 'react-router-dom'
import ChatFeature from "./ChatFeature";

export default function SentInvitation({ name, city, favSport, Delete,status,email }) {
  let navigate = useNavigate();
  const Chat = (email)=>{
   navigate("/ChatFeature",{state:{email:email}})
    console.log(email)
  }
  return (
    // <div className="card col-3 mx-4 my-4">
    //   <h6 className="text-center my-4 ">{name}</h6>
    //   <div className="row my-2 mx-2">
    //          <div className="col-2">
    //           <FaMapMarkerAlt />
    //        </div>
    //         <div className="col-10">{city}</div>
    //        </div>
    //  </div>
    <div className="col-xl-2 col-lg-3 col-md-4 col-sm-12 my-5">
      <div className="row">
        <div className="col-10 mx-auto outer-div">
          <h6 className="text-center my-4">{name}</h6>
          <div className="row my-2 mx-2">
            <div className="col-2">
              <FaMapMarkerAlt />
            </div>
            <div className="col-10">{city}</div>
          </div>
          <div className="row my-2 mx-2">
            <div className="col-2">
              <MdOutlineSportsSoccer />
            </div>
            <div className="col-10">{favSport}</div>
          </div>
          <div className="row my-2">
          <div className="col-2"></div>
            <div className="col-10 ">
            <b>{status}</b>
            </div>
          </div>
          <div className="row my-2">
            {/* <div className="col-2"></div> */}
            <div className="col-12 d-flex justify-content-center px-2">
              {/* <p><b>{status}</b></p> */}
              {status==="ACCEPTED"?(<button disabled className="btn btn-primary my-2 mx-2">Delete</button>):
              (<button className="btn btn-primary my-2 mx-2"
              onClick={() => Delete()}>Delete</button>)}
             <button className="btn btn-primary my-2 mx-2" onClick={()=>{Chat(email)}}>Chat</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
