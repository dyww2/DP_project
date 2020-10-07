package DpMa.service;

import DpMa.pojo.dto.ResponseDTO;
import DpMa.pojo.entity.ShopCar;
import DpMa.pojo.vo.ShopCarVO;

import java.util.List;


public interface ShopCarService {
    ResponseDTO edit(ShopCar shopCar);

    /**
     * 根据用户id寻找他的购物车的数量
     *
     * @param userId
     * @return
     */
    Integer getCarCountByUserId(Integer userId);

    /**
     * 根据用户查询购物车列表
     * @param userId
     * @return
     */
    List<ShopCarVO> findUserCars(Integer userId);
}
