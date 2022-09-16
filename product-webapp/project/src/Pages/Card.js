import { CardElement, ElementsConsumer } from '@stripe/react-stripe-js';
import React, { Component } from 'react'


const CARD_ELEMENT_OPTION = {
      style:{
        base:{
            color:"green",
            fontSize: "24px",
            fontFamily : "sans-serif",
            fontSmoothig: "antialised",
            "::placeholder" :{
                color:"#dfdfdf"
            },
        },
        invalid:{
            color:"red",
            ":focus":{
                color:"red"},
        }
      }
}
class Card extends Component {
    handleSubmit = async (event)=>{
        event.preventDefault();
        const {stripe,elements} = this.props;
        if(!stripe || !elements) return;
        const card = elements.getElement(CardElement);
        const result = await stripe.createToken(card);
        if(result.error){
            console.log(result.error.message);
        }else{
            console.log(result.token);
        }
    }

    
    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <lable style={{height:"300px"}}>
                        Fill Your Card Details
                        <div className=''>
                        <CardElement options={CARD_ELEMENT_OPTION}></CardElement>
                        </div>
                    </lable>

                    
                    <button className='btn btn-lg btn-primary'>Pay Now</button>
                </form>
            </div>
        )
    }
}
export default function InjectCheckout(){
    return(
        <ElementsConsumer>
           {
            ({stripe,elements})=>(
                <Card stripe={stripe} elements={elements}/>
            )
           }
        </ElementsConsumer>
    )
}