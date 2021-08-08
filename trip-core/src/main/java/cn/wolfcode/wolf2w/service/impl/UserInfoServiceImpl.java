package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.mapper.UserInfoMapper;
import cn.wolfcode.wolf2w.service.IUserInfoService;
import cn.wolfcode.wolf2w.redis.IUserInfoRedisService;

import cn.wolfcode.wolf2w.util.AssertUtil;
import cn.wolfcode.wolf2w.util.Consts;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 自定义mybatis-plus 服务层接口实现类
 * 1> 自定义接口 UserInfoServiceImpl
 * 2> 实现自定义接口 IUserInfoService
 * 3> 继承通用接口IService实现类 ServiceImpl
 * 指定2个泛型: 1. 操作实体类mapper接口  2.操作实体对象类型:UserInfo
 */


@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {


    @Autowired
    private IUserInfoRedisService userInfoRedisService;

    /*
     * @Description: 查询输入的手机号是否已被注册
     * @param: phone
     * @return true为查找到该手机号 false为查找不到该手机号
     * @author PandoraHearts
     * @date 2021/8/6 16:33
     */
    @Override
    public Boolean checkPhone(String phone) {

        //select * from userinfo where phone = phone ---->有值则为true
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);

        return super.getOne(wrapper) != null;
    }


    /*
     * @Description: 短信发送的业务逻辑实现
     * @param: phone 手机号码
     * @return void
     * @author PandoraHearts
     * @date 2021/8/8 10:34
     */
    @Override
    public void sendVerifyCode(String phone) {
        //用UUID创建短信验证码
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 4);
        //拼接短信
        StringBuffer sb = new StringBuffer();
        String letter = sb.append("您的短信验证码是：").append(uuid).append(",请在").append(Consts.VERIFY_CODE_VAI_TIME).append("分钟内登录！").toString();
        //发送短信（这里先假装发送了短信）
        System.out.println(letter);
        //调用redis的业务方法，缓存短信到redis中去
        userInfoRedisService.setVerifyCode(phone, uuid);
    }


    /*
     * @Description: 注册功能的业务逻辑的实现
     * @param: phone 手机号码
     * @param: nickname 昵称
     * @param: password 密码
     * @param: rpassword 确认密码
     * @param: verifyCode 验证码
     * @return void
     * @author PandoraHearts
     * @date 2021/8/8 15:11
     */
    @Override
    public void regist(String phone, String nickname, String password, String rpassword, String verifyCode) {

        //判断参数是否为null
        //断言操作，底层跟StringUtils一样判空，但一般不用项目框架提供的，而是自己写一个这个工具类AssertUtil
        AssertUtil.hasLength(phone, "手机号码不能为空");
        AssertUtil.hasLength(nickname, "昵称不能为空");
        AssertUtil.hasLength(password, "密码不能为空");
        AssertUtil.hasLength(rpassword, "确认密码不能为空");
        AssertUtil.hasLength(verifyCode, "验证码不能为空");

        //判断2次输入密码是否一致
        AssertUtil.isEquals(password, rpassword, "两次输入密码不一样");

        //判断手机格式是否正确 @TODO java 正则

        //判断手机号码是否唯一
        if (this.checkPhone(phone)) {
            throw new RuntimeException("该手机号已被注册");
        }

        //判断验证码是否唯一
        String code = userInfoRedisService.getVerifyCode(phone);
        if (!verifyCode.equalsIgnoreCase(code)) {
            throw new RuntimeException("该验证码输入错误或失效");
        }

        //注册
        UserInfo userInfo = new UserInfo();
        userInfo.setHeadImgUrl("/images/default.jpg");
        userInfo.setPhone(phone);
        userInfo.setNickname(nickname);
        userInfo.setPassword(password);
        userInfo.setState(UserInfo.STATE_NORMAL);
        super.save(userInfo);
    }
}
