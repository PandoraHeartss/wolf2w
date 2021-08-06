package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.mapper.UserInfoMapper;
import cn.wolfcode.wolf2w.service.IUserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 自定义mybatis-plus 服务层接口实现类
 * 1> 自定义接口 UserInfoServiceImpl
 * 2> 实现自定义接口 IUserInfoService
 * 3> 继承通用接口IService实现类 ServiceImpl
 * 指定2个泛型: 1. 操作实体类mapper接口  2.操作实体对象类型:UserInfo
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {


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
}
