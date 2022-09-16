import axios from "axios";
import { useEffect, useState } from "react";
import ReactPaginate from "react-paginate";
import moment from "moment";
import MyCard from "./MyCard";
import SentInvitation from "./SentInvitation"
import MyInvitation from "./MyInvitation";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function TeamUp() {
  const notify = () => toast.success("Invite sent successfully");
  const [currentTab, setCurrentTab] = useState(1);
  const [paginationData, setPaginationData] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [itemOffset, setItemOffset] = useState(0);
  const [invite, setInvite] = useState([]);
  const [sentInvite, setSentInvite] = useState([]);
  const [rinvite, setrInvite] = useState([]);
  const [pData, setPData] = useState([]);
  const [allPlayer, setAllplayer] = useState([])
  const date = new Date();

  useEffect(() => {
    filterData();
    // filterTeamup();
  }, [itemOffset, 6]);

  console.log(paginationData);

  const filterTeamup = () => {
    axios.get("http://localhost:3000/teamup")
      .then((res) => {
        console.log(res.data)
        setPData(res.data);
      })
      .catch((err) => console.log(err));
  }

  const filterData = () => {
    setCurrentTab(1);
    axios
      .get("https://sportsarena.stackroute.io/user/api/v1/playerlist")
      .then((res) => {
        console.log(res.data);
         let value=[];
        // console.log(value[0].userEmail);
        setAllplayer(res.data)
        // if(res.data.userEmail!="dharanibala17@gmail.com"){
         res.data.map((val)=>{
          console.log(val.userEmail)
          if(val.userEmail!==sessionStorage.getItem("email")){
            value.push(val)
          }
         })
         setPaginationData(value);
      })
      .catch((err) => console.log(err));
  };
  

  async function Team_up(userEmail) {
    console.log(sessionStorage.getItem("email"))
    await axios
      .post("https://sportsarena.stackroute.io/team/api/v1/teamup/sendInvite", {
        senderEmail: sessionStorage.getItem("email"),
        requestedPlayerEmail: userEmail,
        statusOfRequest: "PENDING",
        requestDate: moment(date).format("YYYY-MM-DD"),
      })
      .then((res) => {
        console.log(res);
        notify();
      })
      .catch((err) => console.log(err));
  }

  const onDelete = (data) => {
    console.log("hi");
    console.log(data);
    deleteid(data.teamup_id);
  }

  const deleteid = (id) => {
    console.log(id)
    axios.delete(`https://sportsarena.stackroute.io/team/api/v1/teamup/removeinvite/requestId/${id}`)
      .then((res) => {
        console.log(res);
        sentInviteTab();
      }).catch((err) => console.log(err));
  }

  const onAccept = (data) => {
    console.log("data")
    console.log(data);
    
    updateStatus(data.teamup_id);
  }

  const updateStatus = (teamup_id) => {
    let statusOfRequest = "ACCEPTED"
    //teamup_id="9df7e8e6-0a17-42a5-b7e8-c47c5c467579";
    axios.put(`https://sportsarena.stackroute.io/team/api/v1/teamup/action/requestId/${teamup_id}/requestStatus/${statusOfRequest}`)
      .then((res) => {
        console.log(res)
        myInvite();
      })
  }

  const myInvite = () => {
    setCurrentTab(2);
    setPaginationData([]);
    const reqEmail = sessionStorage.getItem("email");
    axios
      .get(`https://sportsarena.stackroute.io/team/api/v1/teamup/requestedPlayerEmail/${reqEmail}`)
      .then((res) => {
        console.log(res.data);
        let invitedatas = [];
        res.data.map((el) => {
          //console.log("team_upId"+el.requestId);
          let tid = el.requestId;
          let value = el.senderEmail;
          console.log(el.senderEmail);
          let statusOfRequest = el.statusOfRequest;
          allPlayer.map((ele) => {
            if (value == ele.userEmail) {
              let objCopy = Object.assign({}, ele);
              objCopy.teamup_id = tid;
              objCopy.statusOfRequest = statusOfRequest;              
              invitedatas.push(objCopy);
            }
          });
        });
        console.log("myinvite", invitedatas);
        setrInvite(invitedatas);
        setPaginationData(invitedatas)
      })
      .catch((err) => console.log(err));
  };

  const sentInviteTab = () => {
    setCurrentTab(3);
    setPaginationData([]);
    const senderEmail = sessionStorage.getItem("email");
    axios
      .get(`https://sportsarena.stackroute.io/team/api/v1/teamup/senderEmail/${senderEmail}`)
      .then((res) => {
        console.clear();
        // console.log("MyInvites", res.data);
        // console.log("all Data", paginationData);
        setInvite(res.data);
        let invitedata = [];
        res.data.map((el) => {
          let value = el.requestedPlayerEmail;
          let tid = el.requestId;
          let statusOfRequest = el.statusOfRequest;
          console.log(el.requestId);
          allPlayer.map((ele) => {
            if (value === ele.userEmail) {
              let objCopy = Object.assign({}, ele);
              objCopy.teamup_id = tid;
              objCopy.statusOfRequest = statusOfRequest;              
              invitedata.push(objCopy);
              // ele.teamup_id = tid
              // ele.statusOfRequest = statusOfRequest;
              // invitedata.push(ele);
            }
          });
        });
        console.log("res", invitedata);
        setSentInvite(invitedata);
        setPaginationData(invitedata)
      })

      .catch((err) => console.log(err));

    console.log(paginationData, "paginationdata");

  };

  //-----------------Pagination start------------------
  const PER_PAGE = 6;
  const offset = currentPage * PER_PAGE;
  const currentPageData = paginationData.slice(offset, offset + PER_PAGE);
  console.log(currentPageData, "currentpage");
  const pageCount = Math.ceil(paginationData.length / PER_PAGE);
  console.log(pageCount);

  function handlePageClick({ selected: selectedPage }) {
    setCurrentPage(selectedPage);
  }
  //-----------------Pagination End------------------

  return (
    <div className="container-fluid mt-3 p-3">
      <h1 className="text-center mb-5">Team Up</h1>
      <div className="row">
        <div className="col-12 ">
          <div className="d-flex justify-content-center">
            <button
              className={`btn btn-lg d-md-block d-lg-block d-xl-block ${currentTab === 1 ? "btn-primary" : "btn-warning"
                }`}
              onClick={() => filterData()}
            >
              Players List
            </button>
            <button
              className={`btn btn-lg d-md-block d-lg-block d-xl-block ${currentTab === 2 ? "btn-primary" : "btn-warning"
                }`}
              onClick={() => myInvite()}
            >
              My Invitations
            </button>
            <button
              className={`btn btn-lg  d-md-block d-lg-block d-xl-block ${currentTab === 3 ? "btn-primary" : "btn-warning"
                }`}
              onClick={() => sentInviteTab()}
            >
              Sent Invitations
            </button>
          </div>
        </div>
        <ToastContainer />
      </div>
      <div className="row my-5">
        {currentTab === 1 && (
          <>
           {
            currentPageData.map((elem) => (

              <MyCard
                name={elem.name}
                city={elem.city}
                favSport={elem.favSport}
                Team_up={() => Team_up(elem.userEmail)}
              />
            ))}
          </>
        )}
        {currentTab === 2 && (
          <>
            {/* <h1>My Invitations</h1> */}
            {
            console.log(currentPageData)}
            {currentPageData.map((elem) => (

              <MyInvitation
                name={elem.name}
                city={elem.city}
                favSport={elem.favSport}
                status={elem.statusOfRequest}
                Accept={() => onAccept(elem)}
              />
            ))
            }
          </>
        )}
        {currentTab === 3 && (
          <>
            {/* <h1>Sent Invitations</h1> */}
           
              
            
            {
              
              currentPageData.map((elem) => (
              
                <SentInvitation
                  name={elem.name}
                  city={elem.city}
                  favSport={elem.favSport}
                  status={elem.statusOfRequest}
                  email={elem.userEmail}
                  Delete={() => onDelete(elem)}
                />
              ))
              
            }
          </>
        )}
      </div>
      <div className="d-flex justify-content-center">
        <ReactPaginate
          pageCount={pageCount}
          onPageChange={handlePageClick}
          breakClassName={"page-item"}
          breakLinkClassName={"page-link"}
          containerClassName={"pagination"}
          pageClassName={"page-item"}
          pageLinkClassName={"page-link"}
          previousClassName={"page-item"}
          previousLinkClassName={"page-link"}
          nextClassName={"page-item"}
          nextLinkClassName={"page-link"}
          activeClassName={"active"}
        />
      </div>
    </div>
  );
}

export default TeamUp;

