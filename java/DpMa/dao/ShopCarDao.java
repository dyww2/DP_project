package DpMa.dao;

import DpMa.pojo.entity.ShopCar;
import DpMa.pojo.vo.ShopCarVO;

import java.util.List;

public interface ShopCarDao {
    int deleteByPrimaryKey(Integer shopCarId);

    int insert(ShopCar record);

    int insertSelective(ShopCar record);

    ShopCar selectByPrimaryKey(Integer shopCarId);

    int updateByPrimaryKeySelective(ShopCar record);

    int updateByPrimaryKey(ShopCar record);

    /**
     * 根据用户id和商品id查找购物车
     * @param shopCar
     * @return
     */
    ShopCar findCarByUserIdAndGoods(ShopCar shopCar);

    Integer selectCarCountByUserId(Integer userId);

    /**
     * 根据用户id查询其购物车
     * @param userId
     * @return
     */
    List<ShopCarVO> findUserCars(Integer userId);
}
