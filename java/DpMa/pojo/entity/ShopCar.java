package DpMa.pojo.entity;

import java.io.Serializable;

/**
 * shop_car
 * @author 
 */
public class ShopCar implements Serializable {
    private Integer shopCarId;

    private Integer userId;

    private Integer goodsId;

    private Integer count;

    private static final long serialVersionUID = 1L;

    public Integer getShopCarId() {
        return shopCarId;
    }

    public void setShopCarId(Integer shopCarId) {
        this.shopCarId = shopCarId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}