package cn.wolfcode.wolf2w.redis.service;

import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVO;

import java.util.List;

/*
    攻略数据统计缓存服务接口
 */
public interface IStrategyStatisVOService {

    //阅读数+1
    void viewnumIncr(Long strategyId);

    //获取vo对象，用于统计数据的回显
    StrategyStatisVO getStatisVo(Long sid);


    //设置vo对象
    void setStatisVo(StrategyStatisVO vo);


    //攻略收藏的实现
    //返回true 表示 收藏成功
    //返回false 表示 取消收藏
    Boolean favor(Long userId, Long sid);


    //根据用户id查出攻略收藏id列表
    List<Long> queryStrategyFavorByUserId(Long userId);


    //攻略点赞的实现
    //返回true 表示点赞成功
    //放回false 表示点赞失败
    Boolean strategyThumbup(Long userId, Long sid);


    //根据攻略id判断redis中是否存在已经复制好的vo对象
    //返回true 表示存在
    //返回false 表示不存在
    Boolean isStrategyExist(Long sid);


    //通过查询规则查询所有vo对象
    List<StrategyStatisVO> queryVOByPattern(String pattern);
}
