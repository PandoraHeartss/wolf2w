package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.StrategyContent;
import cn.wolfcode.wolf2w.query.StrategyQuery;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 攻略文章服务接口
 */
public interface IStrategyService extends IService<Strategy> {
    /**
     * 分页
     *
     * @param qo
     * @return
     */
    IPage<Strategy> queryPage(StrategyQuery qo);


    StrategyContent getContent(Long id);

    /**
     * 根据攻略id查询攻略内容
     *
     * @param strategyId 攻略id
     * @return
     */
    StrategyContent queryStrategyContentByStrategyId(Long strategyId);

    /**
     * 根据攻略id查询攻略(含攻略内容)
     *
     * @param strategyId 攻略id
     * @return
     */
    Strategy queryStrategyByStrategyId(Long strategyId);

    /**
     * 根据目的地id查询攻略(含攻略内容)
     *
     * @param destId 目的地id
     * @return
     */
    List<Strategy> queryStrategyByDestId(Long destId);

    //更新统计数
    void updateStatisVO(StrategyStatisVO vo);
}
