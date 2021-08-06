package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
     * @param: null
     * @return true为查找到该手机号  false为查找不到该手机号
     * @author PandoraHearts
     * @date 2021/8/6 16:47
     */
    @GetMapping("/checkPhone")
    public Boolean checkPhone(String phone) {
        Boolean ret = userInfoService.checkPhone(phone);
        return ret;
    }


}
