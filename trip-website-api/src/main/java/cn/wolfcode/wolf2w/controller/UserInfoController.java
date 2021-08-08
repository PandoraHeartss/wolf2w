package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.service.IUserInfoService;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/regist")
    public Object regist(String phone, String nickname, String password, String rpassword, String verifyCode) {

        try {
            //调用注册的业务方法
            userInfoService.regist(phone, nickname, password, rpassword, verifyCode);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error(JsonResult.CODE_ERROR, JsonResult.MSG_ERROR, null);
        }

        return JsonResult.success();
    }


}
