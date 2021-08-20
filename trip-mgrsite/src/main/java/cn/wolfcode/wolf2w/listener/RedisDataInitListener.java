package cn.wolfcode.wolf2w.listener;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.redis.service.IStrategyStatisVOService;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVO;
import cn.wolfcode.wolf2w.service.IStrategyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * redis 数据初始化监听器
 * ContextRefreshedEvent： spring容器更新事件
 */
//@Component
public class RedisDataInitListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private IStrategyService strategyService;
    @Autowired
    private IStrategyStatisVOService strategyStatisVOService;

    //当spring容器启动并初始化完成后执行
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("--------------------攻略统计vo对象初始化-begin------------------------------");
        //1:查询mysql中所有攻略数据List<Strategy>
        List<Strategy> list = strategyService.list();
        //2：封装成vo对象

        /**
         第一次请求项目，项目vo初始化完成
         然后前端对vo数据进行访问(阅读，评论，收藏，点赞，分享 ... )，此时的vo对象数据都会发生变化

         此时，需要第二次启动项目，如果如果再执行vo初始化会出现什么问题？
         之前前端操作vo统计数据全部查询被覆盖

         解决方案：如果redis中已经存了vo了，跳过即可，不初始化
         */

        //遍历这些攻略对象,封装vo对象，缓存redis中
        for (Strategy strategy : list) {
            //根据攻略id判断redis中是否存在已经复制好的vo对象,如果存在，则停止本次循环
            if (strategyStatisVOService.isStrategyExist(strategy.getId())) {
                continue;
            }
            //根据攻略id获取统计vo的对象
            StrategyStatisVO vo = strategyStatisVOService.getStatisVo(strategy.getId());
            //初始化数据到vo中
            BeanUtils.copyProperties(strategy, vo);
            //把对应的攻略id设置到vo对象里面
            vo.setStrategyId(strategy.getId());
            //更新vo对象并缓存到redis中
            strategyStatisVOService.setStatisVo(vo);
        }

        System.out.println("--------------------攻略统计vo对象初始化-end------------------------------");
    }
}
