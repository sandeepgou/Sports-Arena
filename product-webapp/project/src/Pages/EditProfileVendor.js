import React, { useState,useEffect } from 'react'
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';
import {getGroundProfileData,putGroundData} from '../Services/ProfileService.service';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from "react-router-dom";
import DropdownMultiselect from "react-multiselect-dropdown-bootstrap";
import { InputGroup } from 'react-bootstrap';


export default function EditProfileVendor() {

    const notify = () => toast.success("Form submitted successfully!");
    let navigate = useNavigate(); 
    const[data,setData] = useState({
        groundID:"",
            ownerEmail:"",
            ownerPassword:"",
            groundName:"",
            groundType:"",
            addressLineOne:"",
            city:"",
            amenities:[],
            groundPicture:"",
            phoneNumber:"",
            charge:0
    });
    const [ownerPassword,setownerPassword] = useState("");
    const [selectedData,setSelectedData] = useState([]);
    function uploadImage(e){
        let files = e.target.files;
        let reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onload = (e)=>{
            //setprofilePic(e.target.result);
            setData({...data,groundPicture:e.target.result});
        }
        
    }
    function getGroundData(e){
        const name= e.target.name;
        const value = e.target.value;
        setData({
            
                // ...preValue,
                ...data,
                [name]:value,
            
            

        });
    }
    function submitForm(e){
        e.preventDefault();
        setData({...data,groundID:sessionStorage.getItem("groundID"),ownerPassword:ownerPassword})
        console.log(data);
        putGroundData(data,(res)=>{
            if(res.status===200){
                console.log("Form submitted");
                notify();
                navigate('/slotcreation')
            }
        },(err)=>{
            console.log(err);
        });
    }

    useEffect(() => {
        getGroundProfileData(sessionStorage.getItem("groundID"), (res) => {
            setData({...data,groundID:sessionStorage.getItem("groundID"),
                ownerEmail:res.data.ownerEmail,
                ownerPassword:res.data.ownerPassword,
                groundName:res.data.groundName,
        groundType:res.data.groundType,
        addressLineOne:res.data.addressLineOne,
        city:res.data.city,
        amenities:res.data.amenities,
        groundPicture:res.data.groundPicture,
        phoneNumber:res.data.phoneNumber,
        charge:res.data.charge
            });
            setownerPassword(res.data.ownerPassword)
            //setprofilePic(res.data.profilePhoto);
            
        });
        
    }, []);

    const handleOnChange=(e) => {
        setSelectedData(e);
        console.log(e);
        let value=e;
        setData({...data,amenities:value,ownerEmail:sessionStorage.getItem("email"),ownerPassword:data.ownerPassword})
        console.log(data)
      }

    
  return (
    
        <div className='container mt-5 py-5'>
            <div className="row">
                <div className="col-lg-6 col-md-12 vendor-background">
                
                </div>

                <div className="offset-lg-1 col-lg-5 col-md-12">
                <h3 className="text-center mb-4">Ground Details</h3>
                    <Form onSubmit={submitForm}>
                        <Row className="mb-3">
                            <Form.Group as={Col} controlId="formGridName">
                            <Form.Label>Ground Name</Form.Label>
                            <Form.Control type="text" placeholder="Enter Name" name="groundName" pattern="[A-Za-z ]{3,30}" title="Only letters are allowed and min length is 3 and max is 30" value={data.groundName} onChange={getGroundData} required/>
                            </Form.Group>

                            <Form.Group as={Col} controlId="formGridType">
                            <Form.Label>Ground Type</Form.Label>
                            <Form.Select value={data.groundType} name="groundType" onChange={getGroundData} required>
                                <option value="">Select</option>
                                <option value="Cricket">Cricket</option>
                                <option value="Football">Football</option>
                                <option value="Badminton">Badminton</option>
                                <option value="Volleyball">Volleyball</option>
                                <option value="Tennis">Tennis</option>
                            </Form.Select>
                            </Form.Group>
                        </Row>

                        <Form.Group className="mb-3" controlId="formGridAddress">
                            <Form.Label>Address</Form.Label>
                            <Form.Control placeholder="1234 Main St" name="addressLineOne"  value={data.addressLineOne} onChange={getGroundData} required/>
                        </Form.Group>

                        <Row className="mb-3">
                            <Form.Group as={Col} controlId="formGridCity">
                            <Form.Label>City</Form.Label>
                            <Form.Control type="text" placeholder="Enter City" name="city" pattern="[A-Za-z ]{2,30}" title="Only letters are allowed and min length is 2 and max is 30" value={data.city} onChange={getGroundData} required/>
                            </Form.Group>

                            <Form.Group as={Col} controlId="formGridState">
                            <Form.Label>Phone No.</Form.Label>
                            <Form.Control type="text" placeholder="Enter Phone No." name="phoneNumber"  pattern="[0-9]{10}" title="Only digits are allowed and length is 10" value={data.phoneNumber} onChange={getGroundData} required/>
                            </Form.Group>
                            {/* 
                            <Form.Group as={Col} controlId="formGridZip">
                            <Form.Label>Zip</Form.Label>
                            <Form.Control />
                            </Form.Group> */}
                        </Row>

                        <Form.Group className="mb-3" controlId="formGridamenities" >
                            <Form.Label>Amenities</Form.Label>
                            {/* <Form.Select value={data.ameneties} onChange={getGroundData} name="ameneties" required>
                                <option value="">Select</option>
                                <option value="1">Parking</option>
                                <option value="2">Drinking water</option>
                                <option value="3">Changing rooms</option>
                            </Form.Select> */}
                            <DropdownMultiselect required
        options={["Drinking Water", "Parking", "Washroom", "Changing Room"]}
        name="amenities"
        value={data.amenities}
        selected={selectedData}
        handleOnChange={handleOnChange}
      />
                        </Form.Group>

                        <Form.Label>Charge</Form.Label>
                        <InputGroup className="mb-3">
                        <InputGroup.Text>&#8377;</InputGroup.Text>
                        <Form.Control type="number" placeholder="Enter Amount" name="charge" min="1"  title="Only digits are allowed" value={data.charge} onChange={getGroundData} required/>
                        <InputGroup.Text>.00</InputGroup.Text>
                        </InputGroup>

                        <Form.Group className="mb-3" controlId="formGridImage"  >
                            <Form.Label>Ground Image</Form.Label>
                            <Form.Control type="file" name="groundPicture" onChange={uploadImage} accept="image/png,image/jpg,image/jpeg"/>
                        </Form.Group>

                        
                        <div className="float-end">
                        <Button type="button" className='me-2' onClick={()=>{navigate("/")}}>Cancel</Button>
                                    <Button type="submit" >Submit</Button>
                            </div>
                    </Form>
                    <div>
        {/* <button onClick={notify}>Notify!</button> */}
        <ToastContainer />
      </div>
                </div>
            </div>
        </div>
        
    
  )
}







