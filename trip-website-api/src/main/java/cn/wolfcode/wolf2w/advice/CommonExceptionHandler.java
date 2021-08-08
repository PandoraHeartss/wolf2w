package cn.wolfcode.wolf2w.advice;

import cn.wolfcode.wolf2w.exception.LogicException;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 通用异常处理类
 * ControllerAdvice  controller类功能增强注解, 动态代理方式为controller类实现一些额外功能
 * 请求进入controller映射方法之前做功能增强: 经典用法:日期格式化
 * 请求进入controller映射方法之后做功能增强: 经典用法:统一异常处理
 */
@ControllerAdvice
public class CommonExceptionHandler {
    //这个方法定义的跟映射方法操作一样
    @ExceptionHandler(LogicException.class)
    @ResponseBody
    public Object logicExp(Exception e, HttpServletResponse resp) {
        e.printStackTrace();
        resp.setContentType("application/json;charset=utf-8");
        return JsonResult.error(JsonResult.CODE_ERROR_PARAM, e.getMessage(), null);
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Object runTimeExp(Exception e, HttpServletResponse resp) {
        e.printStackTrace();
        resp.setContentType("application/json;charset=utf-8");
        return JsonResult.defaultError();
    }
}