package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.annotation.RequireLogin;
import cn.wolfcode.wolf2w.annotation.UserParam;
import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.StrategyContent;
import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.query.StrategyQuery;
import cn.wolfcode.wolf2w.redis.service.IStrategyStatisVOService;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVO;
import cn.wolfcode.wolf2w.service.impl.StrategyServiceImpl;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("strategies")
public class StrategyController {

    @Autowired
    private StrategyServiceImpl strategyService;
    @Autowired
    private IStrategyStatisVOService strategyStatisVOService;


    @GetMapping("/content")
    public JsonResult content(@RequestParam("id") Long strategyId) {
        StrategyContent strategyContent = strategyService.queryStrategyContentByStrategyId(strategyId);
        return JsonResult.success(strategyContent);
    }

    @GetMapping("/detail")
    public JsonResult detail(@RequestParam("id") Long strategyId) {
        Strategy strategy = strategyService.queryStrategyByStrategyId(strategyId);
        //阅读数+1
        strategyStatisVOService.viewnumIncr(strategyId);

        return JsonResult.success(strategy);
    }

    @GetMapping("/themes")
    public JsonResult themes() {
        return null;
    }


    @GetMapping("/query")
    public JsonResult query(StrategyQuery qo) {
        return JsonResult.success(strategyService.queryPage(qo));
    }


    @GetMapping("/rank")
    public JsonResult query(Integer type) {
        return null;
    }


    @GetMapping("/themeCds")
    public JsonResult themeCds() {
        return null;
    }


    @GetMapping("/condition")
    public JsonResult condition(Integer type) {
        return null;
    }


    //获取vo对象，用于统计数据的回显
    @GetMapping("/statisVo")
    public JsonResult getStatisVo(Long sid) {
        StrategyStatisVO vo = strategyStatisVOService.getStatisVo(sid);
        return JsonResult.success(vo);
    }


    //攻略收藏的实现
    @RequireLogin
    @PostMapping("/favor")
    public Object favor(@UserParam UserInfo user, Long sid) {
        Boolean favor = strategyStatisVOService.favor(user.getId(), sid);
        return JsonResult.success(favor);
    }


    //攻略点赞的实现
    @RequireLogin
    @PostMapping("/strategyThumbup")
    public Object strategyThumbup(@UserParam UserInfo user, Long sid) {
        Boolean thumbup = strategyStatisVOService.strategyThumbup(user.getId(), sid);
        return JsonResult.success(thumbup);
    }


}
