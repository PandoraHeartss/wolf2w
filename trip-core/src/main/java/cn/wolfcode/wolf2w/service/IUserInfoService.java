package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * mybatis-plus service 层接口定义规则:
 * 1>自定义一个接口:IUserInfoService
 * 2>继承一个通用接口:IService
 * 3>指定一泛型: 要操作实体对象: UserInfo
 */

public interface IUserInfoService extends IService<UserInfo> {

}
