import React from "react";
import { Container, Row } from "react-bootstrap";
import '../Assets/css/style.css';
import { MdEmail } from "react-icons/md"
import { FaPhoneAlt } from "react-icons/fa";

function Footer() {
  return (
    <div className="main-footer " style={{ position: "relative", bottom: 0 ,left:0,right:0,width:"100%"}}>
      <Container >
        <Row>
          {/* Column1 */}
          <div className="col-4 my-3">

            <div className='col-3 my-2'>
              <img className=" img-fluid" src={require('../Assets/images/logo.jpeg')} fluid alt="logo" />
            </div>
          </div>
          {/* Column2 */}
          <div className="col text-center my-3">
            <h4 >CONTACT US</h4>

            <div className="my-3">
              <div> <MdEmail /> E-mail: contact@sportsArenaapp.in</div>
              <div> <FaPhoneAlt /> Phone no:- 9876543210</div>

            </div>
          </div>
          {/* Column3 */}
          <div className="col my-4 text-center">
            <h4 >PAYMENT METHODS</h4>

            <Row className="my-3">
              <div className="col-3">   <img className="image " src={require('../Assets/images/pay.png')} fluid alt="logo" />  </div>
              <div className="col-3">   <img className="image " src={require('../Assets/images/pay1.png')} fluid alt="logo" />  </div>
              <div className="col-3">   <img className="image " src={require('../Assets/images/pay2.png')} fluid alt="logo" />  </div>
              <div className="col-3">   <img className="image " src={require('../Assets/images/pay3.png')} fluid alt="logo" />  </div>
            </Row>

          </div>
        </Row>


      </Container>
    </div>
  );
}

export default Footer;