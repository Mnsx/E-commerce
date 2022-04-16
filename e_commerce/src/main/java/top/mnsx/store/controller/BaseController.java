package top.mnsx.store.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import top.mnsx.store.service.ex.*;
import top.mnsx.store.util.JsonResult;

import javax.servlet.http.HttpSession;

/**
 * 控制层的基类
 */
public class BaseController {
    /**
     * 操作成功
     */
    public static final int OK = 200;
    /**
     * 请求处理方法，这个方法返回值就是需要传递的前端数据
     */
    @ExceptionHandler(ServiceException.class) // 用于统一处理异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>();
        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
            result.setMessage("用户数据不存在");
        } else if (e instanceof PasswordNotMatchException){
            result.setState(4002);
            result.setMessage("用户名的密码错误");
        } else if (e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册数据时产生未知的异常");
        } else if (e instanceof UpdateException){
            result.setState(5001);
            result.setMessage("更新数据时产生未知的异常");
        }
        return result;
    }

    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录的用户uid的值
     */
    protected final Integer getuidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录用户的username
     * @param session session对象
     * @return 当前登录用户的用户名
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
