package com.redsheep.service;

import com.redsheep.error.BusinessException;
import com.redsheep.model.UserModel;

/**
 * @author redsheep
 * @date 2019/6/12
 */
public interface UserService {
    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;

    UserModel validateLogin(String tel, String password) throws BusinessException;
}
