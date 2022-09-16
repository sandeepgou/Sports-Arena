import { loadStripe } from '@stripe/stripe-js';
import { Elements } from '@stripe/react-stripe-js';
import React from 'react';
import InjectCheckout from './Card';
const stripePromise = loadStripe("pk_test_51LNKm7SDjt6yxjL4SSm086Ew6SgMu1Jlhk4S1YT6qAdVUvwMRMP5exsLpQKJW9H5GLJqNkzVy4dV5htOJBU79Ag500YIV4Nici");
console.log(stripePromise.elements);
export default function Payment() {
  return (
    <div className='container m-5 p-5'>
      <Elements stripe={stripePromise}>
        <InjectCheckout/>
      </Elements>
    </div>
  )
}
