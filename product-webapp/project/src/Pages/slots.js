import axios from "axios";
import { useState } from "react";
import {GrClose} from "react-icons/gr";
import "../Assets/css/signup.css"
import moment from "moment";
import ToggleButton from 'react-bootstrap/ToggleButton';
import { ButtonGroup } from "react-bootstrap";

function List(props){
  const [isActive, setIsActive] = useState(false);
  const handleClick = () => {
      // alert("hii");
      props.slotAdd(props.props)
      // console.log(isActive)
  //   setIsActive(current => !current)
  //  console.log(isActive)
  };
  const [filteredata, setFilteredData] = useState([props]);
  console.log(props.props.status)
  let status=props.props.status
   return(
      <ButtonGroup>
      <ToggleButton
      disabled={props.props.status === "BOOKED"  }
      variant={props.props.status === "AVAILABLE" ? 'success':'danger'}
      className="mx-2  my-1"
      onClick={(e) => handleClick()}
        >
       {moment(props.props.startTime,"HH:mm").format("hh:mm a") + " - " + moment(props.props.endTime,"HH:mm").format("hh:mm a")}
</ToggleButton>
</ButtonGroup>
   )
}
export default List;