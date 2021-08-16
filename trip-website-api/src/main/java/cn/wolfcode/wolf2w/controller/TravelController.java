package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.query.TravelQuery;
import cn.wolfcode.wolf2w.service.ITravelService;
import cn.wolfcode.wolf2w.util.JsonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("travels")
public class TravelController {

    @Autowired
    private ITravelService travelService;

    @GetMapping("/query")
    public Object queryPage(TravelQuery qo) {
        IPage<Travel> page = travelService.queryPage(qo);

        return JsonResult.success(page);
    }


}
