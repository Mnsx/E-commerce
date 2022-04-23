package top.mnsx.store.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.mnsx.store.interceptor.LoginInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理器拦截器的注册
 */
@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    /**
     * 配置拦截器
     * @param registry 注册器对象
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置白名单
        HandlerInterceptor interceptor = new LoginInterceptor();
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/register");
        patterns.add("/users/login");
        patterns.add("/districts/*");
        patterns.add("/products/**");

       registry.addInterceptor(interceptor)
               .addPathPatterns("/**")
               .excludePathPatterns(patterns);
    }
}
