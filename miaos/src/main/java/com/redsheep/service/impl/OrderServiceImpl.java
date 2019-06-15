package com.redsheep.service.impl;

import com.redsheep.dao.OrderDOMapper;
import com.redsheep.dao.SequenceDOMapper;
import com.redsheep.dataObject.OrderDO;
import com.redsheep.dataObject.SequenceDO;
import com.redsheep.error.BusinessException;
import com.redsheep.error.EmBusinessError;
import com.redsheep.model.ItemModel;
import com.redsheep.model.OrderModel;
import com.redsheep.model.UserModel;
import com.redsheep.service.ItemService;
import com.redsheep.service.OrderService;
import com.redsheep.service.UserService;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.awt.EmbeddedFrame;

import java.awt.image.BufferStrategy;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * @author redsheep
 * @date 2019/6/14
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDOMapper orderDOMapper;



    @Transactional
    @Override
    public OrderModel createOrder(Integer userId, Integer itemId,Integer promoId ,Integer amount) throws BusinessException {

        ItemModel itemModel = itemService.getItemById(itemId);
        if (itemModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品不存在");
        }

        UserModel userModel = userService.getUserById(userId);
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户不存在");
        }

        if (amount <= 0) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品数量不合法");
        }

        if(promoId!=null){
            if(promoId.intValue()!=itemModel.getPromoModel().getId()){
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "活动信息不正确");
            }else if(itemModel.getPromoModel().getStatus().intValue()!=2){
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "活动信息不正确");
            }
        }

        boolean success = itemService.decreaseStock(itemId, amount);
        if (!success) {
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }

        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        if (promoId != null) {
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        }else {
            orderModel.setItemPrice(itemModel.getPrice());
        }
        orderModel.setPromoId(promoId);
        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(new BigDecimal(amount)));
        orderModel.setId(generateOrderNo());

        OrderDO orderDO = convert2OrderDO(orderModel);
        orderDOMapper.insertSelective(orderDO);

        itemService.increaseSales(itemId,amount);

        return orderModel;
    }

    private OrderDO convert2OrderDO(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        return orderDO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private String generateOrderNo() {
        StringBuilder stringBuilder=new StringBuilder();

        LocalDateTime now = LocalDateTime.now();
        String nowTime = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
        stringBuilder.append(nowTime);

        int sequence=0;
        SequenceDO sequenceDO= sequenceDOMapper.getSequenceByName("order_info_id");

        sequence=sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue((sequenceDO.getCurrentValue()+sequenceDO.getStep()));
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        String strSequence=String.valueOf(sequence);
        for(int i =0;i<6-strSequence.length();i++){
            stringBuilder.append(0);
        }
        stringBuilder.append(strSequence);

        stringBuilder.append("00");

        return stringBuilder.toString();
    }
}
