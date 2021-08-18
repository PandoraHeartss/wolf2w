package cn.wolfcode.wolf2w.redis.service.impl;

import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.redis.service.IUserInfoRedisService;

import cn.wolfcode.wolf2w.util.AssertUtil;
import cn.wolfcode.wolf2w.util.RedisKey;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

        String key = RedisKey.VERIFY_CODE.join(phone);
        //String key = "verify_code" + phone; //key要保证唯一 可读  灵活 失效
        String value = code;

        //参数1：key， 参数2：value,参数3：失效时间，参数4：时间单位
        templates.opsForValue().set(key, value, RedisKey.VERIFY_CODE.getTime(), TimeUnit.SECONDS);
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
        //String code = templates.opsForValue().get("verify_code" + phone);
        String code = templates.opsForValue().get(RedisKey.VERIFY_CODE.join(phone));
        return code;
    }


    /*
     * @Description: 根据token来查询 Redis中的userInfo对象
     * 并重新设置失效时间30分钟
     * @param: token
     * @return UserInfo
     * @author PandoraHearts
     * @date 2021/8/10 22:32
     */
    @Override
    public UserInfo getUserInfoByToken(String token) {
        //判断是否有拿到token，没有就返回null
        if (!StringUtils.hasText(token)) {
            return null;
        }
        //先用token拿到之前设置的RedisKey
        String key = RedisKey.USER_LOGIN_TOKEN.join(token);

        //判断是否有key，没有就返回null
        if (templates.hasKey(key)) {
            //如果有key，再拿到 存在Redis中的userInfo对象对应的字符串
            String UserStr = templates.opsForValue().get(key);
            //调用断言工具类，userInfo对象为空则抛出异常
            AssertUtil.hasLength(UserStr, "并没有用户登录");
            UserInfo userInfo = JSON.parseObject(UserStr, UserInfo.class);
            //重新设置30分钟的失效时间
            templates.expire(key, RedisKey.USER_LOGIN_TOKEN.getTime(), TimeUnit.SECONDS);
            return userInfo;
        }
        return null;
    }
}
