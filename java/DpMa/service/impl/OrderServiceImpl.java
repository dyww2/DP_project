package DpMa.service.impl;

import DpMa.dao.OrderInfoDao;
import DpMa.dao.UserOrderDao;
import DpMa.pojo.dto.ResponseDTO;
import DpMa.pojo.entity.OrderInfo;
import DpMa.pojo.entity.UserOrder;
import DpMa.pojo.vo.ShopCarVO;
import DpMa.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * creator：Administrator
 * date:2019/12/19
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    UserOrderDao userOrderDao;

    @Resource
    OrderInfoDao orderInfoDao;


    @Override
    public ResponseDTO addOrder(UserOrder userOrder, List<ShopCarVO> carVOS) {
        // 编写业务逻辑
        int i = userOrderDao.insertSelective(userOrder);
        int x = 0;
        if (i == 1) {
            // 就应该继续插入订单详情
            for (ShopCarVO s : carVOS) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setOrderId(userOrder.getOrderId());
                orderInfo.setCount(s.getCount());
                orderInfo.setImg(s.getGoods().getImg());
                orderInfo.setOldPrice(s.getGoods().getOldPrice());
                orderInfo.setPrice(s.getGoods().getPrice());
                orderInfo.setOriginGoodId(s.getGoodsId());
                x += orderInfoDao.insert(orderInfo);
            }
            if (x == carVOS.size()) {
                return ResponseDTO.ok("下单成功！");
            } else {
                return ResponseDTO.ok("下单失败！");
            }
        }
        return ResponseDTO.ok("下单失败！");
    }

    @Override
    public UserOrder getOrderDetailByOrderId(String orderId) {
        return userOrderDao.selectByPrimaryKey(orderId);
    }

    @Override
    public boolean updatePayStatus(UserOrder dbOrder) {
        return userOrderDao.updateByPrimaryKeySelective(dbOrder) == 1;
    }
}
