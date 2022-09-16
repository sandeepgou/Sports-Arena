import React from 'react';
import { DevToken, StreamChat } from 'stream-chat';
import { Chat, Channel, ChannelHeader, MessageInput, MessageList, Thread, Window } from 'stream-chat-react';
import { useLocation } from 'react-router-dom';
import { useEffect, useState } from 'react';

import 'stream-chat-react/dist/css/index.css';
import axios from 'axios';



const ChatFeature = () => {
    const location = useLocation();
    // const name = location.state.email;
    const [id, setId] = useState('sam');
    const [client, setClient] = useState(null)
    const [channel, setChannel] = useState(null)
    // let id = '';
    const [details, setDetails] = useState({
        name: "",
        phoneNumber: "",
        userEmail: "",
        password: "",
        role: "",
        city: "",
        state: "",
        gender: "",
        profilePhoto: "",
        favSport: ""
    })


    var user = {
        id: 'sam',
        name: 'sam',
    }

    // const userEmail = location.state.email;
    //  const userToken = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiMSJ9.PF5NW2q3qzV5wZJKdD9bn9pVi2y9eJ8JvllJxQgMMUA';

    const getdetails = () => {
        // axios.get(`http://localhost:3000/players?userEmail=${location.state.email}`)
        //     .then((res) => {
        //         console.log(res.data);
        //         let values = res.data[0];
        //         console.log(values);
        //         user.id = values.name;
        //         user.name = values.name
        //         console.log(user);

        //         // console.log("id user: ", user.id);
        //         // // console.log(id);
        //         // setDetails(details => ({
        //         //     ...details, values
        //         // }));

        //     }).catch((Err) => console.log(Err));
    }

    useEffect(() => {
        // getdetails();

        // setTimeout(() => {
        //     getdetails()
        // }, 3000)

        axios.get(`https://sportsarena.stackroute.io/user/api/v1/player/userEmail/${location.state.email}`)
            .then((res) => {
                console.log(res.data);
                let values = res.data;
                console.log(values);
                user.id = values.name;
                user.id.toLowerCase();
                user.name = values.name
                console.log(user);

                async function init() {
                    const chatClient = StreamChat.getInstance('786q87pjsyn2')
                    await chatClient.connectUser({
                        id:(values.name).toLowerCase(),
                        name:values.name
                    }, DevToken((values.name).toLowerCase()))
        
                    const channel = chatClient.channel('messaging', 'sayali', {
                        name: "sayali",
                        // members: [{user_id: 'jack', channel_role: 'channel_member'}]
                    })
                    await channel.create()
                    channel.addMembers([(values.name).toLowerCase()])
                    setChannel(channel)
                    setClient(chatClient)
                }
                init()
                if (client) return () => client.disconnectUser()

                

            }).catch((Err) => console.log(Err));

        // async function init() {
        //     const chatClient = StreamChat.getInstance('g75y38743mbk')
        //     await chatClient.connectUser(user, DevToken(user.id))

        //     const channel = chatClient.channel('messaging', 'sportsArena', {
        //         name: "SportsArena",
        //         // members: [{user_id: 'jack', channel_role: 'channel_member'}]
        //     })
        //     await channel.create()
        //     // channel.addMembers([use])
        //     setChannel(channel)
        //     setClient(chatClient)
        // }
        // init()
        // if (client) return () => client.disconnectUser()
    }, [])

    if (client && channel) {
        return (
            <div>
                <Chat client={client} theme='messaging light'>
                    <Channel channel={channel}>
                        <Window>
                            <ChannelHeader />
                            <MessageList />
                            <MessageInput />
                        </Window>
                        <Thread />
                    </Channel>
                </Chat>
            </div>
        )
    }
    else return null;

};

export default ChatFeature;
