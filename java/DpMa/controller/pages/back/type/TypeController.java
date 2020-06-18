package DpMa.controller.pages.back.type;

import DpMa.controller.BaseController;
import DpMa.pojo.dto.ResponseDTO;
import DpMa.pojo.entity.GoodsType;
import DpMa.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


@Controller
@RequestMapping("/pages/back/type")
public class TypeController extends BaseController {


    Logger logger = LoggerFactory.getLogger(TypeController.class);

    @Resource
    TypeService typeService;


    @RequestMapping("addPre")
    String addPre(Model model) {
        model.addAttribute("types",typeService.selectTypesByParentId(-1));
        return "pages/back/type/type-addPre";
    }

    /**
     * 真正添加商品的方法
     *
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    ResponseDTO add(GoodsType goodsType) {

        return  typeService.add(goodsType);
    }


}
