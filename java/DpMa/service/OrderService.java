package DpMa.service;

import DpMa.pojo.dto.ResponseDTO;
import DpMa.pojo.entity.UserOrder;
import DpMa.pojo.vo.ShopCarVO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface OrderService {

    @Transactional(propagation = Propagation.REQUIRED)
        // 开启事务控制
    ResponseDTO addOrder(UserOrder userOrder, List<ShopCarVO> carVOS);

    UserOrder getOrderDetailByOrderId(String orderId);

    /**
     * 更新付款状态
     *
     * @param dbOrder
     * @return
     */
    boolean updatePayStatus(UserOrder dbOrder);
}
