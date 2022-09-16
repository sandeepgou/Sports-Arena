import {useEffect,useState} from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Row from 'react-bootstrap/Row';
import Col from "react-bootstrap/esm/Col";
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import axios from 'axios';
import EditProfileVendor from "../Pages/EditProfileVendor";
import EditProfilePlayer from "../Pages/EditProfilePlayer";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from 'react-toastify';
import "../Assets/css/login.css"
import {postData} from "../Services/loginServices.services"

function Login ({setEmail}) {
    
    let navigate = useNavigate(); 
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const initialValues = { 
        role:"",
        userEmail: "",
     password: ""
    };
    const [formValues, setFormValues] = useState(initialValues);
    const [formErrors, setFormErrors] = useState({});
    const [isSubmit, setIsSubmit] = useState(false);
    const [errMsg, setErrMsg] = useState('');
    const [userPassword, setuserPassword] = useState({userPassword:""}); 
    const [ownerPassword, setownerPassword] = useState({ownerPassword:""});
    // const [email, setEmail] = useState({email:""});
    const [userEmail,setuserEmail] = useState({userEmail:""});
    const [ownerEmail,setownerEmail] = useState({ownerEmail:""});
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormValues({ ...formValues, [name]: value });
    };
  


    useEffect(() => {
        if (Object.keys(formErrors).length === 0 && isSubmit ) {
            handleClose();
            console.log(formValues);
            if (formValues.role === "players") {
              
                axios
                    .get("http://localhost:3000/players/2")
                    .then((response) => {
                        // console.log(response.data);

                        let values= response.data;
                        setuserEmail({userEmail:values.userEmail})
                        setuserPassword({userPassword:values.password})
                    })
                    .catch((error) => {
                        console.log(error);
                    })
                    .then(() => { });
            } else if(formValues.role === "vendors") {
                axios
                .get("http://localhost:3000/vendors/1")
                .then((response) => {
                    // console.log(response.data);
                   let values= response.data;
                  setownerEmail({ownerEmail:values.ownerEmail})
                  setownerPassword({ownerPassword:values.password})
                })
                .catch((error) => {
                    console.log(error);
                })
                .then(() => { });
            }
            console.log(userEmail);
            console.log(ownerEmail);
            // console.log(email)
            console.log(userPassword);
            console.log(ownerPassword);
            setFormValues(initialValues);

        }
        
    }, [formErrors]);
    const handleSubmit = (e) => {
        const err= {};
        e.preventDefault();
        setFormErrors(validate(formValues));
        // console.log(values);
        setIsSubmit(true);
        if ((formValues.role === "newplayer"  ) && (formValues.password === userPassword.userPassword) && (formValues.email === userEmail.userEmail))
        {
        navigate("/EditProfilePlayer");
        // navigate("/EditProfileVendor")
        }
        else if ((formValues.role === "newground") && (formValues.password === ownerPassword.ownerPassword) && (formValues.email === ownerEmail.ownerEmail))
        {
            navigate("/EditProfileVendor");
        }
        // else  {
        //     // alert('wrong email or password ');
        //     setErrMsg('Unauthorized');t
        //     <ToastContainer/>
        // }
        postData(formValues, (res) => {
            console.log(res)
            sessionStorage.setItem("email",formValues.userEmail)
            sessionStorage.setItem("JWT",res.data.token)
            sessionStorage.setItem("role",formValues.role)
             setEmail(formValues.userEmail)
            if(formValues.role==="newground"){
                axios.get(`https://sportsarena.stackroute.io/user/api/v1/ground/ownerEmail/`+formValues.userEmail)
                .then((res)=>{
                    sessionStorage.setItem("groundID",res.data.groundID)
                })
            }
            
            // if (formValues.role==="newground"){
            //     sessionStorage.setItem("groundID",res.groundID)
            // }
            if(formValues.role==="newplayer") 
            {
                navigate("/EditProfilePlayer");
            }
            else if(formValues.role==="newground")
            {
                navigate("/EditProfileVendor");
            }
        }, (err) => {
            //error
            alert(err);
        });
    };
    const validate = (values) => {
        const errors = {};
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;
        if (!(values.role === "newplayer" || values.role === "newground")) {
            errors.role = "Role is required!";
        } 
        if (!values.userEmail) {
            errors.email = " Enter Email ID";
        
        }
        if (!values.password) {
            errors.password = "Enter Password ";
        
        }
      
        return errors;
    };
    return (
    <div>
         
        
             
         <button className="btn" style={{color:"white"}} onClick={handleShow}>
                Login
            </button>


            

            <Modal show={show} onHide={handleClose} size="lg" >
                <Modal.Header closeButton >
                    <Modal.Title>Login</Modal.Title>
          
                </Modal.Header >
                <Modal.Body>
                        <Row>
                            <Col  md={6}>
                                <img className="image" src="https://www.mydr.com.au/wp-content/uploads/2020/03/AdobeStock_251787228-scaled.jpg" fluid alt=" image" />
                            </Col>
                
                            <Col  md={6}>
                           
                                <Form onSubmit={handleSubmit}>
                                <div className="radio-buttons">
                                        <input type="radio" name="role" value="newplayer" checked={formValues.role === "newplayer"} onChange={handleChange} />
                                        Players
                                        <input type="radio" name="role" value="newground" checked={formValues.role === "newground"} onChange={handleChange} />
                                        Vendors
                                    </div>
                                    <p className="para">{formErrors.role}</p>
                                    <Form.Group className="mb-3" controlId="formBasicEmail">
                                        <Form.Label>Email address :</Form.Label>
                                        <Form.Control type="email" placeholder="Email ID" name="userEmail" value={formValues.userEmail} onChange={handleChange} />
                                    </Form.Group>
                                    <p className="para"> {formErrors.email}</p>
                                    <Form.Group className="mb-3" controlId="formBasicPassword">
                                        <Form.Label>Password :</Form.Label>
                                        <Form.Control type="password" placeholder="Password" name="password" value={formValues.password} onChange={handleChange} />
                                    </Form.Group>
                                    <p className="para">{formErrors.password}</p>
                
                                    <Button type="Submit"  >  Login</Button>
                              
                                </Form>
                                
                            </Col>
                        </Row> 
                </Modal.Body>
            </Modal>
            <ToastContainer />
           </div>
           
            
    )
}

export default Login;




