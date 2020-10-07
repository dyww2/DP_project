package DpMa.pojo.entity;

import java.io.Serializable;

/**
 * order_info
 *
 * @author
 */
public class OrderInfo implements Serializable {
    /**
     * 订单详情表主键id
     */
    private Integer orderInfoId;

    /**
     * 外键哪个订单的
     */
    private String orderId;

    /**
     * 原来商品的连接
     */
    private Integer originGoodId;

    /**
     * 下单时候的商品价格
     */
    private Double price;

    /**
     * 这个商品下单的数量
     */
    private Integer count;

    /**
     * 下单时候商品的图片
     */
    private String img;

    /**
     * 下单时候的原价
     */
    private Double oldPrice;

    private static final long serialVersionUID = 1L;

    public Integer getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(Integer orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOriginGoodId() {
        return originGoodId;
    }

    public void setOriginGoodId(Integer originGoodId) {
        this.originGoodId = originGoodId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }
}
