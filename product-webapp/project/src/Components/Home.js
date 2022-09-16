import react from "react";
import '../Assets/css/list.css';
import { Row } from "react-bootstrap";
import { MdSports } from "react-icons/md";
import { FaSearchLocation } from "react-icons/fa";
import { FcAlarmClock } from "react-icons/fc";

export default function Home() {
  return (

    <div className="body">

      <h1 className="py-5 text-center" style={{ color: 'red', fontFamily: '' }}><i>Let the World Play!</i></h1>

      <h2 className="py-5 text-center" style={{ color: 'red' }}>SALIENT FEATURES</h2>
      <div className="container">
        <div className="row">
          {/* Column1 */}
          <div className="col text-center">

            <FcAlarmClock
              size="70px"
            />
            <h4 >Quick Booking</h4>


            <div className='col-12 text-center'>Once you’ve found the perfect ground, court or gym, Connect with the venue through the Book Now Button to make online booking & secure easier payment! Avail discounts on online transactions.</div>

          </div>
          {/* Column2 */}
          <div className="col text-center">

            <MdSports
              style={{ color: 'blue' }}
              size="70px"
            />

            <h4 className="text-center" >Play</h4>
            <div className='col-12 text-center'>You’re the hero, you’ve found a stunning turf or court, booked with ease and now its time to play. The scene is set for your epic match.</div>
          </div>
          {/* Column3 */}
          <div className="col text-center">
            <FaSearchLocation
              style={{ color: 'white' }}
              size="70px"
            />
            <h4 >Location search</h4>
            <div className="col-12"> Are you looking to play after work, organize your Sunday Five's football match? Explore the largest network of sports facilities whole over the India </div>
          </div>
        </div>


        <div className="col text-center">
          <h2 className="my-5 text-center" style={{ color: 'red' }}>ABOUT US</h2>
          <Row>
            <img className="col-lg-5 col-md-12" src={"https://www.playspots.in/wp-content/uploads/2018/10/aboutus.png"} />

            <div className="offset-lg-1 col-lg-6 col-md-12">
              <div> <strong>SPORTSARENA</strong> is India’s leading Sports recreational platform for Venues, academies and Individuals. </div>
              <div className="my-2">We couldn’t believe there was no one-stop shop for sports players looking to find, contact and book local sports turfs. we set about researching, getting support for and then building a platform that showed all sports venues anywhere in south India, and that was free and easy for anyone to use.We know the urban work-life balance isn’t always easy to juggle and our services takes the hassle away from finding places and co- players</div>
              <div>Our mission is making it easier for everyone to play sports and increasing the utilisation of facilities as a result. Sports play a pivotal role in bringing people together, sharing moments and enhancing the well-being are reasons that form the  essence of SPORTSARENA.</div>

              <h6 className="my-2">Our Goal is making the sports simple #LetTheWorldPlay</h6>
            </div>
          </Row>

        </div>
      </div>
    </div>

  )
}