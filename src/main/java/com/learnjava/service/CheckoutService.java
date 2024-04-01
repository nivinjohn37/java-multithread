package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CartItem;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutService {
    private PriceValidatorService priceValidatorService;

    public CheckoutService(PriceValidatorService priceValidatorService) {
        this.priceValidatorService = priceValidatorService;
    }

    public void checkout(Cart cart){

        List<CartItem> priceValidationList = cart.getCartItemList()
                .stream()
                .map(cartItem -> {
                    cartItem.setExpired(priceValidatorService.isCartItemInvalid(cartItem));
                    return cartItem;
                })
                .filter(CartItem::isExpired).toList();

    }
}
