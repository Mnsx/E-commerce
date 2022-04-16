package top.mnsx.store.controller;

import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.mnsx.store.entity.User;
import top.mnsx.store.service.IUserService;
import top.mnsx.store.service.ex.InsertException;
import top.mnsx.store.service.ex.UsernameDuplicatedException;
import top.mnsx.store.util.JsonResult;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    /**
     * 注册功能
     * @param user 用户数据
     * @return 如果成功将200状态码返回，出现问题，将问题返回
     */
    @RequestMapping("register")
    public JsonResult<Void> reg(User user) {
        // 创建响应结果对象
        userService.register(user);
        return new JsonResult<>(OK);
    }

    /**
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 如果成功将200状态码返回，出现问题，将问题返回
     */
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password);

        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());

        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }
}
