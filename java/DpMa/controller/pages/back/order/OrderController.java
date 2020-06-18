package DpMa.controller.pages.back.order;

import DpMa.controller.BaseController;
import DpMa.pojo.dto.ResponseDTO;
import DpMa.pojo.entity.UserOrder;
import DpMa.pojo.vo.ShopCarVO;
import DpMa.service.AddressService;
import DpMa.service.OrderService;
import DpMa.util.math.MathUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/pages/back/order")
public class OrderController extends BaseController {

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    AddressService addressService;

    @Resource
    OrderService orderService;

    @RequestMapping("addPre")
    String addPre(String orderInfoStr, Model model, Double allCost) {

        System.err.println(orderInfoStr);

        List<ShopCarVO> carVOS = JSON.parseArray(orderInfoStr, ShopCarVO.class);// 变成了一个集合
        // 现在要做的事情就是把这个JSON字符串变成list,用到alibaba的FastJSON
        model.addAttribute("cars", carVOS);
        double allRelief = allCost * Math.random();// 人工给他随机减免
        double actuallyPaid = allCost - allRelief;
        model.addAttribute("actuallyPaid", MathUtil.round(actuallyPaid, 2));
        model.addAttribute("allCost", MathUtil.round(allCost, 2));
        model.addAttribute("allRelief", MathUtil.round(allRelief, 2));

        // 设置用户的默认收获地址

        model.addAttribute("morenAddress", addressService.getDefaultAddress(getUserId()));
        model.addAttribute("addressList", addressService.getUserAddressList(getUserId()));

        model.addAttribute("orderInfoStr", orderInfoStr);


        return "pages/back/order/order-addPre";
    }

    @RequestMapping("add")
    String add(UserOrder userOrder, String orderInfos, Model model) {

        List<ShopCarVO> carVOS = JSON.parseArray(orderInfos, ShopCarVO.class);// 变成了一个集合

        String orderId = System.currentTimeMillis() + MathUtil.getRandomStr(3);
        userOrder.setOrderId(orderId);// 设置订单编号
        userOrder.setStatus("待付款");
        userOrder.setCreateTime(new Date());
        userOrder.setUserId(getUserId());
        System.err.println(userOrder);

        ResponseDTO res = orderService.addOrder(userOrder, carVOS);

        if (res.getRes()) {
            model.addAttribute("order", userOrder);

            model.addAttribute("orderCreateTime", userOrder.getCreateTime().getTime());// long类型的订单创建时间
            return "pages/back/pay/payPre";
        } else {
            model.addAttribute("msg", res.getMsg());
            return "errorPage";
        }


    }


}
