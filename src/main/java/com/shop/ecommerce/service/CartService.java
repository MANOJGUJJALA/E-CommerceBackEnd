package com.shop.ecommerce.service;

import com.shop.ecommerce.exception.ProductException;
import com.shop.ecommerce.modal.Cart;
import com.shop.ecommerce.modal.User;
import com.shop.ecommerce.request.AddItemRequest;



public interface CartService {
    public Cart createCart(User user);

    public String addCartItem(Long userId, AddItemRequest req) throws ProductException;

    public Cart findUserCart(Long userId);
}
