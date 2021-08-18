package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.StrategyContent;
import cn.wolfcode.wolf2w.query.StrategyQuery;
import cn.wolfcode.wolf2w.service.impl.StrategyServiceImpl;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("strategies")
public class StrategyController {

    @Autowired
    private StrategyServiceImpl strategyService;


    @GetMapping("/content")
    public JsonResult content(@RequestParam("id") Long strategyId) {
        StrategyContent strategyContent = strategyService.queryStrategyContentByStrategyId(strategyId);
        return JsonResult.success(strategyContent);
    }

    @GetMapping("/detail")
    public JsonResult detail(@RequestParam("id") Long strategyId) {
        Strategy strategy = strategyService.queryStrategyByStrategyId(strategyId);
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
}
