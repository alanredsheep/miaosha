package com.redsheep.service;

import com.redsheep.model.PromoModel;

/**
 * @author redsheep
 * @date 2019/6/15
 */
public interface PromoService {

    PromoModel getPromoByItemId(Integer itemId);

}
