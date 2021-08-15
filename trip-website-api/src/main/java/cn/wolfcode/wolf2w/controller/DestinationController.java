package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IRegionService;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("destination")
public class DestinationController {

    @Autowired
    private IDestinationService destinationService;

    @Autowired
    private IRegionService regionService;


    //查询热门区域列表
//    @GetMapping("/hotRegion")
//    public Object hotRegion() {
//        List<Region> list = regionService.queryHotRegion();
//        return JsonResult.success(list);
//    }

}
