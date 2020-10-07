package DpMa.controller.pages.back.goods;

import DpMa.controller.BaseController;
import DpMa.pojo.dto.ResponseDTO;
import DpMa.pojo.entity.Goods;
import DpMa.service.GoodsService;
import DpMa.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;


@Controller
@RequestMapping("/pages/back/goods")
public class GoodsController extends BaseController {

    Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Resource
    GoodsService goodsService;

    @Resource
    TypeService typeService;

    @RequestMapping("addPre")
    String addPre(Model model) {

        model.addAttribute("types", typeService.selectTypesByParentId(-1));
        return "pages/back/goods/goods-addPre";
    }

    /**
     * 真正添加商品的方法
     *
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    Map<String, Object> add(Goods goods, MultipartFile pic) {
        System.err.println(goods);
        if ("on".equals(goods.getOnSale())) {
            goods.setOnSale("YES");
        } else {
            goods.setOnSale("NO");
        }
        String imgUrl = uploadFile("/upload/goods/", pic);
        goods.setImg(imgUrl);


        return goodsService.add(goods);
    }

    /**
     * 真正添加商品的方法
     *
     * @return
     */
    @RequestMapping("getGoodsByTypeId/{typeId}")
    @ResponseBody
    ResponseDTO getGoodsByTypeId(@PathVariable Integer typeId) {
        return goodsService.getGoodsByTypeId(typeId);
    }


}
