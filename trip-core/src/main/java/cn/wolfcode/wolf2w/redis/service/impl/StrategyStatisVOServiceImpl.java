package cn.wolfcode.wolf2w.redis.service.impl;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.redis.service.IStrategyStatisVOService;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVO;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.util.RedisKey;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StrategyStatisVOServiceImpl implements IStrategyStatisVOService {


    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private IStrategyService strategyService;


    //阅读数+1
    @Override
    public void viewnumIncr(Long strategyId) {

        //使用sid拼接出vo对象的key
        String key = RedisKey.STRATEGY_STATIS_VO.join(strategyId.toString());
        StrategyStatisVO vo = null;

        //判断key是否存在
        if (!template.hasKey(key)) {
            //如果不存在，获取vo对象，初始化vo对象，缓存到redis中
            vo = new StrategyStatisVO();
            Strategy strategy = strategyService.getById(strategyId);

            BeanUtils.copyProperties(strategy, vo);
            vo.setStrategyId(strategy.getId());
            //缓存redis
            template.opsForValue().set(key, JSON.toJSONString(vo));
        } else {
            //如果已经存在，获取vo对象
            String voStr = template.opsForValue().get(key);
            vo = JSON.parseObject(voStr, StrategyStatisVO.class);
        }
        //获取vo对象，将vo对象中的 viewnum属性+1
        vo.setViewnum(vo.getViewnum() + 1);

        //更新redis中的vo对象
        template.opsForValue().set(key, JSON.toJSONString(vo));
    }


    //获取vo对象，用于统计数据的回显
    @Override
    public StrategyStatisVO getStatisVo(Long sid) {
        //使用sid拼接出vo对象的key
        String key = RedisKey.STRATEGY_STATIS_VO.join(sid.toString());
        StrategyStatisVO vo = null;

        //判断key是否存在
        if (!template.hasKey(key)) {
            //如果不存在，获取vo对象，初始化vo对象，缓存到redis中
            vo = new StrategyStatisVO();
            Strategy strategy = strategyService.getById(sid);

            BeanUtils.copyProperties(strategy, vo);
            vo.setStrategyId(strategy.getId());
            //缓存redis
            template.opsForValue().set(key, JSON.toJSONString(vo));
        } else {
            //如果已经存在，获取vo对象
            String voStr = template.opsForValue().get(key);
            vo = JSON.parseObject(voStr, StrategyStatisVO.class);
        }
        //获取vo对象，将vo对象中的 viewnum属性+1
        vo.setViewnum(vo.getViewnum() + 1);

        //更新redis中的vo对象
        template.opsForValue().set(key, JSON.toJSONString(vo));

        return vo;
    }
}
