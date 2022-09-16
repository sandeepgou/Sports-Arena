import axios from "axios";
import { useEffect, useState } from "react";
import { FaCalendarAlt, FaRegWindowClose } from "react-icons/fa";
import DatePicker from "react-datepicker";
import moment from "moment";
import { Button, Form, FormGroup } from 'react-bootstrap';
import addDays from 'date-fns/addDays';
import subDays from 'date-fns/subDays';
import subMonths from 'date-fns/subMonths';
import Badge from 'react-bootstrap/Badge';
import ListComponent from "./button_Component";


function ListSlot() {

    const [selectedDate, setSelectedDate] = useState();
    const [formValues, setFormValues] = useState({ slotDate: "" });
    const [listData, setListData] = useState([]);
    // const [filteredList,setFilteredList] = useState([])
    const [isSubmit, setIsSubmit] = useState(false)
    const [filteredList, setFilteredList] = useState([]);
    let listdetails = [];

    const handleChange = (dates) => {
        //console.log("Dates", dates);
        setSelectedDate(dates)
        //console.log("selectedDates", selectedDate);
        const convertedDate = moment(dates).format('YYYY-MM-DD');
        console.log(convertedDate);
        setFormValues({ ...formValues, slotDate: convertedDate });
    }
    const listSubmit = () => {

        axios.get(`https://sportsarena.stackroute.io/booking-Management/api/v1/slotslist/groundID/`+sessionStorage.getItem("groundID"))
        .then((res)=>{
            var listdetails = res.data;
            // console.log(listdetails)
            sortedList(listdetails);
            return listdetails;
        }).catch(err=>console.log(err))

        // return fetch("http://localhost:8080/booking-Management/api/v1/newslot", {
        //     headers: {
        //         'Content-Type': 'application/json',
        //         'Accept': 'application/json'
        //     }
        // }).then((result) => {
        //     if (result.status === 200) {
        //         return Promise.resolve(result.json());
        //     } else {
        //         return Promise.reject("Unable to retrieve the Slot");
        //     }
        // }).then(res => {
        //     var listdetails = res;
        //     console.log(listdetails)
        //     sortedList(listdetails);
        //     return listdetails;
        // }).catch(error => {
        //     throw new Error(error);
        // })
    }
    const sortedList=(list)=>{
        let result = [];
        let k = 0;
        console.log(list)
        list.forEach((list) => {
           
            console.log(list.slotDate,formValues.slotDate)
            if (moment(formValues.slotDate).format('YYYY-MM-DD') === list.slotDate) {
                result[k] = list;
                k++;
            }

        });
        setFilteredList(result);
        console.log(filteredList, "filteredlist");
    };

    const slot_delete = (slotId) => {
      console.log("inside delete function");
      axios
        .delete(`https://sportsarena.stackroute.io/booking-Management/api/v1/slot/slotID/${slotId}`)
        .then((response) => {
          listSubmit();
          console.log(response);
          console.log("Deleted Successfully");
        })
        .catch((error) => {
          console.log(error);
        });
    };

       

    return (
        <div>
            <div className='text-center my-5'>

                <FormGroup><FaCalendarAlt />
                    <Form.Label style={{ paddingLeft: "1rem" }}>Select Date : </Form.Label><br />
                    <DatePicker placeholder="Select Date"
                        selected={selectedDate}
                        //onChange={(dates) => setSelectedDate((moment(dates).format('YYYY/MM/DD')))}
                        onSelect={(dates) => { handleChange(dates) }}
                        dateFormat="dd/MM/yyyy"
                        excludeDateIntervals={[{ start: subMonths(new Date(), 72), end: addDays(new Date(), 6) }]}

                    />
                    
                </FormGroup>
                <button type="submit" onClick={() => listSubmit()} className='btn btn-primary' style={{ margin: "2rem", marginLeft: "5rem" }}>List Slots</button><br/>
                {filteredList.map((slot) => {
                    // console.log(slot)           
                    return <ListComponent props={slot} slot_delete={(slotId) => slot_delete(slotId)} />
                })}

            </div>
          
        </div>
    )
}

export default ListSlot;