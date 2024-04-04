package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {
    PriceValidatorService priceValidatorService = new PriceValidatorService();
    CheckoutService checkoutService = new CheckoutService(priceValidatorService);

    @Test
    void numberOfCores() {
        System.out.println("no of cores: " + Runtime.getRuntime().availableProcessors());
    }

    @Test
    void checkout_6_items() {
        //given
        Cart cart = DataSet.createCart(6);
        //when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        //then
        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
    }

    @Test
    void checkout_8_items() {
        //given
        Cart cart = DataSet.createCart(8);
        //when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        //then
        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }

    @Test
    void checkout_25_items() {
        //given
        Cart cart = DataSet.createCart(25);
        //when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        //then
        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }

    @Test
    void checkout() {
        //given
        Cart cart = DataSet.createCart(5);
        //when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        //then
        assertTrue(checkoutResponse.getFinalRate() > 0);
    }
}