package DpMa.controller;

import DpMa.dao.UserDao;
import DpMa.pojo.entity.User;
import DpMa.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
public class IndexController {
    @Resource
    UserDao userDao;

    @Resource
    TypeService typeService;


    @RequestMapping("/")
    String index(Model model) {
        User user = userDao.selectByPrimaryKey(1);
        model.addAttribute("types", typeService.selectTypesByParentId(-1));
        System.err.println("你好，李四");
        return "index";
    }

    @RequestMapping("/pages/back/dashBoard")
    String dashBoard() {
        return "pages/back/dashBoard";
    }

}
