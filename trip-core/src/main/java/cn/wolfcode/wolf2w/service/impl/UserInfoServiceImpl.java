package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.mapper.UserInfoMapper;
import cn.wolfcode.wolf2w.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  自定义mybatis-plus 服务层接口实现类
 *  1> 自定义接口 UserInfoServiceImpl
 *  2> 实现自定义接口 IUserInfoService
 *  3> 继承通用接口IService实现类 ServiceImpl
 *     指定2个泛型: 1. 操作实体类mapper接口  2.操作实体对象类型:UserInfo
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
