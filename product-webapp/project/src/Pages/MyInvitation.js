import React from "react";
import { FaMapMarkerAlt } from "react-icons/fa";
import { MdOutlineSportsSoccer } from "react-icons/md";

export default function MyInvitation({ name, city, favSport, Accept,status }) {
  return (
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
            <div className="col-10">
            {status === "ACCEPTED" ? (
                <button disabled className="btn btn-primary my-2">
                  Accepted
                </button>
              ) : (
                <button
                  className="btn btn-primary my-2"
                  onClick={() => Accept()}
                >
                  Accept
                </button>
              )}
              
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
