package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.exception.LogicException;
import cn.wolfcode.wolf2w.redis.IUserInfoRedisService;
import cn.wolfcode.wolf2w.service.IUserInfoService;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserInfoController {


    @Autowired
    private IUserInfoService userInfoService;


    @GetMapping("/detail")
    public Object detail(Long id) {
        return userInfoService.getById(id);
    }


    /*
     * @Description: 询输入的手机号是否已被注册
     * @param: phone 手机号码
     * @return true为查找到该手机号  false为查找不到该手机号
     * @author PandoraHearts
     * @date 2021/8/6 16:47
     */
    @GetMapping("/checkPhone")
    public Boolean checkPhone(String phone) {
        Boolean ret = userInfoService.checkPhone(phone);
        return ret;
    }


    /*
     * @Description:发送短信验证码
     * @param: phone 手机号码
     * @return 返回json格式的信息
     * @author PandoraHearts
     * @date 2021/8/8 10:26
     */
    @GetMapping("/sendVerifyCode")
    public JsonResult sendVerifyCode(String phone) {
        //调用短信发送的业务方法
        userInfoService.sendVerifyCode(phone);
        return JsonResult.success();
    }


    /*
     * @Description: 注册功能
     * @param: phone 手机号码
     * @param: nickname 昵称
     * @param: password 密码
     * @param: rpassword 确认密码
     * @param: verifyCode 验证码
     * @return java.lang.Object
     * @author PandoraHearts
     * @date 2021/8/8 15:01
     */
//    @PostMapping("/regist")
//    public Object regist(String phone, String nickname, String password, String rpassword, String verifyCode) {
//
//        try {
//            //调用注册的业务方法
//            userInfoService.regist(phone, nickname, password, rpassword, verifyCode);
//        } catch (LogicException e) {
//            //此操作存在问题：给用户看的异常可以抓到，但是系统级别的异常一样可以抓到，而项目开发是不允许给用户看到系统异常的
//            //所以如何区分系统异常，用户异常
//
//            //解决方案：抓用户异常，剩下的都是系统异常
//            //思考：怎样区分用户异常跟系统异常，使用自定义异常表示用户异常，剩下都是系统异常
//            e.printStackTrace();
//            return JsonResult.error(JsonResult.CODE_ERROR, JsonResult.MSG_ERROR, null);
//        } catch (Exception e) {
//            return JsonResult.defaultError();
//        }
//        return JsonResult.success();
//    }


    //区分 用户异常和系统异常 优化后
    @PostMapping("/regist")
    public Object regist(String phone, String nickname, String password, String rpassword, String verifyCode) {
        userInfoService.regist(phone, nickname, password, rpassword, verifyCode);
        return JsonResult.success();
    }


    //登录 功能
    @PostMapping("login")
    public Object login(String username, String password) {
        Map<String, String> map = userInfoService.login(username, password);
        return JsonResult.success(map);
    }

}
