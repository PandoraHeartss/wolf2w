package cn.wolfcode.wolf2w.resolver;

import cn.wolfcode.wolf2w.annotation.UserParam;
import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.redis.IUserInfoRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/*
自定义参数解析器
作用：将接口(请求映射方法)UserInfo类型 的参数解析成当前登录用户对象
*/
public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {


    @Autowired
    private IUserInfoRedisService userInfoRedisService;

    //当前解析器能解析类参数类：UseInfo.class
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == UserInfo.class
                //有贴这个自定义注解才使用解析器解析
                && parameter.hasParameterAnnotation(UserParam.class);
    }


    //当发现接口参数是UserInfo类型，该如何解析
    //此处解析：获取当前登录用户对象注入 接口userInfo变量中
    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        //方式一：String token = webRequest.getHeader("token");
        //方式二：其实就是方式一的一个底层
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String token = request.getHeader("token");

        //注入
        return userInfoRedisService.getUserInfoByToken(token);
    }
}
