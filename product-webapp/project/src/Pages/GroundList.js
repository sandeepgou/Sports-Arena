import React, { useState,useEffect } from 'react';
import { Button, Container,Image } from 'react-bootstrap';
import { Form } from 'react-bootstrap';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import "../Assets/css/list.css";
import { FaCity, FaMapMarkerAlt } from 'react-icons/fa';
import {MdOutlineSportsSoccer,MdSportsScore} from 'react-icons/md';
import { link,useNavigate } from "react-router-dom";
// import  Product from "../Services/db.json";
import moment from 'moment';
import { getGroundList, getGroundCityAndSport, getGroundSports, getView ,} from '../Services/ListService.service';
import ReactPaginate from 'react-paginate';
import Pagination from 'react-bootstrap/Pagination';
import Footer from '../Components/Footer';
import {FcAddressBook, FcBookmark, FcCircuit, FcGlobe, FcHome, FcInspection, FcList, FcSportsMode} from 'react-icons/fc'



function GroundList({id}) {
let navigate = useNavigate(); 
const [filteredData,setFilteredData]=useState([]);
  const [selectCity, setSelectCity] = useState();
  const [selectSports, setSelectSports] = useState();
  const [selectId, setSelectId] =useState();
  const [condition,setCondition]=useState();
const[data,setData] = useState();
const [currentPage, setCurrentPage] = useState(0);
  const [itemOffset, setItemOffset] = useState(0);
  const [isSubmit, setIsSubmit] = useState(false);
  // const [formValues, setFormValues] = useState({id:""});

useEffect(() => {
  getGroundList( (res) => {
    setFilteredData(res.data);
  }, (err) => {
    console.log("Error", err);
  })
}, [itemOffset, 6]);

const PER_PAGE = 6;
const offset = currentPage * PER_PAGE;
const currentPageData  = filteredData.slice(offset, offset + PER_PAGE);

const pageCount = Math.ceil(filteredData.length / PER_PAGE);

function handlePageClick({ selected: selectedPage }) {
  setCurrentPage(selectedPage);
}
 function filterData(status){
   getGroundList('vendors', (res) => {
    setFilteredData(res.data);
    setCurrentPage(0);
  }, (err) => {
    console.log("Error", err);
  })
 }

const handleCity=(city) => {
     console.log(city.target.value);
     setSelectCity(city.target.value);
    //  setSelectSports(city.target.value);
}
const handleSports=(groundType) => {
      console.log(groundType.target.value);
      setSelectSports(groundType.target.value);
}

const handleNext=(e) => {
  e.preventDefault();
  console.log("New Location",selectCity);
  console.log("New Sports",selectSports);
  getGroundCityAndSport (selectCity,selectSports,(res)=>{
    setFilteredData(res.data);
    console.log(res.data)
  },(err)=>{
    console.log(err);
  });
}

const handleSubmit = (groundID)=>{
  navigate("/detailview",{state:{groundID:groundID}})
  //  console.log(id)
 }


  return (
    <div >
      
 <Form className='search' onSubmit={handleNext} >
  <Container>
  <Row className='container-fluid' >
  <Form.Group className='col-4 my-5' controlId="Location">
<Form.Control type="text" placeholder="Enter Location" name="city" value={selectCity} onChange={handleCity} required/>
 </Form.Group>
    <Form.Group  controlId="Sports"className='col-4 my-5'>
    <Form.Select  name="Sports" value={selectSports} onChange={handleSports} required>
        <option value="">Sports</option>
        <option value="Cricket">Cricket</option>
        <option value="Football">Football</option>
        <option value="Badminton">Badminton</option>
        <option value="Volleyball">Volleyball</option>
        <option value="Tennis">Tennis</option>
    </Form.Select>
    </Form.Group>
    <div className='col-4 my-5'>
           <button className='btn btn-primary'>Search</button>
        </div>
    </Row>
    </Container>
    </Form>
    
<div className='container-fluid '>
     {/* console.log(Product) */}
    <div className='row  m-5 ' >
    {currentPageData && currentPageData.map((product) => {
      const {groundID,groundPicture,addressLineOne,groundName,groundType,city,ownerEmail} = product;
                    return(
                      <div className='col-lg-3 col-sm-12 my-5 mx-md-5' key={product.id} >
                      <div className='row' style={{border:"1px solid #e6e6e6",borderRadius:"10px",boxShadow: "5px 5px 5px 5px #e6e6e6"}}>
                      <Image src={groundPicture} height={200} width={100}/>
                         <h6 className='text-center my-3'> {groundName}  </h6>
                            <div className='text-center my-1'><FcSportsMode size="20px"/>  {groundType}</div>
                           <div className=' text-center'><FaCity size="20px" color="darkturquoise"/>  {city}</div>
                          <div className=' text-center'> <FaMapMarkerAlt size="20px" color="green"/>  {addressLineOne}   </div>
                           <Button className='btn btn-primary btn-sm my-4' type="submit" onClick={() =>{handleSubmit(groundID)} }>View Details</Button>
                                    
                       </div>
                      </div>
                   
                    )
                  }
                    )}
        
      </div> 
      <div className='d-flex justify-content-center'>
      <ReactPaginate 
      pageCount={pageCount}
      onPageChange={handlePageClick}
        breakClassName={'page-item'}
        breakLinkClassName={'page-link'}
        containerClassName={'pagination'}
        pageClassName={'page-item'}
        pageLinkClassName={'page-link'}
        previousClassName={'page-item'}
        previousLinkClassName={'page-link'}
        nextClassName={'page-item'}
        nextLinkClassName={'page-link'}
        activeClassName={'active'}
      />
      
      </div> 
      </div>
     
      <div>
                
                </div>


 {/* <Footer/> */}
        </div>   
  )
}


export default GroundList;

