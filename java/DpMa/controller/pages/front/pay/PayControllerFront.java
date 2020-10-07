package DpMa.controller.pages.front.pay;

import DpMa.config.alipay.AlipayConfig;
import DpMa.pojo.entity.UserOrder;
import DpMa.service.OrderService;
import com.alipay.api.internal.util.AlipaySignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/pages/front/pay")
public class PayControllerFront {
    @Resource
    OrderService orderService;

    Logger logger = LoggerFactory.getLogger(PayControllerFront.class);

    // 支付宝的通知的同步接口
    @RequestMapping("/aliPayResTb")
    String aliPayResTb() {
        return "redirect:/pages/back/order/list";
    }

    // 支付宝的通知的异步接口
    @RequestMapping("/aliPayResYb")
    @ResponseBody
    String aliPayResYb(HttpServletRequest request) {
        return checkSignAndUpdateOrder(request);
    }

    private String checkSignAndUpdateOrder(HttpServletRequest request) {
        logger.debug(request.getRequestURL().toString());
        logger.debug("支付宝支付结果异步通知" + request.getParameterMap().toString());
        Map<String, String> params = new HashMap<>();//先把请求参数封装为map集合
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String pName = names.nextElement();
            params.put(pName, request.getParameter(pName));
        }
        try {
            boolean signRes;
            //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
            //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
            //这里必须要协商signType,验证签名
            signRes = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, "utf-8", "RSA2");
            String appid = request.getParameter("app_id");
            if (AlipayConfig.app_id.equals(appid) && signRes) { //首先判断appid是不是正确的，再看签名是不是来自于支付宝
                String morderid = request.getParameter("out_trade_no");// 拿到交易单号，订单号
                String zonge = request.getParameter("total_amount"); // 拿到支付宝给我们返回的支付成功的金额

                UserOrder dbOrder = orderService.getOrderDetailByOrderId(morderid);
                if (String.valueOf(dbOrder.getActualPayment()).equals(zonge)) {// 付款成功
                    String trade_status = request.getParameter("trade_status");
                    dbOrder.setStatus("待发货");
                    if ("TRADE_SUCCESS".equals(trade_status)) {
                        dbOrder.setPayTime(new Date());
                        // 更新订单的某些状态
                        if (orderService.updatePayStatus(dbOrder)) {//付款成功后
                            logger.debug("已收到支付宝付款通知，并且已经修改数据库中的订单状态");
                            return "success"; // 一定要给支付宝一个答案(回复)，到底是收到消息了还是我挂了，或者是网络问题，没收到
                        }
                    } else if ("TRADE_CLOSED".equals(trade_status)) {
//                        mymorder.setZfRes("已退款");
//                        if (morderService.updatePayRes(mymorder)) {//退款成功后
//                            System.err.println("已收到通知-已退款");
//                        }
                    }
                    return "success";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

}
