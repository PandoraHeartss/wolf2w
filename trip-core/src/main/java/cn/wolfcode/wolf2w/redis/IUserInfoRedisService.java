package cn.wolfcode.wolf2w.redis;

import cn.wolfcode.wolf2w.domain.UserInfo;

//redis 相关接口
public interface IUserInfoRedisService {

    /*
     * @Description:
     * @param: phone手机号码，code验证码
     * @return void
     * @author PandoraHearts
     * @date 2021/8/8 11:06
     */
    void setVerifyCode(String phone, String code);


    /*
     * @Description: 根据手机号码获取之前存进去的验证码
     * @param: phone 手机号码
     * @return 返回验证码
     * @author PandoraHearts
     * @date 2021/8/8 15:36
     */
    String getVerifyCode(String phone);

    /*
     * @Description: 根据token来查询 Redis中的userInfo对象
     * @param: token
     * @return UserInfo
     * @author PandoraHearts
     * @date 2021/8/10 22:32
     */
    UserInfo getUserInfoByToken(String token);
}
