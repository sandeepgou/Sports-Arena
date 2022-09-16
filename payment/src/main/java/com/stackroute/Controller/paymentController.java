package com.stackroute.Controller;


import com.stackroute.Service.StripeClient;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class paymentController {

    private StripeClient stripeClient;

    @Autowired
    paymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }



    @PostMapping("/charge/{token}/{amount}")
    public Charge chargeCard(@PathVariable("token")String token, @PathVariable("amount")String amount) throws Exception {
        System.out.println("Amount returned "+amount);
        String token1 = token;
        Double amount1 = Double.parseDouble(amount);
        return this.stripeClient.chargeCreditCard(token1, amount1);
    }
}