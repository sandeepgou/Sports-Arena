import React, { useState, useEffect } from 'react'
//import {useForm, Controller} from 'react-hook-form';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import Alert from 'react-bootstrap/Alert';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import {getData,patchData, putData} from '../Services/ProfileService.service';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from "react-router-dom";


export default function EditProfilePlayer() {
    let navigate = useNavigate(); 
    const notify = () => toast.success("Form submitted successfully!");
    const [profilePic, setprofilePic] = useState('https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png');
    const [data, setData] = useState({
        name: "",
        phoneNumber: "",
        userEmail: "",
        password:"",
        role:"",
        city: "",
        state: "",
        gender: "",
        profilePhoto: "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png",
        favSport: ""
    }


    );
    function getPlayerData(e) {
        const name = e.target.name;
        const value = e.target.value;
        //const value=(name =="profilePhoto")?e.target.files[0].name:e.target.value;

        setData((preValue) => {
            console.log("Previous ", preValue);
            return {
                ...preValue,
                [name]: value,
            }


        });
        /*if (name == "profilePhoto") {
            setprofilePic(e.target.files[0].name);
            console.log(e.target.files[0].name);

        }*/
    }
    function uploadImage(e){
        let files = e.target.files;
        let reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onload = (e)=>{
            setprofilePic(e.target.result);
            setData({...data,profilePhoto:e.target.result});
        }
        
    }
    function submitForm(e) {
        e.preventDefault();
        console.log("inside submit form")
        //setData({...data,profilePhoto:profilePic});
        //console.log('submit data',data);
        putData(data,(res)=>{
                if(res.status==200){
                    notify();
                    navigate('/list')
                }
        });
    }
    useEffect(() => {
        getData((res) => {
            setData({...data,
                name:res.data.name,
                phoneNumber:res.data.phoneNumber,
                userEmail:res.data.userEmail,
                password:res.data.password,
                role:res.data.role,
                city:res.data.city,
                state:res.data.state,
                gender:res.data.gender,
                profilePhoto:res.data.profilePhoto,
                favSport:res.data.favSport, 
            });
           
            // setprofilePic(res.data.profilePhoto);
            
        }, (err) => {
            //error
            //alert(err);
        });
        
    }, []);
    return (
        // <div>
        <div className='container mt-5 p-5' style={{backgroundColor:"#f2f2f2"}}>
            <div className='row' >
                <div className="col-lg-6 col-md-12 edit-player">
                    {/* <img src="./../src/Assets/images/edit_player.png" alt="Ground Image" height="auto" /> */}
                </div>
                <div className="col-lg-6 col-md-12">
                    <h3 className="text-center mb-4">Edit Profile</h3>
                    <Form onSubmit={submitForm}>
                        <Form.Group as={Row} className="mb-3" controlId="formHorizontalName">
                            <Form.Label column sm={3} className="d-lg-block d-xl-block text-lg-end text-xl-end">
                                Full Name :
                            </Form.Label>
                            <Col sm={9}>
                                <Form.Control type="text" placeholder="Full Name" name="name" pattern="[A-Za-z ]{3,30}" title="Only letters are allowed and min length is 3 and max is 30" value={data.name} required onChange={getPlayerData} />
                            </Col>
                        </Form.Group>
                        <Form.Group as={Row} className="mb-3" controlId="formHorizontalEmail">
                            <Form.Label column sm={3} className="d-lg-block d-xl-block text-lg-end text-xl-end">
                                Email :
                            </Form.Label>
                            <Col sm={9}>
                                <Form.Control type="email" placeholder="Email" name="userEmail" title="Only digits are allowed and length is 10" disabled value={data.userEmail} required onChange={getPlayerData} />
                            </Col>
                        </Form.Group>
                        <Form.Group as={Row} className="mb-3" controlId="formHorizontalPhone">
                            <Form.Label column sm={3} className="d-lg-block d-xl-block text-lg-end text-xl-end">
                                Phone :
                            </Form.Label>
                            <Col sm={9}>
                                <Form.Control type="text" placeholder="Phone no." name="phoneNumber" pattern="[0-9]{10}" title="Only digits are allowed and length is 10" value={data.phoneNumber} required onChange={getPlayerData} />
                            </Col>
                        </Form.Group>
                        <Form.Group as={Row} className="mb-3" controlId="formHorizontalSport">
                            <Form.Label column sm={3} className="d-lg-block d-xl-block text-lg-end text-xl-end">
                                Sport :
                            </Form.Label>
                            <Col sm={9}>
                                <Form.Select aria-label="Default select example" name="favSport" value={data.favSport} required onChange={getPlayerData}>
                                    <option value="">Open this select menu</option>
                                    <option value="Cricket">Cricket</option>
                                    <option value="Badminton">Badminton</option>
                                    <option value="Football">Football</option>
                                    <option value="Volleyball">Volleyball</option>
                                    <option value="Tennis">Tennis</option>
                                </Form.Select>
                            </Col>
                        </Form.Group>


                        <fieldset>
                            <Form.Group as={Row} className="mb-3">
                                <Form.Label as="legend" column sm={3} className="d-lg-block d-xl-block text-lg-end text-xl-end">
                                    Gender :
                                </Form.Label>
                                <Col sm={9}>
                                    <Form.Check inline
                                        type="radio"
                                        label="Male"
                                        name="gender"
                                        id="formHorizontalRadios1"
                                        onChange={getPlayerData}
                                        value="Male"
                                        checked={data.gender === "Male"}
                                    />
                                    <Form.Check inline
                                        type="radio"
                                        label="Female"
                                        name="gender"
                                        id="formHorizontalRadios2"
                                        onChange={getPlayerData}
                                        value="Female"
                                        checked={data.gender === "Female"}
                                    />

                                </Col>
                            </Form.Group>
                        </fieldset>
                        <Form.Group as={Row} className="mb-3" controlId="formHorizontalState">
                            <Form.Label column sm={3} className="d-lg-block d-xl-block text-lg-end text-xl-end">
                                State :
                            </Form.Label>
                            <Col sm={9}>
                                <Form.Control type="text" placeholder="State" name="state" pattern="[A-Za-z ]{2,30}" title="Only letters are allowed and min length is 2 and max is 30" value={data.state} required onChange={getPlayerData} />
                            </Col>
                        </Form.Group>
                        <Form.Group as={Row} className="mb-3" controlId="formHorizontalCity">
                            <Form.Label column sm={3} className="d-lg-block d-xl-block text-lg-end text-xl-end">
                                City :
                            </Form.Label>
                            <Col sm={9}>
                                <Form.Control type="text" placeholder="City" name="city" pattern="[A-Za-z ]{2,30}" title="Only letters are allowed and min length is 2 and max is 30" value={data.city} required onChange={getPlayerData} />
                            </Col>
                        </Form.Group>

                        <Form.Group as={Row} className="mb-3" controlId="formHorizontal">
                            <Form.Label column sm={3} className="d-lg-block d-xl-block text-lg-end text-xl-end">
                                Image :
                            </Form.Label>
                            
                            {/* <Col sm={3} className="justyfy-content-center">
                            <img src={profilePic} style={{height:"100px",width:"100px"}}/>
                            </Col> */}
                            <Col sm={9}>
                                {/* <Form.Control type="file" onChange={getPlayerData} value={data.profilePhoto} name="profilePhoto" required accept="image/png,image/jpg,image/jpeg"/> */}
                                <Form.Control type="file" onChange={uploadImage} name="profilePhoto" accept="image/png,image/jpg,image/jpeg"/>
                            </Col>
                        </Form.Group>
                            
                        <Form.Group as={Row} className="mb-3">
                            <Col sm={{ span: 10, offset: 2 }} >
                                <div className="float-end">
                                    <Button type="button" className='me-2' onClick={()=>{navigate("/")}}>Cancel</Button>
                                    <Button type="submit" className='me-2'>Submit</Button>
                                    {/* <Button type="submit" onClick={()=>{navigate("/list")}}>Submit</Button> */}
                                </div>

                            </Col>
                        </Form.Group>
                    </Form>
                    <ToastContainer />
                </div>
            </div>

        </div>

        // </div>
    )
}

