import Tabs from 'react-bootstrap/Tabs';
import Tab from 'react-bootstrap/Tab';
import DatePicker from "react-datepicker";
import TimePicker from 'react-time-picker';
//import TimePicker from 'react-dropdown-timepicker';
import "react-datepicker/dist/react-datepicker.css";
import { useEffect, useState } from "react";
import { Button, Form, FormGroup } from 'react-bootstrap';
import moment from 'moment';
import 'bootstrap/dist/css/bootstrap.min.css';
import addDays from 'date-fns/addDays';
import subDays from 'date-fns/subDays';
import subMonths from 'date-fns/subMonths';
//import { VscCalendar} from "react-icons/vsc";
import { FcClock, FcCalendar } from "react-icons/fc"
import { FaCalendarAlt, FaClock } from "react-icons/fa"
import "../Assets/css/signup.css";
import axios from 'axios';
import ListSlot from './ListSlot';
import buttonComponent from './button_Component'


function SlotCreation() {
    const [selectedDate, setSelectedDate] = useState();
    const [startTime, setStartTime] = useState(0);
    const [endTime, setEndTime] = useState(0);
    const [slotId, setSlotId] = useState(0);
    const [formValues, setFormValues] = useState({
        slotId: "",
        ownerEmail: "",
        startTime: "",
        endTime: "",
        status: "AVAILABLE",
        slotDate: "",

    })
    const [isSubmit,setIsSubmit]=useState(false);
    const [slotData,setSlotData]=useState([])
    let slotList = [];
    let filteredList = [];
    //const [filteredList,setFilteredList] = useState({});
    const date = new Date();

    const handleChange = (dates) => {
        //console.log("Dates", dates);
        setSelectedDate(dates)
        //console.log("selectedDates", selectedDate);
        setIsSubmit(false)
        const convertedDate = moment(dates).format('YYYY-MM-DD');
        setFormValues({ ...formValues, slotDate: convertedDate });

    }
    const handleStartTime = (time) => {
        //console.log("startTime", time);
        setStartTime(time);
        setIsSubmit(false)
        setFormValues({ ...formValues, startTime: time });
    }
    const handleEndTime = (time) => {
        //console.log("endTime", time);
        setEndTime(time);
        setIsSubmit(false)
        setFormValues({ ...formValues, endTime: time });
    }
    const handleSubmit = (e) => {
        e.preventDefault();

        console.log("final Values", formValues);
        axios.
            post(`https://sportsarena.stackroute.io/booking-Management/api/v1/newslot`,
                { groundID: sessionStorage.getItem("groundID"),startTime: formValues.startTime,endTime: formValues.endTime,status: formValues.status,slotDate: formValues.slotDate })
            .then((response) => {
                console.log(response);
                setIsSubmit(true);
            })
            .catch((error) => {
                console.log(error);
            })
            .then(() => { });
            // console.log(isSubmit);
            
    }
    
        
    
    return (
        <div className='container-fluid mt-5 p-5'>

            <div className="row">

                <div className="col-lg-6 col-md-12 slotimage">
                    {/* <img className="slotimage" src={require('../Assets/images/slotcreation.jpeg')} alt="Sports Image" /> */}

                    {/* <Form.Group controlId="dob">
                            <Form.Label>Select Date</Form.Label>
                            <Form.Control type="date" name="dob" placeholder="Date of Birth" />
                    </Form.Group> */}

                </div>
                <div className="offset-lg-1 col-lg-5 col-md-12">

                    <Tabs defaultActiveKey="CreateSlot" id="justify-tab-example" className="mb-3" justify>
                        <Tab eventKey="CreateSlot" title="Create Slot">
                            {isSubmit===true?(<p style={{color:"green"}}>Slot created</p>):(<p></p>)}
                            <div className='text-center my-5'>
                                <form onSubmit={handleSubmit} >
                                    <FormGroup><FaCalendarAlt />
                                        <Form.Label style={{ paddingLeft: "1rem" }}>Select Date : </Form.Label><br />
                                        <DatePicker placeholder="Select Date" name="date" 
                                            selected={selectedDate}
                                            //onChange={(dates) => setSelectedDate((moment(dates).format('YYYY/MM/DD')))}
                                            onSelect={(dates) => { handleChange(dates) }}
                                            dateFormat="dd/MM/yyyy"
                                            excludeDateIntervals={[{ start: subMonths(new Date(), 72), end: addDays(new Date(), 6) }]}
                                            required
                                        />

                                    </FormGroup><br />
                                    <FormGroup><FaClock />
                                        <Form.Label style={{ paddingLeft: "1rem" }}>Start Time : </Form.Label><br />

                                        <TimePicker name="starttime"
                                            //onChange={(time) => setStartTime(time)}
                                            onChange={(time) => { handleStartTime(time) }}
                                            value={startTime}
                                            required
                                        /><br /><br />
                                    </FormGroup>
                                    <FormGroup><FaClock />
                                        <Form.Label style={{ paddingLeft: "1rem" }}>End Time : </Form.Label><br />
                                        <TimePicker name="endtime"

                                            onChange={(time) => { handleEndTime(time) }}
                                            // onChange={handleChange}
                                            value={endTime}
                                            required
                                        /><br /><br /><br />
                                    </FormGroup>

                                    <button className='btn btn-primary' style={{ marginBottom: "2rem", marginLeft: "2rem" }}>Create Slot</button>
                                    {/* onClick={submitSlots(selectedDate,startTime,endTime)} */}
                                    
                                </form>
                                
                            </div>
                            
                        </Tab>

                        <Tab eventKey="manageSlot" title="Manage Slot">
                           
                            <ListSlot />
                            
                            
                        </Tab>

                    </Tabs>
                </div>
            </div>
        </div>

    )

}

export default SlotCreation;