package com.shop.ecommerce.service;

import com.shop.ecommerce.exception.ProductException;
import com.shop.ecommerce.modal.Cart;
import com.shop.ecommerce.modal.User;
import com.shop.ecommerce.repository.CartRepository;
import com.shop.ecommerce.request.AddItemRequest;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation implements CartService{

    private CartRepository cartRepository;

    public CartServiceImplementation(CartRepository cartRepository) {
        this.cartRepository=cartRepository;

    }

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        Cart createdCart=cartRepository.save(cart);
        return createdCart;
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        return null;
    }

    @Override
    public Cart findUserCart(Long userId) {
        return null;
    }
}
