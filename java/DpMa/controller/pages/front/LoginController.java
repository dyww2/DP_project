package DpMa.controller.pages.front;

import DpMa.pojo.entity.User;
import DpMa.service.ShopCarService;
import DpMa.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/pages/front")
public class LoginController {
    @Resource
    UserService userService;

    @Resource
    ShopCarService shopCarService;


    @RequestMapping("loginPage")
    String loginPage() {
        return "pages/front/login/loginPage";
    }

    /**
     * 验证用户名密码执行登录操作的方法
     *
     * @return
     */
    @RequestMapping("login")
    String login(User user, Model model, HttpSession session) {

        if ("".equals(user.getPhone()) || user.getPhone() == null) {// 你电话都不传，就不必查了，直接登录失败
            model.addAttribute("errorMsg", "请输入手机号登录！");
            return "pages/front/login/loginPage"; // 继续返回登录页面继续登录。
        }
        boolean loginResult = userService.login(user);

        if (loginResult) {// 登录成功，返回到后台首页
            User dbUser = userService.getUserByPhone(user.getPhone());
            session.setAttribute("userId", dbUser.getUserId());
            session.setAttribute("carCount", shopCarService.getCarCountByUserId(dbUser.getUserId()));
            return "pages/back/backHome";
        }
        model.addAttribute("errorMsg", "登陆失败，手机号或密码错误！");
        return "pages/front/login/loginPage";
    }
  @RequestMapping("toPage")
    String toPage(HttpServletRequest request){
      String url = request.getParameter("url");
      return url;

  }
@RequestMapping("register")
    String regist(User user){
       userService.register(user);
    return "pages/front/login/loginPage";
}


}
