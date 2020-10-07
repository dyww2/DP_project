package DpMa.pojo.vo;

import DpMa.pojo.entity.Goods;
import DpMa.pojo.entity.ShopCar;
import lombok.Data;


@Data
public class ShopCarVO extends ShopCar {
    /**
     * 这个购物车对应的商品
     */
    private Goods goods;


}
