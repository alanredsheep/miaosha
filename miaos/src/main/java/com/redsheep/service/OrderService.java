package com.redsheep.service;

import com.redsheep.error.BusinessException;
import com.redsheep.model.OrderModel;

/**
 * @author redsheep
 * @date 2019/6/14
 */
public interface OrderService {
    OrderModel createOrder(Integer userId,Integer itemId,Integer promoId,Integer amount) throws BusinessException;

}
