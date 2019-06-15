package com.redsheep.service.impl;

import com.redsheep.dao.ItemDOMapper;
import com.redsheep.dao.ItemStockDOMapper;
import com.redsheep.dataObject.ItemDO;
import com.redsheep.dataObject.ItemStockDO;
import com.redsheep.error.BusinessException;
import com.redsheep.error.EmBusinessError;
import com.redsheep.model.ItemModel;
import com.redsheep.model.PromoModel;
import com.redsheep.service.ItemService;
import com.redsheep.service.PromoService;
import com.redsheep.validator.ValidationResult;
import com.redsheep.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author redsheep
 * @date 2019/6/13
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private ItemDOMapper itemDOMapper;

    @Autowired
    private ItemStockDOMapper itemStockDOMapper;

    @Autowired
    private PromoService promoService;

    private ItemDO convert2ItemDO(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel, itemDO);
        return itemDO;
    }

    private ItemStockDO convert2ItemStockDO(ItemModel itemModel){
        if(itemModel==null){
            return null;
        }
        ItemStockDO itemStockDO=new ItemStockDO();
        itemStockDO.setItemId(itemModel.getId());
        itemStockDO.setStock(itemModel.getStock());
        return itemStockDO;
    }

    private ItemModel convert2ItemModel(ItemDO itemDO,ItemStockDO itemStockDO){
        ItemModel itemModel=new ItemModel();
        BeanUtils.copyProperties(itemDO,itemModel);
        itemModel.setStock((itemStockDO.getStock()));
        return itemModel;
    }

    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        ValidationResult result = validator.validate(itemModel);
        if (result.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, result.getErrMsg());
        }

        ItemDO itemDO = this.convert2ItemDO(itemModel);
        itemDOMapper.insertSelective(itemDO);

        itemModel.setId(itemDO.getId());

        ItemStockDO itemStockDO=this.convert2ItemStockDO(itemModel);
        itemStockDOMapper.insertSelective(itemStockDO);

        return this.getItemById(itemModel.getId());
    }

    @Override
    public List<ItemModel> listItem() {
        List<ItemDO> itemDOList=itemDOMapper.listItem();
        List<ItemModel> itemModelList=itemDOList.stream().map(itemDO->{
            ItemStockDO itemStockDO=itemStockDOMapper.selectByItemId(itemDO.getId());
            ItemModel itemModel=this.convert2ItemModel(itemDO,itemStockDO);
            return itemModel;
        }).collect(Collectors.toList());
        return itemModelList;
    }


    @Override
    public ItemModel getItemById(Integer id) {
        ItemDO itemDO=itemDOMapper.selectByPrimaryKey(id);
        if(itemDO==null){
            return null;
        }
        ItemStockDO itemStockDO=itemStockDOMapper.selectByItemId(itemDO.getId());
        ItemModel itemModel=convert2ItemModel(itemDO,itemStockDO);

        PromoModel promoModel=promoService.getPromoByItemId(itemModel.getId());
        if (promoModel != null&&promoModel.getStatus().intValue()!=3) {
            itemModel.setPromoModel((promoModel));
        }
        return itemModel;
    }

    @Transactional
    @Override
    public boolean decreaseStock(Integer itemId, Integer amount) {
        int affectedRow=itemStockDOMapper.decreaseStock(itemId,amount);
        if(affectedRow>0){
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    @Override
    public void increaseSales(Integer itemId, Integer amount) throws BusinessException {
        itemDOMapper.increaseSales(itemId,amount);
    }
}
