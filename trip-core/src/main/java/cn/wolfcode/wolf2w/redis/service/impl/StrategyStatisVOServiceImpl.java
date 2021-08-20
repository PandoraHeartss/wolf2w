package cn.wolfcode.wolf2w.redis.service.impl;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.redis.service.IStrategyStatisVOService;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVO;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.util.RedisKey;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    //用于更新vo对象
    @Override
    public void setStatisVo(StrategyStatisVO vo) {
        String key = RedisKey.STRATEGY_STATIS_VO.join(vo.getStrategyId().toString());
        template.opsForValue().set(key, JSON.toJSONString(vo));

    }


    //攻略收藏的实现
    @Override
    public Boolean favor(Long userId, Long sid) {
        //用userId作为参数传入RedisKey的重设计，得到攻略收藏的key
        String key = RedisKey.STRATEGY_FAVOR_VO.join(userId.toString());
        List<Long> sidList = new ArrayList<>();
        StrategyStatisVO vo = null;

        if (!template.hasKey(key)) {
            //初始化：从mysql数据库中用户攻略收藏列表查询出来【后续自己扩展】


            //如果key不存在，那么就 传入sid到List里面，作为value，缓存到redis里面
            //template.opsForList()   方式1：使用redis里面自带的List操作 【redis版本6之后才可以】
            //template.opsForValue()   方式2：String操作 value值是list的json格式字符串

            template.opsForValue().set(key, sidList.toString());
        } else {
            //如果key存在，就根据key去查找出value,获取sidList集合
            String list = template.opsForValue().get(key);
            //参数1：json格式字符串， 参数2：集合元素泛型
            sidList = JSON.parseArray(list, Long.class);

        }
        //通过文章id获取vo对象
        vo = getStatisVo(sid);

        if (sidList.contains(sid)) {
            //判断sid是否在sidList里面
            //在sidList里面，就说明是想要取消收藏，在vo对象中的收藏数减一，
            // 并移除sidList里面的sid
            vo.setFavornum(vo.getFavornum() - 1);
            sidList.remove(sid);

        } else {
            //不在sidList里面，就说明是想要收藏，在vo对象中的收藏数加一
            //则把sid加入到sidList里面
            vo.setFavornum(vo.getFavornum() + 1);
            sidList.add(sid);
        }

        //更新收藏数被更新之后的vo对象
        this.setStatisVo(vo);
        //收藏数该变之后进行更新到redis
        template.opsForValue().set(key, JSON.toJSONString(sidList));

        return sidList.contains(sid);
    }


    //根据用户id查出攻略收藏id列表
    @Override
    public List<Long> queryStrategyFavorByUserId(Long userId) {

        //根据用户id拼接出攻略收藏的Redis key
        String key = RedisKey.STRATEGY_FAVOR_VO.join(userId.toString());
        List<Long> sidList = new ArrayList<>();

        if (!template.hasKey(key)) {
            //判断是否存在这个Redis Key
            //如果key不存在，直接返回一个空的集合
            template.opsForValue().set(key, sidList.toString());
        } else {
            //如果key存在，用key查出之前存的value,即攻略收藏id列表
            String list = template.opsForValue().get(key);
            sidList = JSON.parseArray(list, Long.class);
        }

        return sidList;
    }
}