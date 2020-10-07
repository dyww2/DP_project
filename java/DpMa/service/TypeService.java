package DpMa.service;

import DpMa.pojo.dto.ResponseDTO;
import DpMa.pojo.entity.GoodsType;
import DpMa.pojo.vo.GoodsTypeVO;

import java.util.List;


public interface TypeService {
    /**
     * 增加商品类型
     *
     * @param goodsType
     * @return
     */
    ResponseDTO add(GoodsType goodsType);

    /**
     * 查询出所有一级商品类型给前端使用
     *
     * @return
     */
    List<GoodsTypeVO> selectTypesByParentId(int i);

}
