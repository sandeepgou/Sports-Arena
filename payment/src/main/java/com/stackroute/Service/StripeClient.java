package com.stackroute.Service;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeClient {
    @Autowired
    StripeClient() {
        Stripe.apiKey = "sk_test_51LQTCTA511UTIYLSmN2OvR3bQuabruiuB9YqtrS0o56klZ7kxnHMraG5YMfjJu6d2IaQSEdaSDTxWRTrirqiuDLV00BmA1Nxqz";
    }


    public Charge chargeCreditCard(String token, Double amount) throws APIConnectionException, APIException, AuthenticationException, InvalidRequestException, CardException {

        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;

    }
}
