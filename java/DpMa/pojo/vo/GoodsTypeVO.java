package DpMa.pojo.vo;

import DpMa.pojo.entity.GoodsType;
import lombok.Data;

import java.util.List;


@Data
public class GoodsTypeVO extends GoodsType {
    /**
     * 装子商品类型的集合
     */
    private List<GoodsTypeVO> childrenTypes;

}
