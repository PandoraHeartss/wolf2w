package cn.wolfcode.wolf2w.redis.impl;

import cn.wolfcode.wolf2w.redis.IUserInfoRedisService;

import cn.wolfcode.wolf2w.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//redis相关的业务逻辑的实现
@Service
public class UserInfoRedisServiceImpl implements IUserInfoRedisService {

    @Autowired
    private StringRedisTemplate templates;

    /*
     * @Description: 以手机号为key,验证码为value 存到redis中去
     * @param: phone 手机号码
     * @param: code 验证码
     * @return void
     * @author PandoraHearts
     * @date 2021/8/8 11:18
     */
    @Override
    public void setVerifyCode(String phone, String code) {
        String key = "verify_code" + phone; //key要保证唯一 可读  灵活 失效
        String value = code;

        //参数1：key， 参数2：value,参数3：失效时间，参数4：时间单位
        templates.opsForValue().set(key, value, Consts.VERIFY_CODE_VAI_TIME * 60L, TimeUnit.SECONDS);
    }


    /*
     * @Description: 根据手机号码获取之前存进去的验证码
     * @param: phone 手机号码
     * @return java.lang.String
     * @author PandoraHearts
     * @date 2021/8/8 15:36
     */
    @Override
    public String getVerifyCode(String phone) {

        String code = templates.opsForValue().get("verify_code" + phone);
        return code;
    }
}
