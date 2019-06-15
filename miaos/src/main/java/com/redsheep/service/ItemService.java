package com.redsheep.service;

import com.redsheep.error.BusinessException;
import com.redsheep.model.ItemModel;

import java.nio.BufferOverflowException;
import java.util.List;

/**
 * @author redsheep
 * @date 2019/6/13
 */
public interface ItemService {
    ItemModel createItem(ItemModel itemModel) throws BufferOverflowException, BusinessException;

    List<ItemModel> listItem();

    ItemModel getItemById(Integer id);

    boolean decreaseStock(Integer itemId,Integer amount);

    void increaseSales(Integer itemId,Integer amount)throws BusinessException;
}
