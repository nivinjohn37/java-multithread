package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CartItem;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;

import java.util.List;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static com.learnjava.util.LoggerUtil.log;
import static java.util.stream.Collectors.summingDouble;

public class CheckoutService {
    private PriceValidatorService priceValidatorService;

    public CheckoutService(PriceValidatorService priceValidatorService) {
        this.priceValidatorService = priceValidatorService;
    }

    public CheckoutResponse checkout(Cart cart) {
        startTimer();
        List<CartItem> priceValidationList = cart.getCartItemList()
                .parallelStream()
                .map(cartItem -> {
                    cartItem.setExpired(priceValidatorService.isCartItemInvalid(cartItem));
                    return cartItem;
                })
                .filter(CartItem::isExpired).toList();
        timeTaken();
        if (priceValidationList.size() > 0) {
            return new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList);
        }


        double finalPrice = calculateFinalPrice(cart);
        log("Checkout Complete -> final price : " + finalPrice);
        return new CheckoutResponse(CheckoutStatus.SUCCESS, finalPrice);
    }

    private double calculateFinalPrice(Cart cart) {
        return cart.getCartItemList().parallelStream()
                .map(item -> item.getQuantity() * item.getRate()).mapToDouble(Double::doubleValue).sum();
    }

    private double calculateFinalPriceCollect(Cart cart) {
        return cart.getCartItemList().parallelStream()
                .map(item -> item.getQuantity() * item.getRate()).collect(summingDouble(Double::doubleValue));
    }

    private double calculateFinalPriceReduce(Cart cart) {
        return cart.getCartItemList().parallelStream()
                .map(item -> item.getQuantity() * item.getRate())
                .reduce(0.0, (x,y)-> x+y);
    }

    private double calculateFinalPriceReduceMethodReference(Cart cart) {
        return cart.getCartItemList().parallelStream()
                .map(item -> item.getQuantity() * item.getRate())
                .reduce(0.0, Double::sum);
    }
}
