import axios from "axios";
import { useState } from "react";
import { GrClose } from "react-icons/gr";
import "../Assets/css/signup.css";
import moment from "moment";
function ListComponent(props) {
  const [filteredata, setFilteredData] = useState([props]);
  // console.log(filteredata);
  // console.log("button Component");
  // console.log(props.props);

  return (
    <button className="btn btn-warning my-2">
      {/* moment().format("HH:mm") */}
      {/* moment(slotStartTime, "HH:mm").format("hh:mm a") */}
      {moment(props.props.startTime,"HH:mm").format("hh:mm a") + "-" + moment(props.props.endTime,"HH:mm").format("hh:mm a")}
      <GrClose
        className="icon mx-2"
        onClick={() => props.slot_delete(props.props.slotId)}
      />
    </button>
  );
}

export default ListComponent;