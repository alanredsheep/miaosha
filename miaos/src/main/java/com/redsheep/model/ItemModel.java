package com.redsheep.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author redsheep
 * @date 2019/6/13
 */
public class ItemModel {

    private Integer id;
    @NotBlank(message = "请填写商品名称")
    private String title;
    @NotNull(message = "请填写商品价格")
    @Min(value = 0, message = "价格必须大于0")
    private BigDecimal price;
    @NotNull(message = "请填写库存")
    private Integer stock;
    @NotBlank(message = "请填写商品描述")
    private String description;
    private Integer sales;
    @NotBlank(message = "请选择商品图片")
    private String imgUrl;

    public PromoModel getPromoModel() {
        return promoModel;
    }

    public void setPromoModel(PromoModel promoModel) {
        this.promoModel = promoModel;
    }

    private PromoModel promoModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
