import { useState, useEffect } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from "react-bootstrap/esm/Col";
import "../Assets/css/signup.css";
import "../Assets/images/signup.jpeg"
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import axios from 'axios';
import { getData, postData, putData } from '../Services/signupservices';
// import api from "../Services/signupservices";
// import Alert from 'react-bootstrap/Alert';
// import Image from 'react-bootstrap/Image'


function Signup() {
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const initialValues = { email: "", password: "", confirmpassword: "", role: "" };
    const [formValues, setFormValues] = useState(initialValues);
    const [formErrors, setFormErrors] = useState({});
    const [isSubmit, setIsSubmit] = useState(false);
    const [details, setDetails] = useState({
        name: "",
        phoneNumber: "",
        userEmail: "",
        password:"",
        role:"",
        city: "",
        state: "",
        gender: "",
        profilePhoto: "",
        favSport: ""
    });
    // const [formdetails,setFormdetails] = useState(
        
    // );

    // const retrieveDetails = () => {
    //     const response = api.get("/players");
    //     let value=response.data;
    //     console.log(value.userEmail);
    //     return response.data;
    // };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormValues({ ...formValues, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setFormErrors(validate(formValues));
        setIsSubmit(true);

    };

    // const getDetails = () => {
    //     axios.get("http://localhost:3000/players/2").then((response) => {
    //         console.log(response)
    //         setFormdetails(response.data)
    //     }).catch((error) => {
    //         console.log(error);
    //     }).then(() => { });
    //     console.log(formdetails);
    // }

    const postDetails = () => {
        if (formValues.role === "player") {
            axios
                .post("http://localhost:3000/players",
                    { name: "", phoneNumber: "", userEmail: formValues.email, password: formValues.password, role: formValues.role, city: "", state: "", gender: "", profilePhoto: "", favSport: "" })
                .then((response) => {
                    console.log(response);
                })
                .catch((error) => {
                    console.log(error);
                })
                .then(() => { });
        } else {
            axios
                .post("http://localhost:3000/vendors",
                    { ownerEmail: formValues.email, groundID: "", groundName: "", groundType: "", addressLineOne: "", addressLineTwo: "", city: "", pincode: "", ameneties: "", groundPicture: "", phoneNumber: "", password: formValues.password, role: formValues.role })
                .then((response) => {
                    console.log(response);
                })
                .catch((error) => {
                    console.log(error);
                })
                .then(() => { });
            // }
        }
    }
    const updateDetails = () => {
        const values = {
            name: "Jack",
            phoneNumber: "9876543210",
            userEmail: "Jack@gmail.com",
            password: "jack123",
            role: "player",
            city: "Chennai",
            state: "Tamil Nadu",
            gender: "Male",
            profilePhoto: "",
            favSport: "Cricket",
            id: 1
        }
        axios.put("http://localhost:3000/newplayer/1", values)
            .then(res => console.log(res.data)).catch((error) => { console.log(error) })
    }

    const deleteDetails = () => {
        axios.delete("http://localhost:3000/newplayer/3").
            then((response) => {
                console.log(response)
                console.log("Deleted Successfully");
            }).catch((error) => { console.log(error) })
    }

    // useEffect(() => {
    //     var config = { "Access-Control-Allow-Origin": "*" }
    //     getData(config, (res) => {
    //         //successconsole.log
    //         console.log(res);
    //     }, (err) => {
    //         //error
    //         alert(err);
    //     });
    //     // let formvalues = { name: "", phoneNumber: "", userEmail: formValues.email, password: formValues.password, role: formValues.role, city: "", state: "", gender: "", profilePhoto: "", favSport: "" }

    // }, [])

    useEffect(() => {
        //console.log(formErrors);
        if (Object.keys(formErrors).length === 0 && isSubmit) {

            console.log(formValues);
            handleClose();

            // if (formValues.role === "player") {
            //     // let data= {   
            //     //     "userEmail": "test123@gmail.com",
            //     //     "password": "test123",
            //     //     "role": "player"
            //     // "ownerEmail":"String",
            //     // "groundID":"String",
            //     // "groundName":"String",
            //     // "groundType":"String",
            //     // "addressLineOne":"String",
            //     // "addressLineTwo":"String",
            //     // "city":"String",
            //     // "pincode":"String",
            //     // "ameneties":"String[]",
            //     // "openingTime":"Date",
            //     // "closingTime":"Date",
            //     // "groundPicture":"String",
            //     // "phoneNumber":"String"
            //     //    }


            //     axios
            //         .post("http://localhost:3000/players",
            //             { name: "", phoneNumber: "", userEmail: formValues.email, password: formValues.password, role: formValues.role, city: "", state: "", gender: "", profilePhoto: "", favSport: "" })
            //         .then((response) => {
            //             console.log(response);
            //         })
            //         .catch((error) => {
            //             console.log(error);
            //         })
            //         .then(() => { });
            // } else {
            //     axios
            //         .post("http://localhost:3000/vendors",
            //             { ownerEmail: formValues.email, groundID: "", groundName: "", groundType: "", addressLineOne: "", addressLineTwo: "", city: "", pincode: "", ameneties: "", groundPicture: "", phoneNumber: "", password: formValues.password, role: formValues.role })
            //         .then((response) => {
            //             console.log(response);
            //         })
            //         .catch((error) => {
            //             console.log(error);
            //         })
            //         .then(() => { });
            // }
            //postDetails();
            let payload = {};
            // var config = { "Access-Control-Allow-Origin": "*" }
            if (formValues.role === "newplayer") {
                payload = { userEmail: formValues.email, name: "", phoneNumber: "", password: formValues.password,city: "",state: "",profilePhoto: "", favSport: "" ,  gender: ""};
            } else {
                payload = { ownerEmail:formValues.email,ownerPassword: formValues.password,  groundName: "", groundType: "", addressLineOne: "", city: "",ameneties: [],groundPicture: "", phoneNumber: "", charge:0};
            }

            postData(formValues.role, payload, (res) => {
                console.log(res)
               
                
            }, (err) => {
                //error
                alert(err);
            });
            setFormValues(initialValues);

        }
        // const getAllDetails = () => {
        //     const allDetails = retrieveDetails();
        //     if(allDetails) setDetails(allDetails);
        // }

        //getDetails();       
        //updateDetails();
        //deleteDetails();

        // getData((res) => {
        //     //success
        //     console.log(res.data);
        //     let values=res.data;
        //     setDetails(values);
        // }, (err) => {
        //     //error
        //     alert(err);
        // });
        // console.log(details);
        // var config = { "Access-Control-Allow-Origin": "*" }
        //     getData(config, (res) => {
        //         //successconsole.log
        //         //console.log(res.data);
        //         const values = res.data;
        //         // setDetails();
        //         //JSON.stringify(values);
        //          console.log(values);
        //          setDetails({...details,name:values.name,phoneNumber:values.phoneNumber,userEmail:values.userEmail,
        //          password: values.password,
        //          role: values.role,
        //          city: values.city,
        //          state: values.state,
        //          gender: values.gender,
        //          profilePhoto: values.profilePhoto,
        //          favSport: values.favSport
        //          });
        //         // values.map((value)=>{
        //         //     console.log(value);
               
                
        //         // console.log(details);
        //     }, (err) => {
        //         //error
        //         alert(err);
        //     });     

    }, [formErrors]);

    const validate = (values) => {
        const errors = {};
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;
        // if (!values.player ) {
        //     errors.player = "select one role!";
        // }else if(!values.vendor){
        //     errors.vendor = "select one role";
        // }
        // if (!role.player || !role.vendor) {
        //     errors.role = "One role must be selected";
        // }
        if (!(values.role === "newplayer" || values.role === "newground")) {
            errors.role = "Role is required!";
        }
        if (!values.email) {
            errors.email = "Email is required!";
        } else if (!regex.test(values.email)) {
            errors.email = "This is not a valid Email format!";
        }
        if (!values.password) {
            errors.password = "Password is required";
        } else if (values.password.length < 4 || values.password.length > 10) {
            errors.password = "Password must be less than 10 and more than 4 characters!";
        }
        if (!values.confirmpassword) {
            errors.confirmpassword = "Password is required and should match with password";
        }
        else if (values.confirmpassword.length < 4 || values.confirmpassword.length > 10) {
            errors.confirmpassword = "Password must be less than 10 and more than 4 characters!";
        }
        else if (values.password !== values.confirmpassword) {
            errors.confirmpassword = "Confirm password should match with password";
        }
        return errors;
    };
    return (
        <div>
            {/* <div className="my-4">
                {Object.keys(formErrors).length === 0 && isSubmit ? (
        <Alert variant="primary">Signed in successfully</Alert>
      ) : (
        <pre>{JSON.stringify(formValues)}</pre>
      )}
            </div> */}
            <button className="btn" style={{color:"white"}} onClick={handleShow}>
                Signup
            </button>



            <Modal show={show} onHide={handleClose} size="lg">
                <Modal.Header closeButton>
                    <Modal.Title className="ms-auto">Signup</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Container>
                        <Row>
                            <Col xs={12} md={5}>
                                <img className="image" src={require('../Assets/images/signup.jpeg')} alt="Sports Image" />
                            </Col>
                            <Col xs={12} md={7}>
                                <Form onSubmit={handleSubmit}>
                                    {/* <h1>Signup Form</h1> */}
                                    <div className="radio-buttons">
                                        <input type="radio" name="role" value="newplayer" checked={formValues.role === "newplayer"} onChange={handleChange} />
                                        Players
                                        <input type="radio" name="role" value="newground" checked={formValues.role === "newground"} onChange={handleChange} />
                                        Vendors
                                    </div>
                                    <p className="para">{formErrors.role}</p>
                                    <Form.Group className="mb-3" controlId="formBasicEmail">
                                        <Form.Label>Email address</Form.Label>
                                        <Form.Control type="email" placeholder="Enter your email" name="email" value={formValues.email} onChange={handleChange} />
                                    </Form.Group>
                                    <p className="para">{formErrors.email}</p>
                                    <Form.Group className="mb-3" controlId="formBasicPassword">
                                        <Form.Label>Password</Form.Label>
                                        <Form.Control type="password" placeholder="Enter your password" name="password" value={formValues.password} onChange={handleChange} />
                                    </Form.Group>
                                    <p className="para">{formErrors.password}</p>
                                    <Form.Group className="mb-3" controlId="formBasicConfirmPassword">
                                        <Form.Label>Confirm Password</Form.Label>
                                        <Form.Control type="password" placeholder="Enter your Password" name="confirmpassword" value={formValues.confirmpassword} onChange={handleChange} />
                                    </Form.Group>
                                    <p className="para">{formErrors.confirmpassword}</p>
                                    {/* <Button variant="primary">Submit</Button>  */}
                                    <button className="header-background mx-5 py-2">Register</button>

                                </Form>
                            </Col>
                        </Row>
                    </Container>
                </Modal.Body>
            </Modal>
        </div>
    )
}

export default Signup;