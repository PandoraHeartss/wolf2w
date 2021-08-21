package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.*;
import cn.wolfcode.wolf2w.mapper.StrategyContentMapper;
import cn.wolfcode.wolf2w.mapper.StrategyMapper;
import cn.wolfcode.wolf2w.query.StrategyQuery;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVO;
import cn.wolfcode.wolf2w.service.IStrategyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 攻略文章服务接口实现
 */
@Service
@Transactional
public class StrategyServiceImpl extends ServiceImpl<StrategyMapper, Strategy> implements IStrategyService {


    @Autowired
    private StrategyContentMapper strategyContentMapper;

    @Autowired
    private DestinationServiceImpl destinationService;

    @Override
    public IPage<Strategy> queryPage(StrategyQuery qo) {
        IPage<Strategy> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Strategy> wrapper = Wrappers.<Strategy>query();

        wrapper.orderByDesc(!ObjectUtils.isEmpty(qo.getOrderBy()), qo.getOrderBy());
        return super.page(page, wrapper);
    }


    @Override
    public StrategyContent getContent(Long id) {
        return strategyContentMapper.selectById(id);
    }


    @Override
    public StrategyContent queryStrategyContentByStrategyId(Long strategyId) {
        return strategyContentMapper.selectById(strategyId);
    }


    public Strategy queryStrategyByStrategyId(Long strategyId) {
        Strategy strategy = super.getById(strategyId);
        strategy.setContent(strategyContentMapper.selectById(strategyId));
        return strategy;
    }


    public List<Strategy> queryStrategyByDestId(Long destId) {
        QueryWrapper<Strategy> wrapper = new QueryWrapper<>();
        wrapper.eq("dest_id", destId);

        return super.list(wrapper);
    }


    //更新统计数
    @Override
    public void updateStatisVO(StrategyStatisVO vo) {

        UpdateWrapper<Strategy> wrapper = new UpdateWrapper<>();

        wrapper.eq("id", vo.getStrategyId())
                .set("viewnum", vo.getViewnum())
                .set("replynum", vo.getReplynum())
                .set("favornum", vo.getFavornum())
                .set("sharenum", vo.getSharenum())
                .set("thumbsupnum", vo.getThumbsupnum());

        super.update(wrapper);
    }


}
