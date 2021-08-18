package cn.wolfcode.wolf2w.interceptor;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/*
 * @Description: 用户登录拦截器
 * @author PandoraHearts
 * @date 2021/8/11 21:34
 */
@Component
public class CheckLoginInterceptor implements HandlerInterceptor {

//    @Autowired
//    private UserInfoRedisServiceImpl userInfoRedisService;
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        //可以直接放行预请求
//        if (handler instanceof HandlerMethod){
//            return false;
//        }
//
//        //从浏览器的请求头中拿到token
//        String token = request.getHeader("token");
//        //调用断言工具类,如果token为空，则抛出异常
//        //AssertUtil.hasLength(token,"token不能为空");
//        //根据拿到的token去取出userInfo 对象
//        UserInfo userInfo = userInfoRedisService.getUserInfoByToken(token);
//        //userInfo对象为空，则说明没有用户登录，响应错误信息给页面
//        if(userInfo == null){
//            response.setContentType("application/json;charset=utf-8");
//            response.getWriter().write(JSON.toJSONString(JsonResult.noLogin()));
//            return false;
//        }
//        return true;
//    }
}
