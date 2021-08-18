package cn.wolfcode.wolf2w.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @author PandoraHearts
 * @Description: RedisKey的管理类
 * 约定一个枚举实例，对应着一个RedisKey
 * @date 2021/8/9 6:42
 */
@Getter
public enum RedisKey {

    //攻略统计vo key，-1表示不设置超时
    STRATEGY_STATIS_VO("strategy_statis_vo", -1L),

    //注册短信验证码
    VERIFY_CODE("verify_code", Consts.VERIFY_CODE_VAI_TIME * 60L),

    //用户第一次登录的时候 要用token作为key
    USER_LOGIN_TOKEN("user_login_token", Consts.USER_INFO_TOKEN_VAI_TIME * 60L);


    @Setter
    private String prefix; //redis 的 key 的前缀
    @Setter
    private Long time;//redis 的key 的有效时间

    private RedisKey(String prefix, Long time) {
        this.prefix = prefix;
        this.time = time;
    }

    private RedisKey() {

    }

    //拼接redis 的key (拼接多个参数的时候也可以使用)
    public String join(String... values) {
        StringBuffer sb = new StringBuffer(80);
        sb.append(this.prefix);
        for (String value : values) {
            sb.append(":").append(value);
        }
        return sb.toString();
    }


    public static void main(String[] args) {

        String s = RedisKey.VERIFY_CODE.join("1372987458", "xxx", "xxx");
        System.out.println(s);

    }

}
