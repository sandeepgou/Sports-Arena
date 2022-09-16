import React,{useState,useEffect} from "react";
import { FaAddressCard,FaCalendarAlt,FaCity,FaClipboardList, FaLocationArrow, FaMap, FaMapMarkerAlt, FaMoneyBillWave, FaRupeeSign } from 'react-icons/fa';
import { useNavigate } from "react-router-dom";
import { Button, Container,Image,Form, FormGroup } from 'react-bootstrap';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Footer from '../Components/Footer';
import { getDetailView } from "../Services/DetailviewService.service";
// import "../Assets/css/list.css";
import {MdCheckBox, MdLocationCity, MdOutlineSportsSoccer,MdSportsScore} from 'react-icons/md';
import {FcAddDatabase, FcAddressBook, FcAlarmClock, FcBookmark, FcCircuit, FcGlobe, FcHome, FcInspection, FcList, FcOvertime, FcSportsMode} from 'react-icons/fc'
import DatePicker from "react-datepicker";
import moment from "moment";
import subMonths from 'date-fns/subMonths';
import addDays from 'date-fns/addDays';
import List from "./slots";
import { useLocation } from 'react-router-dom';
import axios from 'axios';
import CardPayment from "./CardPayment";

function DetailView () {
    let navigate = useNavigate(); 
    const [detailData,setDetailData] =useState([]);
    const [selectedDate, setSelectedDate] = useState();
    const [formValues, setFormValues] = useState({ slotDate: "" });
    const [listData, setListData] = useState([]);
    const location = useLocation();
    const [isSubmit, setIsSubmit] = useState(false)
    const [filteredList, setFilteredList] = useState([]);
    const [selectedSlot,setSelectedSlot] = useState();
    let id=0;
   
 

  const handleChange = (dates) => {
    //console.log("Dates", dates);
    setSelectedDate(dates)
    //console.log("selectedDates", selectedDate);
    const convertedDate = moment(dates).format('YYYY-MM-DD');
    setFormValues({ ...formValues, slotDate: convertedDate });
}
const listSubmit = () => {
//     return fetch("http://localhost:8080/createdslots", {
//         headers: {
//             'Content-Type': 'application/json',
//             'Accept': 'application/json'
//         }
//     }).then((result) => {
//         if (result.status === 200) {
//             return Promise.resolve(result.json());
//         } else {
//             return Promise.reject("Unable to retrieve the location");
//         }
//     }).then(res => {
//         var listdetails = res;
//         console.log(listdetails)
//         sortedList(listdetails);
//         return listdetails;
//     }).catch(error => {
//         throw new Error(error);
//     })
return axios.get(`https://sportsarena.stackroute.io/booking-Management/api/v1/slotslist/groundID/`+location.state.groundID)
 .then((res)=>{
 var listdetails = res.data;
 console.log(listdetails)
 sortedList(listdetails);
 return listdetails;
 }).catch(err=>console.log(err))   


}
const sortedList=(list)=>{
    let result = [];
    let k = 0;
    list.forEach((list) => {
      if (moment(formValues.slotDate).format('YYYY-MM-DD') === list.slotDate) {
            result[k] = list;
            k++;
        }

    })
    setFilteredList(result);
    console.log(filteredList, "filteredlist");
}
// console.log(location.state.id);
const getdetailview = () => {
  console.log("id");
    axios.get(`https://sportsarena.stackroute.io/user/api/v1/ground/groundID/`+location.state.groundID)
    .then((res)=>{
        // console.log(res.data[0]);
        let values=[];
        values.push(res.data)
        // console.log(values.id);
        // id=values.id;
        console.log(values);
        setDetailData(values);
        setTimeout(()=> {
          console.log(detailData);
        },5000)
    }).catch((Err)=>console.log(Err));
}


useEffect(() => {
  getdetailview();

},[]);

// const slotAdd = (slotId) => {

  // axios
  //   .post(`http://localhost:8080/booking-Management/api/v1/newbooking`,)
const slotAdd = (slotDetail) => {
   console.log(slotDetail);
  setSelectedSlot(slotDetail);
  
  /*axios
    .post(`http://localhost:3000/booking`)
    .then((res) => {
      console.log(res.data);
      console.log("Add successfuly");
    })
    .catch((error) => {
      console.log(error);
    });*/
};

return (
<div >
    <Container>
    <div className='  m-5 '>
      {detailData && detailData.map((state) => {
        const{groundID,groundPicture,addressLineOne,groundName,groundType,city,ownerEmail,amenities,charge} = state
        return(
            <div >
               <div >
                {/* <Image src={groundPicture} className="img-fluid w-100" /> */}
                <Image src={groundPicture} className="gi "  alt="img"/>
               </div>
               <h2 className=' my-5 text-center'> {groundName}  </h2>
                <div className="row my-5">
                <div className="col-lg-6 col-md-12 ">
                   
                    
              <h3 className="my-3 justify-content"><FaMapMarkerAlt  style={{ color :'green',}} size="30px"/>  Address</h3>
                           <div className="mx-5" > <FaLocationArrow color="blue" size="20px"/>  {addressLineOne}  - <FaCity size="20px" color="darkturquoise"/>  {city} </div> 
    
                    <h3 className="my-3 "> <FcList size="30px"/> Ameneties</h3>
                       <div className="mx-5">
                        {amenities.map((am) => {
                          return (
                            <div className="">
                                <FcInspection size="20px"/>  {am}
                            </div>
                          )
                            
                         })} </div>

                           <div > 
                         <h3 className="my-3 "> <FaMoneyBillWave color="green" size="30px"/>  cost</h3>
                        <div className="mx-5">
                         <FaRupeeSign  style={{color:'gold'}} size="20px"/>  {charge}/- </div>
                      </div>
              
               
            </div>
            <div className="offset-lg-1 col-lg-4 col-md-12 my-5">
            <div>
            <div >
            <Row >
                            

                            <Form.Group as={Col} >
                              <FaCalendarAlt style={{color:'crimson'}} size="30px"/>
                          
                            <Form.Label style={{ paddingLeft: "1rem" }}>Select Date : </Form.Label><br />
                    <DatePicker placeholder="Select Date"
                        selected={selectedDate}
                        //onChange={(dates) => setSelectedDate((moment(dates).format('YYYY/MM/DD')))}
                        onSelect={(dates) => { handleChange(dates) }}
                        dateFormat="dd-MM-yyyy"
                        excludeDateIntervals={[{ start: subMonths(new Date(), 72), end: addDays(new Date(), -1) }]}
                     />
                            </Form.Group>
                           <Form.Group as={Col} >
                             <div className=' my-4  '>
                <button type="submit" onClick={() => listSubmit()} className='btn btn-primary' > Slots</button>
                
               </div>
                            </Form.Group>
                        </Row>
                   
                    
                   
                        {filteredList.map((slot) => {
                    // console.log(slot)           
                    return <List props={slot} slotAdd={(slotId) => slotAdd(slotId)} />
                })}
  
            </div>
          
        </div>
         <div className="text-end my-5 ">
  <CardPayment slot={selectedSlot} groundDetail={state}/> 
  </div>
      {/* <div >
        <button className='btn btn-primary' style={{ margin: "2rem", marginLeft: "5rem" }}>Book Slot</button>
    </div> */}
   
   </div>
     </div>
     
            </div>
        )
      })}

    </div>
   </Container>
</div>
)
}
export default DetailView;