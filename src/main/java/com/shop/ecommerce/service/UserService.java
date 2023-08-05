package com.shop.ecommerce.service;

import com.shop.ecommerce.exception.UserException;
import com.shop.ecommerce.modal.User;

public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;

}
