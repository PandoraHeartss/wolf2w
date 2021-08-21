package cn.wolfcode.wolf2w.job;

import cn.wolfcode.wolf2w.redis.service.IStrategyStatisVOService;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVO;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.util.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 将redis数据持久化到mysql中
 * 定时任务
 */
@Component
public class RedisDataPersistenceJob {

    @Autowired
    private IStrategyStatisVOService strategyStatisVOService;

    @Autowired
    private IStrategyService strategyService;


    //10s钟操作一次
    @Scheduled(cron = "0/10 * * * * ?")
    public void doWork() {

        System.out.println("-----------------vo对象持久化-begin----------------------");

        //从redis中获取所有vo对象
        String pattern = RedisKey.STRATEGY_STATIS_VO.join("*");
        List<StrategyStatisVO> vos = strategyStatisVOService.queryVOByPattern(pattern);

        //遍历vo对象， 将数据更新到对应攻略表中
        for (StrategyStatisVO vo : vos) {
            strategyService.updateStatisVO(vo);
        }

        System.out.println("-----------------vo对象持久化-end----------------------");
    }
}
