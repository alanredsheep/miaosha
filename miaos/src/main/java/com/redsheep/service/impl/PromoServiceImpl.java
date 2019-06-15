package com.redsheep.service.impl;

import com.redsheep.dao.PromoDOMapper;
import com.redsheep.dataObject.PromoDO;
import com.redsheep.model.PromoModel;
import com.redsheep.service.PromoService;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author redsheep
 * @date 2019/6/15
 */
@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDOMapper promoDOMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {

        PromoDO promoDO=promoDOMapper.selectByItemId(itemId);
        PromoModel promoModel=convert2PromoModel(promoDO);
        if(promoModel==null){
            return null;
        }

        if(promoModel.getStartTime().isAfterNow()){
            promoModel.setStatus(1);
        }else if(promoModel.getEndTime().isBeforeNow()){
            promoModel.setStatus(3);
        }else{
            promoModel.setStatus(2);
        }

        return promoModel;
    }

    private PromoModel convert2PromoModel(PromoDO promoDO){
        if(promoDO==null){
            return null;
        }
        PromoModel promoModel=new PromoModel();
        BeanUtils.copyProperties(promoDO,promoModel);
        promoModel.setPromoItemPrice(promoDO.getItemPrice());
        promoModel.setStartTime(new DateTime((promoDO.getStartTime())));
        promoModel.setEndTime(new DateTime((promoDO.getEndTime())));
        return promoModel;
    }
}
