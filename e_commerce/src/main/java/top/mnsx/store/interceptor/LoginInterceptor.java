package top.mnsx.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义一个拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session对象中是否有uid数据，如果有则放行，如果没有重定向登录姐买你
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器
     * @return 如果返回true，表示放行当前请求，false拦截当前请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object obj = request.getSession().getAttribute("uid");
        if(obj == null) {
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
