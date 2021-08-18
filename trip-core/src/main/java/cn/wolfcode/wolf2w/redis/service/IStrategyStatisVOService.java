package cn.wolfcode.wolf2w.redis.service;

import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVO;

/*
    攻略数据统计缓存服务接口
 */
public interface IStrategyStatisVOService {

    //阅读数+1
    void viewnumIncr(Long strategyId);

    //获取vo对象，用于统计数据的回显
    StrategyStatisVO getStatisVo(Long sid);
}
